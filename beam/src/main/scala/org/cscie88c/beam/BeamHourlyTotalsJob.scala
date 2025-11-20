package org.cscie88c.beam

import com.spotify.scio._
import com.spotify.scio.values.SCollection
import com.typesafe.scalalogging.LazyLogging
import org.joda.time.{Duration, Instant}
import java.time.ZoneId
import org.apache.beam.sdk.transforms.windowing.IntervalWindow

object BeamHourlyTotalsJob extends LazyLogging {

  /*
   * Example run command (after staging the app):
   *
   * beam/target/universal/stage/bin/beam-hourly-totals-job \
   *   --input-file=data/taxi_tripdata.csv \
   *   --output-file=output/taxi_hourly_totals
   */
  def main(cmdLineArgs: Array[String]): Unit = {
    // 1. Create context
    val (sc: ScioContext, args: Args) = ContextAndArgs(cmdLineArgs)
    implicit val scImplicit: ScioContext = sc

    // 2. Read command line arguments (with reasonable defaults)
    val inputFile  = args.getOrElse("input-file", "data/taxi_tripdata.csv")
    val outputFile = args.getOrElse("output-file", "output/taxi_hourly_totals")

    // 3. Run pipeline
    runPipeline(inputFile, outputFile)

    // 4. Execute
    sc.run().waitUntilDone()
  }

  private def calculateHourlyTotals(inputFile: String)
                                   (implicit sc: ScioContext): SCollection[(Instant, Double)] = {

    val zoneId = ZoneId.of("UTC")

    // Sum total_amount in 1-hour fixed windows using event-time timestamps
    val hourlyTotals: SCollection[Double] =
      sc.textFile(inputFile)
        .filter(_.nonEmpty)
        .filter(!_.startsWith("VendorID"))
        .flatMap(line => TaxiTripData(line))
        .timestampBy { trip =>
          val inst = trip.lpep_dropoff_datetime
            .atZone(zoneId)
            .toInstant
          new Instant(inst.toEpochMilli)
        }
        .map(_.total_amount)
        .withFixedWindows(Duration.standardHours(1))
        .sum

    // Attach window end time to each aggregate
    hourlyTotals
      .withWindow[IntervalWindow]
      .map { case (total, w) =>
        (w.end(), total)
      }
  }

  def runPipeline(inputFile: String, outputFile: String)
                 (implicit sc: ScioContext): Unit = {

    calculateHourlyTotals(inputFile)
      .map { case (windowEnd, total) =>
        // CSV: window_end_time,total_hourly_amount
        s"${windowEnd.toString},$total"
      }
      .withGlobalWindow()
      .saveAsTextFile(outputFile)
  }
}
