package org.cscie88c.core.week3

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._
import org.cscie88c.core.week3.UtilFunctions.mult2
import org.cscie88c.core.week3.UtilFunctions.pythTriplesUpto100
import org.cscie88c.core.week3.UtilFunctions.pythTest

class UtilFunctionsPropertyTest
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  // I know Ven said this is beyond the scope of this class but just want to make sure
  private val smallInt: Gen[Int] = Gen.choose(-20000, 20000)

  val triplesGen: Gen[(Int, Int, Int)] = Gen.oneOf(pythTriplesUpto100)

  test("mult2 result test") {
    forAll(smallInt, smallInt, smallInt) { (a, b, c) =>
      val left = mult2(a, b + c)
      val right = mult2(a, b) + mult2(a, c)
      withClue(s"Failed for a=$a, b=$b, c=$c") {
        left shouldBe right
      }
    }
  }

  test(
    "satisfy the property: if (x, y, z) is a triple, then (y, x, z) is also a triple"
  ) {
    // generate from known triples up to 100
    forAll(triplesGen) { case (x, y, z) =>
      withClue(s"Checking ($x,$y,$z) and swapped ($y,$x,$z)") {
        pythTest(x, y, z) shouldBe true
        pythTest(y, x, z) shouldBe true
      }
    }
  }

  test(
    "satisfy the property: if (x, y, z) is a triple, then (2y, 2x, 2z) is also a triple"
  ) {
    // generate from known triples up to 100
    forAll(triplesGen) { case (x, y, z) =>
      withClue(s"Checking scaled version of ($x, $y, $z)") {
        pythTest(x, y, z) shouldBe true
        pythTest(2 * y, 2 * x, 2 * z) shouldBe true
      }
    }
  }

  // write more property tests below
}
