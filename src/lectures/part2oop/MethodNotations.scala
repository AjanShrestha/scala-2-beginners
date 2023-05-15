package lectures.part2oop

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def +(person: Person): String =
      s"${this.name} is hanging out with ${person.name}"

    def unary_! : String = s"$name, what the heck?"

    def isAlive: Boolean = true

    // special method
    def apply(): String =
      s"Hi, my name is $name and I like $favoriteMovie"
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
  print(mary()) // equivalent
}
