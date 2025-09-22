package org.cscie88c.core.week2

object UtilFunctions {

  // complete the functions below
  def maximum(a: Int, b: Int): Int = 
    if (a > b)
      a
    else
      b
  
  def median(dataset: List[Double]): Double = {
    if (dataset.isEmpty) {
      
    }
    val sortedNumbers = dataset.sorted
    val size = sortedNumbers.size

    if (size % 2 == 1) {
      // Odd number of elements, median is the middle element
      sortedNumbers(size / 2)
    } else {
      // Even number of elements, median is the average of the two middle elements
      val middle1 = sortedNumbers(size / 2 - 1)
      val middle2 = sortedNumbers(size / 2)
      (middle1 + middle2) / 2.0
    }
  }

}
