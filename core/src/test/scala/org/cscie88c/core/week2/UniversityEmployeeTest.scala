package org.cscie88c.core.week2

import org.cscie88c.core.testutils.{StandardTest}

// write unit tests for University employee below
class UniversityEmployeeTest extends StandardTest {
  "UniversityEmployee" when {
    "instantiated" should {
      "have a name property" in {
        val mike = new UniversityEmployee("Mike Stone", "mstone@harvard.edu")
        mike.description.contains("Mike Stone") shouldBe true
      }
      "have an email property" in {
        val jane = new UniversityEmployee("Jane Kim", "jkim@harvard.edu")
        jane.description.contains("jkim@harvard.edu") shouldBe true
      }
    }
  }
}
