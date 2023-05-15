package lectures.part2oop

object OOBasics extends App {
  // instantiation
  // concrete realizations in memory that conform
  // to the data structure that class describes
  private val person = new Person("John", 26)
  println(person.x)
  println(person.age)
  person.greet("Daniel")

  private val author = new Writer("Charles", "Dickens", 1812)
  private val imposter = new Writer("Charles", "Dickens", 1812)
  private val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))

  private val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print
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

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

   Novel: name, year of release, author
    - authorAge
    - isWrittenBy(author)
    -copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val year: Int) {
  def fullname: String = s"$firstName $surname"
}

class Novel(name: String, val year: Int, author: Writer) {
  def authorAge: Int = year - author.year

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYear: Int): Novel =
    new Novel(name, newYear, author)
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
 */

class Counter(val count: Int = 0) {

  def inc: Counter = {
    println("incrementing")
    new Counter(count + 1) // immutability
  }

  def dec: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n - 1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n - 1)
  }

  def print: Unit = println(count)
}