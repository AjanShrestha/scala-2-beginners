package lectures.part3fp

object HOFsCurries extends App {

  val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
  // higher order function (HOF)

  // map, flatMap, filter in MyList

  // function that applies a function n times over a value x
  // nTimes(f, n, x)
  // nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  // nTimes(f, n, x) - f(f(...(x))) = nTimes(f, n-1, f(x))
  private def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  private val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // nTimesBetter(f,n) = x => f(f(f...(x)))
  // increment10 = nTimesBetter(plusOne, 10) = x => plusOne(plusOne...(x))
  // val y = increment10(1)
  private def nTimesBetter(f: Int => Int, n: Int): (Int => Int) =
    if (n <= 0) (x: Int) => x
    else (x: Int) => nTimesBetter(f, n - 1)(f(x))

  private val plus10 = nTimesBetter(plusOne, 10)
  println(plus10(1))

  // curried functions
  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  private val add3 = superAdder(3) // y => 3 + y
  println(add3(10))
  println(superAdder(3)(10))

  // functions with multiple parameter lists
  private def curriedFormatter(c: String)(x: Double): String = c.format(x)

  private val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  private val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}
