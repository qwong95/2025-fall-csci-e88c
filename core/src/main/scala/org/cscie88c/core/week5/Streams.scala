package org.cscie88c.core.week5

import java.util.UUID
import scala.util.Random

object Streams {
  val rnd = new Random()
  def uuid: String = UUID.randomUUID.toString.replaceAll("-", "")

  case class Dog(name: String, age: Int, hasCurrentShots: Boolean)

  val mult5: LazyList[Int] = LazyList.range(0, 101, 5)

  val randIntStream: LazyList[Int] = LazyList.continually(Random.nextInt(16))

  val dogs: LazyList[Dog] = {
    val r = new Random() // separate RNG instance
    LazyList.continually(
      Dog(
        name = s"dog-${java.util.UUID.randomUUID()}",
        age = r.nextInt(16),
        hasCurrentShots = r.nextBoolean()
      )
    )
  }

  def healthyDogs(dogs: LazyList[Dog]): LazyList[Dog] = dogs.filter(_.hasCurrentShots)

  def averageHealthyAge(allDogs: LazyList[Dog], sampleSize: Int): Double = {
    val healthyAges =
      allDogs.take(sampleSize).collect { case Dog(_, age, true) => age }
    if (healthyAges.isEmpty) 0.0
    else healthyAges.sum.toDouble / healthyAges.size
  }
}
