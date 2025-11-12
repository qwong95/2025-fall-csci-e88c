package org.cscie88c.core.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

final case class CustomerTransaction(
  customerId: String,
  transactionDate: String,
  transactionAmount: Double
)

object CustomerTransaction {
  def apply(csvString: String): Option[CustomerTransaction] = {
    val parts = csvString.split(",", -1).map(_.trim)
    if (parts.length != 3) None
    else {
      val Array(id, date, amountStr) = parts
      if (id.isEmpty || date.isEmpty) None
      else Try(amountStr.toDouble).toOption.map { amt =>
        CustomerTransaction(id, date, amt)
      }
    }
  }

  def readFromCSVFile(fileName: String): List[CustomerTransaction] = {
    val src = Source.fromResource(fileName, getClass.getClassLoader)
    try {
      src.getLines().flatMap(line => CustomerTransaction(line)).toList
    } finally {
      src.close()
    }
  }
}