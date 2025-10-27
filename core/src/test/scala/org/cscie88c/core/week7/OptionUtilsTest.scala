package org.cscie88c.core.week7

import org.cscie88c.core.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}

class OptionUtilsTest extends StandardTest {
  import OptionUtils._

  private val existingFile = "data/dirty-retail-data-sample.csv"
  private val missingFile = "data/dirty-retail-data-sample.csv-x"

  "OptionUtils" when {
    "calling fileCharCount" should {
      "return the correct number of characters in a valid file" in {
        fileCharCount(existingFile) shouldBe Success(195L)
      }
    }

    "calling charCountAsString" should {
      "return the correct count string for the existing file" in {
        charCountAsString(existingFile) shouldBe "number of characters: 195"
      }

      "return the error message for a missing file" in {
        charCountAsString(missingFile) shouldBe "error opening file"
      }
    }
  }

}
