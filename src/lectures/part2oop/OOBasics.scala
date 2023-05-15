package lectures.part2oop

object OOBasics extends App {
  // instantiation
  // concrete realizations in memory that conform
  // to the data structure that class describes
  private val person = new Person("John", 26)
  println(person.x)
  println(person.age)
  person.greet("Daniel")
}

// Class organizes data and behavior i.e. code
// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2 // fields

  println(1 + 3) // executed when instantiating

  // method
  def greet(name: String): Unit =
    println(s"${this.name} says: Hi, $name")

  // overloading
  // same function name but different signature
  // different parameter number/type
  def greet(): Unit = println(s"Hi, I am $name") // this is implied

  // multiple constructors
  // auxiliary constructor can only have/call another constructor
  def this(name: String) = this(name, 0)
}

// class parameters are NOT FIELDS
// add(requires) val/var to class parameters to convert to field