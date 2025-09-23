package org.cscie88c.core.week2

import org.cscie88c.core.testutils.{StandardTest}
import org.cscie88c.core.week2.Student.allStudents
//import org.cscie88c.core.week2.Student.allStudents

// write unit tests for Student below

class StudentTest extends StandardTest {
  "Student" when {
    "instantiated" should {
      "properly list name fields" in {
        val s = Student("1,Emmy,Conrart,econrart0@gizmodo.com,Male,China")
        s.id shouldBe 1
        s.firstName shouldBe "Emmy"
        s.lastName shouldBe "Conrart"
        s.email shouldBe "econrart0@gizmodo.com"
        s.gender shouldBe "Male"
        s.country shouldBe "China"
        s.fullName shouldBe "Emmy Conrart"
      }
    }

    "getting all students" should {
        "get all students in the provided table" in {
            allStudents.length should be (5)
        }
    }

    "filtered by country" should {
        "contain full names of students from that country" in {
            val namesChina = Student.studentNamesByCountry("China")
            namesChina should contain theSameElementsAs List("Emmy Conrart", "Jesse Chismon", "Jocelyn Blaxlande")

            val namesUS = Student.studentNamesByCountry("United States")
            namesUS should contain theSameElementsAs List("Marin Blasoni", "Delmore Scriver")
        }

        "count the number of students from that country" in {
            Student.studentTotalsByCountry("China") shouldBe 3
            Student.studentTotalsByCountry("United States") shouldBe 2
            Student.studentTotalsByCountry("Brazil") shouldBe 0
        }
      }
  }

}
