package org.cscie88c.core.week6

import org.cscie88c.core.testutils.{ StandardTest }

class AddableTraitTest extends StandardTest {

  "plus" should {

    "add two MyInt values correctly" in {
      // add your unit tests for MyInt below
      MyInt(5).plus(MyInt(3)) shouldBe MyInt(8)
      MyInt(5).plus(MyInt(2)) shouldBe MyInt(7)
      MyInt(5).plus(MyInt(-3)) shouldBe MyInt(2)
    }

    "add two MyBool values correctly" in {
      // add your unit tests for MyBool below
      MyBool(true).plus(MyBool(false))  shouldBe MyBool(true)
      MyBool(true).plus(MyBool(true))   shouldBe MyBool(true)
      MyBool(false).plus(MyBool(false)) shouldBe MyBool(false)
      MyBool(false).plus(MyBool(true))  shouldBe MyBool(true)
    }
   } 
}
