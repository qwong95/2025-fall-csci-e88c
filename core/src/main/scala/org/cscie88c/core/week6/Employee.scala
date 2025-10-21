package org.cscie88c.core.week6

final case class Employee(name: String, age: Int, salary: Int)

object Employee {

  implicit val employeeOrdering: Ordering[Employee] = Ordering.by[Employee, String](_.name)

  implicit val employeeAddableTypeclass: AddableTypeclass[Employee] =
    new AddableTypeclass[Employee] {
      override def addTwoValues(a: Employee, b: Employee): Employee =
        Employee(
          name   = s"${a.name},${b.name}",
          age    = a.age + b.age,
          salary = a.salary + b.salary
        )
    }

  def defaultSortEmployees(employees: List[Employee]): List[Employee] = employees.sorted

  def sortEmployeesBySalary(employees: List[Employee]): List[Employee] = {
    val bySalaryDesc: Ordering[Employee] =
      Ordering.by[Employee, Int](_.salary).reverse
    employees.sorted(bySalaryDesc)
  }
  
}
