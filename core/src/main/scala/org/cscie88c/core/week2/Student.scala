package org.cscie88c.core.week2

// complete the definition of the Student case class and companion object
final case class Student(
  id: Int,
  firstName: String,
  lastName: String,
  email: String,
  gender: String,
  country: String
) {
  def fullName: String = s"${firstName} ${lastName}"
}

object Student {
  def apply (csvRow: String): Student = {
    val fields = csvRow.split(",")
    Student (
      id = fields(0).toInt,
      firstName = fields(1),
      lastName = fields(2),
      email = fields(3),
      gender = fields(4),
      country = fields(5)
    )
  }

  val st: List[String] = List(
    "1,Emmy,Conrart,econrart0@gizmodo.com,Male,China",
    "2,Marin,Blasoni,mblasoni1@edublogs.org,Female,United States",
    "3,Jesse,Chismon,jchismon2@hostgator.com,Male,China",
    "4,Delmore,Scriver,dscriver3@boston.com,Male,United States",
    "5,Jocelyn,Blaxlande,jblaxlande4@europa.eu,Female,China"
  )

  val allStudents: List[Student] = st.map(Student(_))
  
  def studentNamesByCountry(country: String): List[String] = {
    allStudents.filter(_.country.equals(country)).map(_.fullName)
  }

  def studentTotalsByCountry(country: String): Int = {
    allStudents.filter(_.country.equals(country)).length
  }
  
}