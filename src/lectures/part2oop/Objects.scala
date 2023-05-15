package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static")
  // it has even better - object

  // objects can be defined like classes
  // objects do not receive parameters
  object Person { // type + its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory methods that build Person
    //    def from(mother: Person, father: Person): Person =
    //      new Person("Bobbie")
    def apply(mother: Person, father: Person): Person =
      new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS
  // same scope and name

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object - SINGLETON INSTANCE
  private val person1 = Person
  private val person2 = Person
  println(person1 == person2)

  private val mary = new Person("Mary")
  private val john = new Person("John")
  println(mary == john)
  // Singleton Object - single instance that can referred to with the name Person

  //  val bobbie = Person.from(mary, john)
  //  val bobbie = Person.apply(mary, john)
  val bobbie = Person(mary, john) // looks like a constructor

  // Scala Applications - Scala object with
  // def main(args: Array[String]): Unit
}
