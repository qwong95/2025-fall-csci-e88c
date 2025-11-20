package org.cscie88c.beam

import com.spotify.scio._
import com.spotify.scio.values.SCollection
import com.typesafe.scalalogging.LazyLogging
import com.twitter.algebird.CMS

import com.twitter.algebird.CMSMonoid

/*
 * run with
 beam/target/universal/stage/bin/beam-cms-job \
 --input-file=/path-to-input-file \
 --output-file=/path-to-output-folder
 */
object BeamCMSJob extends LazyLogging {

  def main(cmdLineArgs: Array[String]): Unit = {
    // 1. Create context
    val (sc: ScioContext, args: Args) = ContextAndArgs(cmdLineArgs)
    implicit val scImplicit: ScioContext = sc

    // 2. Read command line arguments
    val inputFile  = args.getOrElse("input-file", "data/taxi_tripdata.csv")
    val outputFile = args.getOrElse("output-file", "output/taxi_CMS")

    // 3. Run pipeline
    // uncomment line below to run the beam pipeline
    runPipeline(inputFile, outputFile)

    sc.run().waitUntilDone()
  }
  def approximatePickupLocations(inputFile: String)
                                (implicit sc: ScioContext): SCollection[CMS[Long]] = {

    val eps   = 0.001
    val delta = 1e-5
    val seed  = 1
    val cmsMonoid = new CMSMonoid[Long](eps, delta, seed)

    sc.textFile(inputFile)
      .filter(_.nonEmpty)
      .filter(!_.startsWith("VendorID"))
      .flatMap(line => TaxiTripData(line))
      .map(_.PULocationID.toLong)
      .map(id => cmsMonoid.create(id))
      .reduce(cmsMonoid.plus)
  }

  def runPipeline(inputFile: String, outputFile: String)
                 (implicit sc: ScioContext): Unit = {

    val cmsSketch: SCollection[CMS[Long]] = approximatePickupLocations(inputFile)

    cmsSketch
      .flatMap { cms =>
        val est49 = cms.frequency(49L).estimate
        val est74 = cms.frequency(74L).estimate

        println(s"[BeamCMSJob] Approx freq for PULocationID 49 = $est49")
        println(s"[BeamCMSJob] Approx freq for PULocationID 74 = $est74")

        Seq(
          s"Estimate for 49: $est49",
          s"Estimate for 74: $est74"
        )
      }
      .saveAsTextFile(outputFile)
  }
}
