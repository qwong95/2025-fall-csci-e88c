package org.cscie88c.core.week2

// write the class UniversityEmployee below
class UniversityEmployee(val name: String, val email: String) {
  def description: String =
    s"Name: ${name}, Email: ${email}"
}
