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
    
  }
}
