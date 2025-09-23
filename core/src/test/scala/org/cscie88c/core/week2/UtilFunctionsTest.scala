package org.cscie88c.core.week2

import org.cscie88c.core.testutils.{StandardTest}

class UtilFunctionsTest extends StandardTest {
  
  "UtilFunctions" when {
    "maximum" should {
      "return maximum of two ints when first integer is greater" in {
        UtilFunctions.maximum(2, 1) should be (2)
        UtilFunctions.maximum(3, 2) should be (3)
        UtilFunctions.maximum(4, 1) should be (4)
        UtilFunctions.maximum(4, 4) should be (4)
      }
      // add more unit tests for maximum below
      
    }

    // add unit tests for median below
    "median" should {
      "return the middle value of a sorted list if there are an odd number of values" in {
        UtilFunctions.median(List(1, 5, 2, 3, 6)) should be (3)
        UtilFunctions.median(List(4, 3, 6)) should be (4)
        UtilFunctions.median(List(4, 4, 4, 4, 4)) should be (4)
      }

      "return the average of the 2 middle numbers in an even sorted list" in {
        UtilFunctions.median(List(1, 4, 2, 3)) should be (2.5)
        UtilFunctions.median(List(1, 4, 2, 4)) should be (3)
        UtilFunctions.median(List(1, 4, 2, 7, 5, 6)) should be (4.5)
      }
    }
    
  }
}
