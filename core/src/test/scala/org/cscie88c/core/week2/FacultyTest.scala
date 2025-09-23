package org.cscie88c.core.week2

import org.cscie88c.core.testutils.{StandardTest}

// write unit tests for Faculty below
class FacultyTest extends StandardTest {
  "Faculty" when {
    "instantiated" should {
      "have a name property" in {
        val jim = new Faculty("Jim Stone", "jstone@harvard.edu", "Math101")
        jim.description.contains("Jim Stone") shouldBe true
      }
      "have an email property" in {
        val ann = new Faculty("Ann Hsiao", "ahsiao@harvard.edu", "Bio210")
        ann.description.contains("ahsiao@harvard.edu") shouldBe true
      }
      "have an course property" in {
        val adam = new Faculty("Adam Smith", "asmith@harvard.edu", "Chem202")
        adam.description.contains("Chem202") shouldBe true
      }
    }
  }
}
