package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // RHS: EXPRESSION
  // Expression are evaluated to a value
  // and have a type
  println(x)

  println(2 + 3 * 4)
  // Arithmetic operator
  //  + - * / & | ^ << >> >>>(right shift with zero extension)

  println(1 == x)
  // Relational operator
  // == != > >= < <=

  println(!(1 == x))
  // Logical operator
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= .... side effects
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE AND/OR TYPE)
  // Instructions are executed(think Java)
  // Expressions are evaluated(Scala)

  // IF expression
  private val aCondition = true
  private val aConditionValue = if (aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionValue)
  println(if (aCondition) 5 else 3) // EXPRESSION evaluates to value

  // LOOP/ITERATION
  // DISCOURAGE IN SCALA
  // Very Specific to imperative programming
  // Doesn't return anything
  // Introduces side-effects
  private var i = 0
  val aWhile = while (i < 10) {
    println(i)
    i += 1
  }
  // NEVER WRITE THIS AGAIN.

  // EVERYTHING in Scala is an Expression!

  private val aWeirdValue = (aVariable = 3) // Type -> Unit === void
  println(aWeirdValue) // ()
  // side-effects in Scala are expressions returning Unit
  // side effects: println(), whiles, reassigning

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  // Code blocks is an expression
  // the value of the block is the value of its last expression
  // Code block introduces closure

  // # Exercises
  // 1. difference between "hello world" vs println("hello world")?
  // value of type String => String Literal
  // side-effect returning Unit => prints string
  // 2. What are the value of?
  private val someValue = {
    2 < 3
  }
  // the value is of type Boolean
  // res: Boolean = true

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  // the value is
  // res: Int = 42
}
