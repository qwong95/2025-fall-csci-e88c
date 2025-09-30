package org.cscie88c.core.week3

import org.cscie88c.core.testutils.{StandardTest}
import org.cscie88c.core.week3.UtilFunctions.pythTest
import org.cscie88c.core.week3.UtilFunctions.pythTriplesUpto100

class UtilFunctionsTest extends StandardTest {
  "UtilFunctions" when {
    "with pythTriplesUpto100" should {
      "verify elements in parameters are pythagorean triples, with the sums of the squares " +
        "of the first two parameters equalling the square of the third " in {
          // write your test here
          pythTest(3, 4, 5) shouldBe true
          pythTest(4, 3, 5) shouldBe true
          pythTest(5, 4, 3) shouldBe false
        }
    }

    "with pythTriplesUpto100" should {
      "contain known triples" in {
        pythTriplesUpto100 should contain allOf ((3, 4, 5), (5, 12, 13), (
          8,
          15,
          17
        ), (7, 24, 25))
      }

      "only contain valid Pythagorean triples" in {
        all(
          pythTriplesUpto100.map { case (a, b, c) => pythTest(a, b, c) }
        ) shouldBe true
      }

      "be deterministic and allow sampling a few elements to verify" in {
        pythTriplesUpto100.take(3).foreach { case (a, b, c) =>
          pythTest(a, b, c) shouldBe true
        }
      }
    }
  }
}
