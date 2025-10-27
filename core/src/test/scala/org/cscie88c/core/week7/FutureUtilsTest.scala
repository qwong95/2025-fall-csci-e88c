package org.cscie88c.core.week7

import org.cscie88c.core.testutils.{FuturesTest}
import scala.concurrent.Future

class FutureUtilsTest extends FuturesTest {
  
  "Any future function" should {
    "return a future assertion" in {
      def futureAdd2(x: Int) = Future(x + 2)
      futureAdd2(5).map { x =>
        x shouldBe 7
      }
    }
  }

  "FutureFunctions" when {
    "calling creditScoreAPI" should {

      "return a credit score greater than 300" in {
        // add unit tests below
        fail("add unit tests")
      }
    }

    "calling futureFactorial" should {
      "return 24 for input 4" in {
        val f = futureFactorial(4)
        whenReady(f) { res =>
          res shouldBe 24
        }
      }
    }
  }
}
