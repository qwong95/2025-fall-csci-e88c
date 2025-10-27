package org.cscie88c.core.week7

import scala.io.Source
import scala.util.{Try, Success, Failure}

object OptionUtils {
  
  def fileCharCount(fileName: String): Try[Long] = Try {
    val src = Source.fromResource(fileName, getClass.getClassLoader)
    try {
      src.mkString.length.toLong
    } finally {
      src.close()
    }
  }

  def charCountAsString(fileName: String): String = fileCharCount(fileName) match {
    case scala.util.Success(n) => s"number of characters: $n"
    case scala.util.Failure(_) => "error opening file"
  }

  def lineStreamFromFile(fileName: String): Option[LazyList[String]] = {
    Try {
      val src = Source.fromResource(fileName, getClass.getClassLoader)
      try {
        LazyList.from(src.getLines().toVector)
      } finally {
        src.close()
      }
    }.toOption
  }
}
