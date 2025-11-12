package org.cscie88c.core.week7

import org.cscie88c.core.testutils.{ StandardTest }
import scala.util.{Try, Success, Failure}

class CustomerTransactionTest extends StandardTest {
  "CustomerTransaction" should {
    "load and clean raw CSV data file" in {
      // add unit tests below
      val in  = "id1,customer-A,84.5"
      val out = CustomerTransaction.apply(in)
      out shouldBe Some(CustomerTransaction("id1", "customer-A", 84.5))

      val inSpace  = "  id1  ,  customer-A  ,   84.5  "
      val outSpace = CustomerTransaction.apply(in)
      outSpace shouldBe Some(CustomerTransaction("id1", "customer-A", 84.5))
    }

    "return None for invalid amount" in {
      val in = "id1,customer-A,84.5x"
      CustomerTransaction.apply(in) shouldBe None
    }

    "return None for wrong number of fields" in {
      CustomerTransaction.apply("only-two,fields") shouldBe None
    }

    "return None when required fields are empty" in {
      CustomerTransaction.apply(",customer-A,10.0") shouldBe None
      CustomerTransaction.apply("id1,,10.0")        shouldBe None
    }

  }

  "CustomerTransaction.readFromCSVFile" should {
    "read 5 valid records from the dirty sample file" in {
      val file = "data/dirty-retail-data-sample.csv"
      val rows = CustomerTransaction.readFromCSVFile(file)
      rows.length shouldBe 5
    }
  }
}
