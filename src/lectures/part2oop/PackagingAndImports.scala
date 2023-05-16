package lectures.part2oop

import playground.{Cinderella => Princess, PrinceCharming}

import java.util.Date
import java.sql.{Date => SqlDate}

// package = a group of definitions under the same name
object PackagingAndImports extends App {
  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2023)

  // import the package
  //  val princess = new Cinderella
  //  val princess = new Cinderella // playground.Cinderella = fully qualified name
  val princess = new Princess // aliasing

  // packages are in hierarchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming
  // 1. use Fully Qualified (FQ) names

  val date = new Date
  val sqlDate = new SqlDate(2023, 5, 12)
  // 2. use aliasing

  // default imports
  // java.lang - String, Objects, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
