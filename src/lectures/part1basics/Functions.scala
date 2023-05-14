package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App {

  private def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  private def aParameterlessFunction(): Int = 42

  println(aParameterlessFunction())
  println(aParameterlessFunction)

  private def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n - 1)
  }

  println(aRepeatedFunction("hello", 3))

  // WHEN YOU NEED LOOPS, USE RECURSION.
  // A compiler cannot infer return type of recursive function.

  def aFunctionWithSideEffects(aString: String): Unit =
    println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n - 1)
    // Code block to define auxiliary functions
  }

  // # Exercises
  println("** Exercises **")
  /*
    1.  A greeting function (name, age) => "Hi, my name is $name and I am $age year old"
    2.  Factorial function 1 * 2 * 3 * ... * n
    3.  A Fibonacci function
        f(1) = 1
        f(2) = 1
        f(n) = f(n - 1) + f(n - 2)
    4.  Tests if a number is prime.
   */

  private def greetingForKids(name: String, age: Int): String =
    "Hi, my name is " + name + " and I am " + age + " years old"

  println(greetingForKids("Mia", 12))

  private def factorial(n: Int): Int = {
    if (n <= 0) return 1
    n * factorial(n - 1)
  }


  private def printFactorial(n: Int): Unit = {
    if (n < 1) return
    println(s"factorial($n) = ${factorial(n)}")
    printFactorial(n - 1)
  }

  printFactorial(5)

  private def fibonacci(n: Int): Int = {
    //    def innerFunc(a: Int, b: Int, n: Int): Int = {
    //      if (n <= 1) return a + b
    //      innerFunc(b, a + b, n - 1)
    //    }
    //
    //    innerFunc(1, 1, n)
    if (n <= 2) return 1
    fibonacci(n - 1) + fibonacci(n - 2)
  }

  private def printFibonacci(n: Int): Unit = {
    if (n < 1) return
    println(s"fib($n) = ${fibonacci(n)}")
    printFibonacci(n - 1)
  }

  // 1 1 2 3 5 8 13 21
  printFibonacci(8)

  private def isPrime(n: Int): Boolean = {
    //    if (n == 2 || n == 3) return true
    //    if (n % 2 == 0) return false
    //
    //    def isPrimeUntil(t: Int): Boolean = {
    //      if (n % t == 0) return false
    //      if (t < 3) return true
    //      isPrimeUntil(t - 1)
    //    }
    def isPrimeUntil(t: Int): Boolean =
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t - 1)

    isPrimeUntil(n / 2)
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
}
