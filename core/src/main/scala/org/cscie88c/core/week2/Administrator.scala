package org.cscie88c.core.week2

// write code for class Administrator below
class Administrator(
    name: String,
    email: String,
    val budget: Long) extends UniversityEmployee(name, email) {
    override def description: String =
        s"Name: ${name}, Email: ${email}, Budget: ${'$'}${budget}"
}