package org.cscie88c.core.week2

import org.cscie88c.core.week2.UniversityEmployee

// write code for class Faculty below
class Faculty(
    name: String,
    email: String,
    val courseId: String) extends UniversityEmployee(name, email) {
    override def description: String =
        s"Name: ${name}, Email: ${email}, Course: ${courseId}"
}