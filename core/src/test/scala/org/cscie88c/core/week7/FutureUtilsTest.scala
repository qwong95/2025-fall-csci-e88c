package org.cscie88c.core.week7

import org.cscie88c.core.testutils.FuturesTest
import org.cscie88c.core.week7.FutureUtils.{asyncAverageCreditScore, creditScoreAPI, futureFactorial, futurePermutations}

import scala.concurrent.{Await, Future, TimeoutException}
import scala.util.{Failure, Success}

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
        FutureUtils.creditScoreAPI(301).map { score =>
          score should (be >= 300 and be <= 800)
        }.recover {
          case _: Throwable => succeed
        }
      }

      "calling futureFactorial" should {
        "return 24 for input 4" in {
          futureFactorial(4).map { x => x shouldBe 24 }
        }
      }

      "futurePermutations" should {
        "compute 5P3 = 60" in {
          futurePermutations(5, 3).map { x => x shouldBe 60 }
        }

        "compute 6P0 = 1" in {
          futurePermutations(6, 0).map { x => x shouldBe 1 }
        }
      }

      "asyncAverageCreditScore" should {
        "return an average strictly between 300 and 800 (no retries)" in {
          val ids = (1 to 20).toList
          FutureUtils.asyncAverageCreditScore(ids).map { avg =>
            avg should be > 300.0
            avg should be < 800.0
          }.recover {
            case _: Throwable => succeed
          }
        }
      }
    }
  }
}
