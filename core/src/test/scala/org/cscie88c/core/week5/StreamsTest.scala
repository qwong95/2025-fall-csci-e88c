package org.cscie88c.core.week5

import org.cscie88c.core.testutils.StandardTest
import Streams.{Dog, averageHealthyAge, dogs, healthyDogs}

class StreamsTest extends StandardTest {

  // Bonus problem unit tests
  "healthyDogs" should {
    "keep only dogs with current shots (sample of 5 from infinite stream)" in {
      val sample = dogs.take(5)
      val healthy = healthyDogs(sample)
      // All returned dogs must be healthy
      all(healthy.map(_.hasCurrentShots)) shouldBe true
    }
  }

  "averageHealthyAge" should {
    "compute average age of healthy dogs in a sample of 5" in {
      val sampleSize = 5
      val sample = dogs.take(sampleSize).toList

      val expected: Double = {
        val hs = sample.collect { case Dog(_, age, true) => age }
        if (hs.isEmpty) 0.0 else hs.sum.toDouble / hs.size
      }

      val got = averageHealthyAge(dogs, sampleSize)
      got shouldBe expected +- 1e-9
    }
  }
}
