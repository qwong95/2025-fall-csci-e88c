package org.cscie88c.core.week3

object UtilFunctions {
  
  def mult2(x: Int, y: Int): Int = x * y
  
  def pythTest(x: Int, y: Int, z: Int): Boolean = x * x + y * y == z * z

  def pythTriplesUpto100: List[(Int, Int, Int)] = (for {
      x <- 1 to 100
      y <- x to 100
      z <- y to 100
      if x * x + y * y == z * z
    } yield (x, y, z)).toList
}
