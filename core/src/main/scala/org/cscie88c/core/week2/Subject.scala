package org.cscie88c.core.week2

//import cats.instances.boolean

// complete the definition of the Subject case class and companion object
final case class Subject(
  id: Int,
  name: String,
  isSTEM: Boolean
)

object Subject {
  def apply (csvRow: String): Subject = {
    val fields = csvRow.split(",")
    Subject (
      id = fields(0).toInt,
      name = fields(1),
      isSTEM = fields(2).toBoolean
    )
  }

  val sub: List[String] = List(
    "1,Physics,true",
    "2,Chemistry,true",
    "3,Math,true",
    "4,English,false"
  )

  val allSubjects: List[Subject] = sub.map(Subject(_))

  def stemSubjects: List[Subject] = allSubjects.filter(_.isSTEM)
  
}