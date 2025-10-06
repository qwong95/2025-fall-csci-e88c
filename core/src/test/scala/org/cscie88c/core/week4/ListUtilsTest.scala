package org.cscie88c.core.week4

import org.cscie88c.core.testutils.{StandardTest}

class ListUtilsTest extends StandardTest {
  "ListUtils" when {
    "calling ones" should {
      "return the correct value" in {
        // write unit test here
        ListUtils.ones(0) shouldBe Nil
        ListUtils.ones(1) shouldBe List(1.0)
        val L = ListUtils.ones(5)
        L.length shouldBe 5
        L.forall(_ == 1.0) shouldBe true
      }
    }

    // write unit tests for zeroes, and other functions here
    "calling zeros" should {
      "return all 0.0s for various sizes" in {
        ListUtils.zeros(0) shouldBe Nil
        ListUtils.zeros(3) shouldBe List(0.0, 0.0, 0.0)
        val L = ListUtils.zeros(6)
        L.length shouldBe 6
        L.forall(_ == 0.0) shouldBe true
      }
    }

    "calling character counts" should {
      "count characters for 'Hello world' ignoring spaces" in {
        val counts = ListUtils.charCounts("Hello world")
        counts('H') shouldBe 1
        counts('e') shouldBe 1
        counts('l') shouldBe 3
        counts('o') shouldBe 2
        counts('w') shouldBe 1
        counts('r') shouldBe 1
        counts('d') shouldBe 1
        counts.contains(' ') shouldBe false
      }
      
      "show that 'The quick brown fox jumps over the lazy dog' is a pangram" in {
        val sent   = "The quick brown fox jumps over the lazy dog"
        val counts = ListUtils.charCounts(sent)
        val keysLower = counts.keySet.map(_.toLower)
        ('a' to 'z').forall(keysLower.contains) shouldBe true
      }
    }

    "calling top N" should {
      "return the top N characters as a Map" in {
        val freqs = Map('e' -> 1, 'l' -> 3, 'H' -> 1, 'w' -> 1, 'r' -> 1, 'o' -> 2, 'd' -> 1)
        val top2 = ListUtils.topN(2)(freqs)
        top2 shouldBe Map('l' -> 3, 'o' -> 2)
      }
      
      "handle N larger than the map size" in {
        val freqs = Map('a' -> 2, 'b' -> 1)
        val result = ListUtils.topN(10)(freqs)
        result shouldBe freqs
      }
    }
  }
}
