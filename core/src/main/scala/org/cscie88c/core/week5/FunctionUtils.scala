package org.cscie88c.core.week5

import scala.math.{tan,toRadians}

object FunctionUtils {

  case class CustomerTransaction(customerId: String,transactionDate: String,transactionAmount: Double)

  // Problem 1
  def colorToCode(color: String): (Int, Int, Int) = color.toLowerCase match {
    case "black" => (0, 0, 0)
    case "white" => (255, 255, 255)
    case "red" => (255, 0, 0)
    case "lime" => (0, 255, 0)
    case "blue" => (0, 0, 255)
    case "yellow" => (255, 255, 0)
    case other => throw new IllegalArgumentException(s"Unknown color: $other")
  }

  def fizzBuzzString(n: Int): String = n match {
    case x if x % 15 == 0 => "FizzBuzz"
    case x if x % 3 == 0 => "Fizz"
    case x if x % 5 == 0 => "Buzz"
    case x => x.toString
  }

  def fizzBuzz(n: Int): List[String] = (1 to n).map(fizzBuzzString).toList

  // Problem 2
  def tanDegrees: PartialFunction[Double, Double] = {
    case d if d != 90.0 && d != -90.0 => tan(toRadians(d))
  }

  def totalHighValueTransactions(transactionList: List[CustomerTransaction]): Double = transactionList.collect {
    case CustomerTransaction(_, _, amt) if amt > 100.0 => amt
  }.sum

  // Problem 3
  def flip2[A, B, C](f: (A, B) => C): (B, A) => C = (b: B, a: A) => f(a, b)

  // Write a generic function sampleList parameterized by type A, that returns the first 5 elements of a list of type A.
  def sampleList[A](list: List[A]): List[A] =
    list.take(5)

  def deferredExecutor[A, B](name: String)(f: A => B): A => B = ???
}
