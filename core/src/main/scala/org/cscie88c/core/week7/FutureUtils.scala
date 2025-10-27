package org.cscie88c.core.week7

import scala.concurrent.{Future}
import scala.util.{Try, Success, Failure}
import scala.concurrent.ExecutionContext.Implicits.global
import java.util.concurrent.TimeoutException
import scala.util.Random
import scala.collection.parallel.CollectionConverters._

object FutureUtils {

  val rnd = new Random()

  def creditScoreAPI(applicantId: Int): Future[Int] = Future {
    if (Random.nextInt(5) == 0) throw new TimeoutException("Simulated timeout")
    300 + Random.nextInt(501)
  }

  def printCreditScore(applicantId: Int): Unit = {
    creditScoreAPI(applicantId).onComplete {
      case Success(score) =>
        println(s"The credit score for $applicantId is: $score")
      case Failure(_) =>
        println(s"Cannot get credit score for $applicantId at this time")
    }
  }

  def passedCreditCheck(applicantId: Int): Future[Boolean] = creditScoreAPI(applicantId)
    .map(_ > 600)
    .recover { case _ => false }

  def futureFactorial(n: Int): Future[Int] = Future {
    require(n >= 0, "n must be non-negative")
    @annotation.tailrec
    def loop(k: Int, acc: Int): Int =
      if (k <= 1) acc else loop(k - 1, acc * k)
    loop(n, 1)
  }

  def futurePermutations(n: Int, r: Int): Future[Int] = {
    require(n >= 0 && r >= 0 && r <= n, "Require 0 <= r <= n and n,r >= 0")
    for {
      fn  <- futureFactorial(n)
      fnr <- futureFactorial(n - r)
    } yield fn / fnr
  }

  def asyncAverageCreditScore(idList: List[Int]): Future[Double] = {
    val perId: List[Future[Option[Int]]] =
      idList.map { id =>
        retry(times = 5) { creditScoreAPI(id) }
          .map(score => Some(score))
          .recover { case _ => None }
      }

    Future.sequence(perId).map { results =>
      val scores = results.flatten
      require(scores.nonEmpty, "No successful credit scores to average")
      scores.sum.toDouble / scores.size
    }
  }

  def slowMultiplication(x: Long, y: Long): Long = {
    Thread.sleep(1000)
    x * y
  }

  def concurrentFactorial(n: Long): Long = {
    require(n >= 0, "n must be non-negative")
    if (n <= 1) 1L
    else (2L to n).par.fold(1L)(slowMultiplication)
    // Alternatively (requires non-empty range):
    // (2L to n).par.reduce(slowMultiplication)
  }

  def sequentialFactorial(n: Long): Long = {
    require(n >= 0, "n must be non-negative")
    if (n <= 1) 1L
    else (2L to n).foldLeft(1L) { (acc, k) => slowMultiplication(acc, k) }
  }

}
