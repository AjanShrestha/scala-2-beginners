package lectures.part2oop

object Exceptions extends App {
  val x: String = null
  //  println(x.length)
  // this ^^ will crash with a NPE

  // 1. throwing and catching exceptions
  //  val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class.
  // Exception and Error are the major Throwable subtypes
  // Difference in semantics
  // Exceptions denotes what went wrong with the program - NullPointerException
  // Errors denotes what went wrong with the system - Stackoverflow

  // 2. how to catch exceptions
  private def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  private val potentialFail = try {
    // code that might throw
    getInt(false)
  } catch {
    //    case e: RuntimeException => println("caught a Runtime exception")
    //    case e: NullPointerException => println("caught a Runtime exception")
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    // logging, close db connection
    println("finally")
  }

  // 3. how to define your own exceptions
  private class MyException extends Exception

  private val exception = new MyException

  //  throw exception

  println(potentialFail)

  /*
    1.  Crash your program with an OutOfMemoryError
    2.  Crash with SOError
    3.  PocketCalculator
        - add(x,y)
        - subtract(x,y)
        - multiple(x,y)
        - divide(x,y)

        Throw
          - OverflowException if add(x,y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x,y) exceed Int.MIN_VALUE
          - MathCalculationException for division by 0
   */

  // OutOfMemoryError - OOM
  // allocate more memory than JVM can allocate
  //  val array = Array.ofDim(Int.MaxValue)

  // StackOverflowError - SO
  def infinite: Int = 1 + infinite

  //  val noLimit = infinite

  private class OverflowException extends RuntimeException

  private class UnderflowException extends RuntimeException

  private class MathCalculationException extends RuntimeException("Division by 0")

  private object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int): Int = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int): Int = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculationException
      else x / y
  }

  //  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}
