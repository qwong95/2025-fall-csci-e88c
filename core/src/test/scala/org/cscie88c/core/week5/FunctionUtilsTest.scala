package org.cscie88c.core.week5

import org.cscie88c.core.testutils.StandardTest
import org.cscie88c.core.week5.FunctionUtils._
import FunctionUtils.CustomerTransaction

// run using: sbt "testOnly org.cscie88c.week5.FunctionUtilsTest"
class FunctionUtilsTest extends StandardTest {
  "FunctionUtils" when {
    // Problem 1 unit tests
    "calling colorToCode" should {
      "return the correct value for white" in {
        // write unit tests here
        colorToCode("white") shouldBe(255, 255, 255)
      }

      "return the correct value for lime" in {
        // write unit tests here
        colorToCode("lime") shouldBe(0, 255, 0)
      }
    }

    "calling fizzBuzzString" should {
      "return the correct value" in {
        // write unit tests here
        fizzBuzzString(9) shouldBe "Fizz"
        fizzBuzzString(10) shouldBe "Buzz"
        fizzBuzzString(30) shouldBe "FizzBuzz"
        fizzBuzzString(7) shouldBe "7"
      }
    }

    "calling fizzBuzz" should {
      "return the correct value" in {
        // write unit tests here
        fizzBuzz(6) shouldBe List("1", "2", "Fizz", "4", "Buzz", "Fizz")
      }
    }

    // Problem 2 unit tests
    "tanDegrees" should {
      "not be defined at 90 degrees" in {
        tanDegrees.isDefinedAt(90.0) shouldBe false
      }
      "not be defined at -90 degrees" in {
        tanDegrees.isDefinedAt(-90.0) shouldBe false
      }
      "return correct tangent for a safe angle (e.g., 45)" in {
        val v = tanDegrees(45.0)
        v shouldBe 1.0 +- 1e-12
      }
    }

    "totalHighValueTransactions" should {
      "sum only amounts strictly greater than 100" in {
        val txns = List(
          CustomerTransaction("c1", "2025-10-01", 50.0),
          CustomerTransaction("c2", "2025-10-02", 100.0), // not high value
          CustomerTransaction("c3", "2025-10-03", 101.0), // high value
          CustomerTransaction("c4", "2025-10-04", 200.0), // high value
          CustomerTransaction("c5", "2025-10-05", 99.99) // not high value
        )
        totalHighValueTransactions(txns) shouldBe 301.0
      }
    }
    // Problem 3 unit tests
    "flip2" should {
      "flip the order of arguments for math.pow" in {
        val flippedPow = flip2(math.pow)
        math.pow(5, 2) shouldBe 25.0
        flippedPow(5, 2) shouldBe 32.0 // 2^5 = 32
      }

      "work with a string concatenation function" in {
        val concat = (a: String, b: String) => a + b
        val flipped = flip2(concat)
        flipped("world", "hello") shouldBe "helloworld"
      }
    }

    "sampleList" should {
      "return first 5 elements for a list of Ints" in {
        sampleList(List(1, 2, 3, 4, 5, 6, 7)) shouldBe List(1, 2, 3, 4, 5)
      }

      "return all elements if list shorter than 5" in {
        sampleList(List(1, 2)) shouldBe List(1, 2)
      }

      "return first 5 elements for a list of Strings" in {
        sampleList(List("one", "two", "three", "four", "five", "six")) shouldBe
          List("one", "two", "three", "four", "five")
      }

      // Bonus unit tests
    }
  }
}
