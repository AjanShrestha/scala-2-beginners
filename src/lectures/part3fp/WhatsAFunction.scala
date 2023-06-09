package lectures.part3fp

object WhatsAFunction extends App {
  // DREAM: use functions as first class elements
  // problem: oop
  private val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function1[A, B]
  // supported 1 to 22 parameters
  private val stringToIntConverter = new Function[String, Int] {
    override def apply(string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: (Int, Int) => Int = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A,B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS
  // JVM was built with OOP in mind only
  // first-class elements: objects(instance of classes
  // Scala truly Functional language via syntactic features

  /*
    1.  a function which takes 2 string and concatenates them
    2.  transform the MyPredicate and MyTransformer into function type
    3.  define a function which takes an int and returns another
        function which takes an int and returns an int
        - what's the type of this function
        - how to do it
   */

  private val concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  println(concatenator("Hello, ", "Scala"))

  // Function1[Int, Function1[Int, Int]]
  private val superAdder: Function1[Int, Function1[Int, Int]] =
    new Function1[Int, Function1[Int, Int]] {
      override def apply(x: Int): Int => Int = new Function[Int, Int] {
        override def apply(y: Int): Int = x + y
      }
    }

  private val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function
}

trait MyFunction[A, B] {
  def apply(element: A): B
}