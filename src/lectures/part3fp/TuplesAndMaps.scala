package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  private val aTuple = (2, "Hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)

  // Maps= keys => values
  private val aMap: Map[String, Int] = Map()

  private val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook("Jim"))
  println(phonebook("Mary")) // raises error may want to guard with default value

  // add a pairing
  private val newPairing = "Mary" -> 678
  private val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // Functionals on maps
  // map, flatMap, filter
  // takes pairing
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterkeys
  println(phonebook.view.filterKeys(x => x.startsWith("J")).toMap)

  // mapValues
  println(phonebook.view.mapValues(number => "0245-" + number).toMap)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  private val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
