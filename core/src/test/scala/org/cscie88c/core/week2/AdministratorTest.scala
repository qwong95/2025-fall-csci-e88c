package org.cscie88c.core.week2

import org.cscie88c.core.testutils.{StandardTest}

// write unit tests for Administrator below
class AdministratorTest extends StandardTest {
  "Administrator" when {
    "instantiated" should {
      "have a name property" in {
        val jake = new Faculty("Jake Johnson", "jjohnson@harvard.edu", "1000000")
        println(jake.description)
        jake.description.contains("Jake Johnson") shouldBe true
      }
      "have an email property" in {
        val allison = new Faculty("Allison Simmons", "asimmons@harvard.edu", "200000")
        println(allison.description)
        allison.description.contains("asimmons@harvard.edu") shouldBe true
      }
      "have a budget amount including a dollar sign" in {
        val anita = new Administrator("Anita Jackson", "ajackson@harvard.edu", 9001)
        println(anita.description)
        anita.description.contains("$9001") shouldBe true
      }
    }
  }
}
