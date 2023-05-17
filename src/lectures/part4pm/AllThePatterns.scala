package lectures.part4pm

import exercises.{Cons, Empty, MyList}

object AllThePatterns extends App {

  //  // 1 - constants
  //  private val x: Any = "Scala"
  //  private val constants = x match {
  //    case 1 => "a number"
  //    case "Scala" => "THE Scala"
  //    case true => "The Truth"
  //    case AllThePatterns => "A singleton object"
  //  }
  //
  //  // 2 - match anything
  //  // 2.1 wildcard
  //  private val matchAnything = x match {
  //    case _ => "wildcard"
  //  }
  //
  //  // 2.2 variable
  //  private val matchAVariable = x match {
  //    case something => s"I've found $something"
  //  }
  //
  //  // 3 - tuples
  //  private val aTuple = (1, 2)
  //  private val matchATuple = aTuple match {
  //    case (1, 1) => "Literal match"
  //    case (something, 2) => s"I've found $something"
  //  }
  //
  //  private val nestedTuple = (1, (2, 3))
  //  private val matchANestedTuple = nestedTuple match {
  //    case (_, (2, v)) => s"I've found $v"
  //  }
  //  // PMs can be NESTED!
  //
  //  // 4 - case classes - constructor pattern
  //  // PMs can be nested with CCs as well
  //  private val aList: MyList[Int] = Cons(1, Cons(2, Empty))
  //  private val matchAList = aList match {
  //    case Empty => "Empty"
  //    case Cons(head, tail) => s"${head}, ${tail}"
  //    case Cons(head, Cons(subHead, subTail)) => s"${head}, ${subHead}, ${subTail}"
  //  }
  //
  //  // 5 - list patterns
  //  private val aStandardList = List(1, 2, 3, 42)
  //  private val standardListMatching = aStandardList match {
  //    case List(1, _, _, _) => "extractor pattern" // extractor - advanced
  //    case List(1, _*) => "var arg pattern" // list of arbitrary length - advanced
  //    case 1 :: List(_) => "infix pattern" // infix pattern
  //    case List(1, 2, 3) :+ 42 => "infix pattern"
  //  }
  //
  //  // 6 - type specifiers
  //  private val unknown: Any = 2
  //  private val unknownMatch = unknown match {
  //    case list: List[Int] => "explicit type specifier" // explicit type specifier
  //    case _ => "matches all"
  //  }
  //
  //  // 7 - name binding
  //  private val nameBindingMatch = aList match {
  //    case nonEmptyList@Cons(_, _) => s"${nonEmptyList.t}" // name binding => use the name later(here)
  //    case Cons(1, rest@Cons(2, _)) => s"${rest.h}" // name binding inside nested patterns
  //  }
  //
  //  // 8 - multi-patterns
  //  private val multiPattern = aList match {
  //    case Empty | Cons(0, _) => "" // compound/multi pattern
  //  }
  //
  //  // 9 - if guards
  //  private val secondElementSpecial = aList match {
  //    case Cons(_, Cons(specialElement, _)) if specialElement % 2 == 0 => ""
  //  }

  /*
    Question.
   */

  // Type eraser
  private val numbers = List(1, 2, 3)
  private val numbersMatch = numbers match {
    case listOfStrings: List[String] => "a list of strings" // List
    case listOfNumbers: List[Int] => "a list of numbers" // List
    case _ => ""
  }

  println(numbersMatch)
  // JVM trick question
  // JVM was designed for JAVA
  // also it was designed with backward compatibility in mind
  // JAVA 1 didn't have Generics
  // Java 5 introduced Generics
  // So to make it compatible, Java 5 compiler
  // removed generics type after type checking


}
