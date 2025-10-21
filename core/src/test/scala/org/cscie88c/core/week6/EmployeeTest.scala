package org.cscie88c.core.week6

import org.cscie88c.core.testutils.{ StandardTest }

class EmployeeTest extends StandardTest {
  val e1 = Employee("Charlie", 30, 120)
  val e2 = Employee("Alice",   28, 150)
  val e3 = Employee("Bob",     40, 100)
  val e4 = Employee("Bob",     22, 180)

  "Employee" should {
 
    "have a default sort order" in {
      // write unit tests here
      val in  = List(e1, e2, e3)
      val out = Employee.defaultSortEmployees(in)
      out.map(_.name) shouldBe List("Alice", "Bob", "Charlie")
    }

    "sort employees by salary" in {
      // write unit tests here
      val in  = List(e1, e2, e3, e4)
      val out = Employee.sortEmployeesBySalary(in)
      out.map(_.salary) shouldBe List(180, 150, 120, 100)
      out.head shouldBe e4
    }
  }
}
