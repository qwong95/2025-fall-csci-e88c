package org.cscie88c.core.week3

final case class Student(
    name: String,
    email: String,
    subject: String,
    score: Int
) {
  def description: String =
    s"name: ${name}, email: ${email}, subject: ${subject}, score: ${score}"
}

object Student {

  def validateEmail(student: Student): Boolean = student.email.contains("@")

  def averageScoreBySubject(
      subject: String,
      studentList: List[Student]
  ): Double = {
    val target = subject.trim.toLowerCase
    val scores = studentList.collect {
      case s if s.subject.trim.toLowerCase == target => s.score
    }
    if (scores.nonEmpty) scores.sum / scores.size else 0.0
  }

  def averageScoreByStudent(
      student: Student,
      studentList: List[Student]
  ): Double = {
    val key = student.email.trim.toLowerCase
    val scores = studentList.collect {
      case s if s.email.trim.toLowerCase == key => s.score
    }
    if (scores.nonEmpty) scores.sum / scores.size else 0.0
  }
}
