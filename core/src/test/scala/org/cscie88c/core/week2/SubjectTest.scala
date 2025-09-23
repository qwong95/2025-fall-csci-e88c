package org.cscie88c.core.week2

import org.cscie88c.core.testutils.{StandardTest}
import org.cscie88c.core.week2.Subject.allSubjects

// write unit tests for Subject below

class SubjectTest extends StandardTest {
    "Subject" when {
        "instantiated" should {
            "properly list subject fields" in {
                val sub = Subject("1,Physics,true")
                sub.id shouldBe 1
                sub.name shouldBe "Physics"
                sub.isSTEM shouldBe true

                println(allSubjects)
            }
        // add more unit tests for maximum below
        
        }

        "getting all subjects" should {
            "get all subjects in the provided table" in {
                allSubjects.length should be (4)
            }
        }

        // add unit tests for median below
        "filtered by STEM" should {
            "list only STEM subject" in {
                val names = Subject.stemSubjects.map(_.name)
                names should contain theSameElementsAs List("Physics", "Chemistry", "Math")
                names should not contain "English"
            }

        }
    
  }
}
