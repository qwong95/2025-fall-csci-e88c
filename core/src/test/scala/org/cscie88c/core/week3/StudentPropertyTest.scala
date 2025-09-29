package org.cscie88c.core.week3
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import org.scalacheck._
import org.cscie88c.core.week3.Student.averageScoreBySubject
import org.cscie88c.core.week3.Student.averageScoreBySubject

class StudentPropertyTest
    extends AnyFunSuite
    with Matchers
    with ScalaCheckPropertyChecks {

  // ----- Base generators -----
  private val nameGen = Gen.alphaStr.suchThat(_.nonEmpty)
  private val userGen = Gen.alphaLowerStr.suchThat(_.nonEmpty)
  private val domainGen = Gen.oneOf("example.com", "school.edu", "test.org")
  private val subjectGen = Gen.oneOf("English", "Math", "Science")
  private val scoreGen = Gen.choose(0, 100)
  private val scoreLt100 = Gen.choose(0, 99)

  val studentGen: Gen[Student] = for {
    name <- Gen.alphaStr.suchThat(_.nonEmpty) // non-empty string for name
    user <- Gen.alphaLowerStr.suchThat(_.nonEmpty) // local part of email
    domain <- Gen.oneOf("example.com", "school.edu", "test.org")
    subject <- Gen.oneOf("English", "Math", "Science") // allowed subjects
    score <- Gen.choose(0, 100) // percentage score
  } yield Student(name, s"$user@$domain", subject, score)

  // complete the student list generator below if attempting bonus problem
  val studentsListGen: Gen[List[Student]] = for {
    n <- Gen.choose(0, 30)
    list <- Gen.listOfN(n, for {
      name    <- nameGen
      user    <- userGen
      domain  <- domainGen
      subject <- subjectGen
      score   <- subject match {
        case "Math"    => scoreLt100
        case _         => scoreGen
      }
    } yield Student(name, s"$user@$domain", subject, score))
  } yield list

  test("description contains name and email") {
    forAll(studentGen) { student =>
        val desc = student.description
        withClue(s"Description [$desc] should contain name and email") {
          desc should include(student.name)
          desc should include(student.email)
        }
    }
  }

  test("averageScoreBySubject (Math) < 100 for generated student lists") {
      forAll(studentsListGen) { students =>
        val avgMath = averageScoreBySubject("Math", students)
        avgMath < 100.0 shouldBe true
      }
  }
}
