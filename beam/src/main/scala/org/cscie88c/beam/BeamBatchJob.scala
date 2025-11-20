package org.cscie88c.beam

import com.spotify.scio._
import com.spotify.scio.values.SCollection
import com.typesafe.scalalogging.LazyLogging

/*
 * run with
 beam/target/universal/stage/bin/beam-batch-job \
 --input-file=/path-to-input-file \
 --output-file=/path-to-output-folder
 */
object BeamBatchJob extends LazyLogging {

  def main(cmdLineArgs: Array[String]): Unit = {
    // 1. Create context
    val (sc: ScioContext, args: Args) = ContextAndArgs(cmdLineArgs)
    implicit val scImplicit: ScioContext = sc

    // 2. Read command line arguments
    val inputFile  = args.getOrElse("input-file", "data/taxi_tripdata.csv")
    val outputFile  = args.getOrElse("output-file", "output/batch_results")

    // 3. Run pipeline
    // uncomment line below to run the beam pipeline
    runPipeline(inputFile: String, outputFile: String)

    sc.run().waitUntilDone()
  }

  def runPipeline(inputFile: String, outputFile: String)
                 (implicit sc: ScioContext): Unit = {

    val sumRows = sumTransactions(inputFile)
      .withGlobalWindow()

    val avgRows = avgTransactions(inputFile)
      .withGlobalWindow()

    // combine them into one output
    (sumRows ++ avgRows)
      .saveAsTextFile(outputFile)
  }

  def sumTransactions(inputFile: String)
                     (implicit sc: ScioContext): SCollection[String] = {

    sc.textFile(inputFile)
      .filter(_.nonEmpty)
      .filter(!_.startsWith("VendorID"))
      .flatMap(TaxiTripData(_))
      .map(_.total_amount)
      .sum
      .map(sum => s"Sum total amount: $sum")
  }

  def avgTransactions(inputFile: String)
                     (implicit sc: ScioContext): SCollection[String] = {

    val zero = (0.0, 0L)

    sc.textFile(inputFile)
      .filter(_.nonEmpty)
      .filter(!_.startsWith("VendorID"))
      .flatMap(TaxiTripData(_))
      .map(_.total_amount)
      .aggregate(zero)(
        (acc, v) => (acc._1 + v, acc._2 + 1),
        (a, b) => (a._1 + b._1, a._2 + b._2)
      )
      .map { case (sum, count) =>
        val avg = sum / count
        s"Average total amount: $avg"
      }
  }
}