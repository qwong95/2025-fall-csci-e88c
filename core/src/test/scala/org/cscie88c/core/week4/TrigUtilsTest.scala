package org.cscie88c.core.week4

import org.cscie88c.core.testutils.{StandardTest}

class TrigUtilsTest extends StandardTest {
  // Helper tolerance for floating-point comparisons
  private val Eps = 1e-12

  "TrigUtils" when {
    "calling sin" should {
      "return the correct value for 90" in {
        // write unit test below
        TrigUtils.sinDegrees(90.0) should equal (1.0 +- Eps)
      }

      "return the correct value for 0" in {
        TrigUtils.sinDegrees(0.0) should equal (0.0 +- Eps)
      }
    
    }

    "calling cos" should {
      "return the correct value for 90" in {
        TrigUtils.cosDegrees(90.0) should equal (0.0 +- Eps)
      }

      "return the correct value for 0" in {
        TrigUtils.cosDegrees(0.0) should equal (1.0 +- Eps)
      }
    }

    "squared" should {
      "square positive, zero, and negative values" in {
        TrigUtils.squared(0.0)   should equal (0.0 +- Eps)
        TrigUtils.squared(3.0)   should equal (9.0 +- Eps)
        TrigUtils.squared(-4.5)  should equal (20.25 +- Eps)
      }
    }

    // write tests for cos and squared below
  }
}
