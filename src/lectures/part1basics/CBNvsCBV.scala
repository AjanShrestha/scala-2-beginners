package lectures.part1basics

object CBNvsCBV extends App {

  private def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    //    println("by value: " + 369712073887250L)
    println("by value: " + x)
    //    println("by value: " + 369712073887250L)
  }

  // => called by name
  // delays the expression passed in as parameter until it's used
  // computed everytime it is called
  // useful in Lazy streams and
  // things that might fail - Try type
  private def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    //    println("by name: " + System.nanoTime())
    println("by name: " + x)
    //    println("by name: " + System.nanoTime())
  }

  // called by value
  // the exact value of expression is computed
  // before the function evaluates
  // same value is used throughout the function definition
  calledByValue(System.nanoTime())
  // called by name
  // the expression is passed literally as it is
  // expression is evaluated everytime in the function definition
  calledByName(System.nanoTime())

  private def infinite(): Int = 1 + infinite()

  private def printFirst(x: Int, y: => Int): Unit = println(x)

  //  printFirst(infinite(), 34)
  printFirst(34, infinite())

  /*
    Call by value:
      - value is computed before call
      - same value used everywhere

    Call by name:
      - expression is passed literally
      - expression is evaluated at every use within
   */
}
