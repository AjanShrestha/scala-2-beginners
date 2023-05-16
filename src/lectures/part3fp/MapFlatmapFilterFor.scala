package lectures.part3fp

object MapFlatmapFilterFor extends App {
  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println("map")
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println("filter")
  println(list.filter(_ % 2 == 0))

  // flatMap
  println("flatMap")
  private val toPair = (x: Int) => List(x, x + 1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  private val numbers = List(1, 2, 3, 4)
  private val chars = List('a', 'b', 'c', 'd')
  private val colors = List("black", "white")
  // List("a1","a2",...,"d4")

  // "iterating"
  private val combinations =
    numbers.flatMap(n =>
      chars.map(c => "" + c + n))
  private val combinations2 =
    numbers.filter(_ % 2 == 0)
      .flatMap(n =>
        chars.flatMap(c =>
          colors.map(color => "" + c + n + "-" + color)))
  println("combinations : flatMap => map")
  println(combinations)
  println("combinations : flatMap => flatMap => map")
  println(combinations2)

  // foreach
  list.foreach(println)

  // for-comprehensions
  private val forCombinations = for {
    n <- numbers if n % 2 == 0 // guard
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println("for-comprehensions")
  println(forCombinations)

  // side-effect
  for {
    n <- list
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }
}
