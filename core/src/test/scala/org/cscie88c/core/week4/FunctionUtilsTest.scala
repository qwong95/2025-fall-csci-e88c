package org.cscie88c.core.week4

import org.cscie88c.core.testutils.{StandardTest}

class FunctionUtilsTest extends StandardTest {

  // helper used in several tests
  private val add5: Int => Int = _ + 5
  
  "FunctionUtils" when {
    "calling applyNtimes" should {
      "return the correct value" in {
        // write unit test here
        applyNtimes(3)(0)(add5) shouldBe 15
        applyNtimes(0)(42)(add5) shouldBe 42
        applyNtimes(2)(7)(_ * 2) shouldBe 28
      }
    }

    // write unit tests for other functions here
    "calling myPositivePower" should {
      "compute x^n for non-negative n using applyNtimes" in {
        myPositivePower(2)(0) shouldBe 1
        myPositivePower(2)(5) shouldBe 32
        myPositivePower(3)(4) shouldBe 81
        myPositivePower(-2)(3) shouldBe -8
      }
    }

    "calling deferredExecutor" should {
      "return a function that logs and then applies f" in {
        val wrapped = deferredExecutor("CPU Pool")(add5)
        wrapped(4) shouldBe 9
      }
      
      "work with other functions too" in {
        val times2 = deferredExecutor("BG")( _ * 2 )
        times2(10) shouldBe 20
      }
    }
  }
 
}
