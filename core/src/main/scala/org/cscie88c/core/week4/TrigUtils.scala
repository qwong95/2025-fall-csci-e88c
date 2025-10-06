package org.cscie88c.core.week4

object TrigUtils {

  // https://www.scala-lang.org/api/2.13.16/scala/math/index.html
  // use the function literal syntax for sin and cos
  val sinDegrees: Double => Double = deg => math.sin(math.toRadians(deg))
  val cosDegrees: Double => Double = deg => math.cos(math.toRadians(deg))

  // use the placeholder syntax for squared
  val squared: Double => Double = math.pow(_, 2)
  
}
