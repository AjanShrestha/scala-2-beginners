package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int =
    if (n <= 1) 1
    else {
      println(s"Computing factorial of $n - I first need factorial of ${n - 1}")
      val result = n * factorial(n - 1)
      println(s"Computed factorial of $n")
      result
    }

  // JVM keeps call stack of functions
  println(factorial(10))
  // Issue is Stackoverflow
  // i.e. when the number of call stack increases it quickly
  // consumes memory and JVM runs out of memory
  //  println(factorial(9000))

  private def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else factHelper(x - 1, x * accumulator) // TAIL RECURSION
      // - use recursive call as the LAST expression
      // recursive func as the last expression of code block
      // uses the same stack frame instead of creating new one
      // intermediate value is calculated immediately
    }

    factHelper(n, 1)
  }

  /*
    anotherFactorial(10) = factHelper(10, 1)
    = factHelper(9, 10 * 1)
    = factHelper(8, 9 * 10 * 1)
    = factHelper(7, 8 * 9 * 10 * 1)
    = factHelper(6, 7 * 8* 9 * 10 * 1)
    = ...
    = factHelper(2, 3 * 4 * ... * 10 * 1)
    = factHelper(1, 1 * 2 * 3 * 4 * ... * 10)
    = 1 * 2 * 3 * 4 * ... * 10
   */

  println(anotherFactorial(500))
  println(anotherFactorial(5000))

  // WHEN YOU NEED LOOPS< USE _TAIL_ RECURSION.

  /*
    1.  Concatenate a string n times
    2.  IsPrime function tail recursive
    3.  Fibonacci function, tail recursive
   */

  @tailrec
  private def concatenateTailrec(
                                  aString: String,
                                  n: Int,
                                  accumulator: String
                                ): String =
    if (n <= 0) accumulator
    else concatenateTailrec(aString, n - 1, aString + accumulator)

  println(concatenateTailrec("hello", 3, ""))

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeTailRec(t: Int, isStillPrime: Boolean): Boolean = {
      if (t <= 1) true
      else if (!isStillPrime) false
      else isPrimeTailRec(t - 1, n % t != 0 && isStillPrime)
    }

    isPrimeTailRec(n / 2, isStillPrime = true)
  }

  @tailrec
  private def verifyPrime(n: Int): Unit = {
    if (n == 1) return
    if (isPrime(n)) println(s"$n is prime")
    else println(s"$n is composite")
    verifyPrime(n - 1)
  }

  verifyPrime(37)
  println(s"2003 is prime? -> ${isPrime(2003)}")
  println(s"37 * 17 is prime? -> ${isPrime(37 * 17)}")

  def fibonacci(n: Int): Int = {
    @tailrec
    // TAIL Recursion requires n accumulators
    // for the number of recursive calls
    def fibTailrec(i: Int, last: Int, nextToLast: Int): Int =
      if (i >= n) last
      else fibTailrec(i + 1, last + nextToLast, last)

    if (n <= 2) 1
    else fibTailrec(2, 1, 1)
  }

  @tailrec
  private def printFibonacci(n: Int): Unit = {
    if (n < 1) return
    println(s"fib($n) = ${fibonacci(n)}")
    printFibonacci(n - 1)
  }

  // 1 1 2 3 5 8 13 21
  printFibonacci(8)
}
