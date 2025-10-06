package org.cscie88c.core.week4

object FunctionUtils {
  
  // complete the implementation of the higher order functions below
  def applyNtimes(n: Int)(x: Int)(f: Int => Int): Int = (1 to n).foldLeft(x)((acc, _) => f(acc))

  def myPositivePower(x: Int, n: Int): Int = applyNtimes(n)(1)(acc => acc * x)

  def deferredExecutor(name: String)(f: Int => Int): Int => Int = (input: Int) => {
      println(s"running on deferred executor $name with value $input")
      f(input)
  }
}
