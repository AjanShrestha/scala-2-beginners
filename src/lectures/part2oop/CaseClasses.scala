package lectures.part2oop

object CaseClasses extends App {

  /*
    equals, hashCode, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  private val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // println(instance) == println(instance.toString) // syntactic sugar
  println(jim)

  // 3. equals and hashCode implemented out of the box(OOTB)
  private val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. CCs have handy copy method
  private val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. CCs have companion objects8
  val thePerson = Person
  val mary = Person("Mary", 23)
  println(mary)

  // 6. CCs are serializable
  // specially useful when dealing with distributed system
  // can send instances of case classes through the network
  // and in between JVMs
  // Example: Akka framework => sends messages(case classes)

  // 7. CCs have extractor patterns = CCs can be used in PATTERN MATCHING

  // case objects acts like a case class
  // they are their own companion objects
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
    Expand MyList - use case classes and case objects
   */
}
