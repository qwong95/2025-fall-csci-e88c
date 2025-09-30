package org.cscie88c.core.week3

import org.cscie88c.core.testutils.{StandardTest}
import org.cscie88c.core.week3.Student.validateEmail
import org.cscie88c.core.week3.Student.averageScoreBySubject
import org.cscie88c.core.week3.Student.averageScoreByStudent

class StudentTest extends StandardTest {
  // Test data used across specs
  val sMathA = Student("Alice", "alice@example.com", "Math", 90)
  val sEngA = Student("Alice", "alice@example.com", "English", 80)
  val sMathB = Student("Bob", "bob@example.com", "Math", 70)
  val sSciBad =
    Student("Cara", "cara_at_example.com", "Science", 85) // invalid email
  val students = List(sMathA, sEngA, sMathB, sSciBad)

  "Student Management System" when {
    "creating a student" should {
      "have properties - name, email, subject and score" in {
        // write your test here
        sMathA.name shouldBe "Alice"
        sMathA.email shouldBe "alice@example.com"
        sMathA.subject shouldBe "Math"
        sMathA.score shouldBe 90
      }
    }

    // write more tests to verify items in acceptance criteria
    "validateEmail" should {
      "return true for a valid email containing '@' (AC: validateEmail true for valid)" in {
        validateEmail(sMathA) shouldBe true
        validateEmail(sEngA) shouldBe true
      }
      "return false for an invalid email missing '@' (AC: validateEmail false for invalid)" in {
        validateEmail(sSciBad) shouldBe false
      }
    }

    "averageScoreBySubject" should {
      "compute the average for a subject (AC: averageScoreBySubject)" in {
        // Math: (90 + 70) / 2 = 80.0
        averageScoreBySubject("Math", students) shouldBe 80.0
      }
      "be case-insensitive and return 0.0 when no matches" in {
        averageScoreBySubject("english", students) shouldBe 80.0
        averageScoreBySubject("History", students) shouldBe 0.0
      }
    }

    "averageScoreByStudent" should {
      "compute the average across a student's enrolled subjects (AC: averageScoreByStudent)" in {
        // Alice: (90 + 80) / 2 = 85.0
        averageScoreByStudent(sMathA, students) shouldBe 85.0
      }
      "match students by email and return 0.0 when none are found" in {
        val ghost = Student("Ghost", "nobody@example.com", "Math", 0)
        averageScoreByStudent(ghost, students) shouldBe 0.0
      }
    }
  }

}
