package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck?"

    def isAlive: Boolean = true

    // special method
    def apply(): String =
      s"Hi, my name is $name and I like $favoriteMovie"

    // # Exercises
    def +(nickname: String): Person =
      new Person(s"$name ($nickname)", favoriteMovie)

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    private def learns(thing: String = "Scala"): String =
      s"$name is learning $thing"

    def learnsScala: String = this learns "Scala"

    def apply(n: Int): String =
      s"$name watched $favoriteMovie $n times"
  }

  private val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation (syntactic sugar)
  // works with one parameter only

  // "operators" in Scala
  private val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS.
  // Akka actors have ! ?

  // prefix notation
  // all about unary operators
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation
  // no parameters
  println(mary.isAlive) // preferred
  // import scala.language.postfixOps
  //  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  /*
  1.  Overload the + operator
      mary + "the rockstar" => new Person "Mary (the rockstar)"

  2.  Add an age to the Person class
      Add a unary + operator => new person with the age + 1
      +mary => mary with the age incrementer

  3.  Add a "learns" method in the Person class => "Mary learns Scala"
      Add a learnsScala method, calls learns method with "Scala".
      Use it in postfix notation.

  4.  Overload the apply method
      mary.apply(2) => "Mary watched Inception 2 times"
 */

  println((mary + "the rockstar")())
  println((+mary).age)
  // import scala.language.postfixOps
  //  println(mary learnsScala)
  println(mary.learnsScala)
  println(mary(2))
}