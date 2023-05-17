package lectures.part3fp

import scala.annotation.tailrec

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

  /*
    1.  What would happen if I had two original "Jim" -> 555 and "JIM" -> 900?
        ran phonebook.map(pair => pair._1.toLowerCase -> pair._2)?
        !!! careful with mapping keys.
    2.  Overly simplified social network on maps
        Person = String
        - add a person to the network
        - remove
        - friend (mutual)
        - unfriend (mutual)
        stats
        - number of friends of a person
        - person with most friends
        - how many people have NO friends
        - if there is a social connection between two people (direct or not)
   */

  println(Map(("Jim", 555), ("JIM", 666)))
  // When mapping keys, make sure resulting keys don't Overlap
  println(
    Map(("Jim", 555), ("JIM", 666))
      .map(pair => pair._1.toLowerCase -> pair._2)
  )

  private def add(
                   network: Map[String, Set[String]],
                   person: String
                 ): Map[String, Set[String]] =
    network + (person -> Set())

  private def friend(
                      network: Map[String, Set[String]],
                      a: String,
                      b: String
                    ): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  private def unfriend(
                        network: Map[String, Set[String]],
                        a: String,
                        b: String
                      ): Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  private def remove(
                      network: Map[String, Set[String]],
                      person: String
                    ): Map[String, Set[String]] = {
    def removeAux(
                   friends: Set[String],
                   networkAcc: Map[String, Set[String]]
                 ): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person // removes person key
  }

  private val empty: Map[String, Set[String]] = Map()
  private val network = add(add(empty, "Bob"), "Mary")
  println("** Social Network **")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim,Bob,Mary
  private val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  private val jimBob = friend(people, "Bob", "Jim")
  private val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  private def nFriends(
                        network: Map[String, Set[String]],
                        person: String
                      ): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  private def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  private def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int =
  // network.view.filterKeys(k => network(k).isEmpty).toList.size
  // network.filter(pair => pair._2.isEmpty).size
  // network.count(pair => pair._2.isEmpty)
    network.count(_._2.isEmpty)

  println(nPeopleWithNoFriends(testNet))

  private def socialConnection(
                                network: Map[String, Set[String]],
                                a: String,
                                b: String
                              ): Boolean = {
    @tailrec
    def bfs(
             target: String,
             consideredPeople: Set[String],
             discoveredPeople: Set[String]
           ): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person))
          bfs(
            target,
            consideredPeople,
            discoveredPeople.tail
          )
        else
          bfs(
            target,
            consideredPeople + person,
            discoveredPeople.tail ++ network(person)
          )
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
