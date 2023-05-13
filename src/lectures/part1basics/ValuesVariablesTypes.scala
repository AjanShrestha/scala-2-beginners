package lectures.part1basics

object ValuesVariablesTypes extends App {
  // creating value
  private val x = 42
  println(x)

  //  x = 2;
  // VALs ARE IMMUTABLE
  // once set they cannot be updated

  // COMPILER can infer types

  val aString: String = "hello"
  val anotherString = "goodbye"

  val aBoolean: Boolean = true // false
  val aChar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 4613
  // Short is Int with 1/2 the representation size
  // 2 bytes
  val aLong: Long = 1249712648342525L
  // Long is Int with double the representation size
  // 8 bytes
  // end the number with "L" to represent long
  val aFloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  private var aVariable: Int = 4

  aVariable = 5 // side effects
}
