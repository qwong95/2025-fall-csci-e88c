package org.cscie88c.core.week6

import org.cscie88c.core.testutils.{ StandardTest }
import AddableTypeclass._
import AddableAggregator._

class AddableTypeclassTest extends StandardTest {
  
  "AddableAggregator" should {
    "sum a list of integers" in {
      // add your unit tests here
      sumWithAddable(List(1, 2, 3, 4)) shouldBe 10
      sumWithAddable(List(42)) shouldBe 42
    }
    "sum a list of booleans" in {
      // add your unit tests here
      sumWithAddable(List(true, false, true))  shouldBe true
      sumWithAddable(List(false, false, false)) shouldBe false
      sumWithAddable(List(true)) shouldBe true
    }
    "sum a list of employees" in {
      // add your unit tests here
      val emps = List(
        Employee("ken",   25, 80000),
        Employee("burns", 35, 90000)
      )

      val total = sumWithAddable(emps)

      total shouldBe Employee("ken,burns", 60, 170000)
    }
  }
}
