package lectures.part1basics

object StringOps extends App {
  private val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  private val aNumberString = "2"
  val aNumber = aNumberString.toInt
  // +: prepending
  // :+ appending
  println('a' +: aNumberString :+ 'z')
  println(str.reverse)
  println(str.take(2))

  // Scala-specific: String interpolator\s

  // S-interpolator
  // expand values and expressions
  val name = "David"
  val age = 12
  private val greeting = s"Hello, my name is $name and I am $age years old"
  private val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old"
  println(greeting)
  println(anotherGreeting)

  // F-interpolator
  // acts as S-interpolator
  // can also receive printf like formats
  private val speed = 1.2f
  private val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  println(myth)

  // raw-interpolator
  // same as s-interpolator
  // has property to print characters literally
  println(raw"This is a \n newline")
  private val escaped = "This is a \n newline"
  // injected variables do get escaped
  println(raw"$escaped")
}
