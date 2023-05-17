package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Seq
  // A (very) general interface for data structures that
  //  - have a well defined order
  //  - can be indexed
  /*
  trait Seq[+A] {
    head: A
    tail: Seq[A]
  }
  */
  private val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2)) // retrieves value basically a get
  println(aSequence ++ Seq(5, 6, 7))
  println(aSequence.sorted)

  // Ranges
  private val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(_ => println("Hello"))

  // List
  /*
  sealed abstract class List[+A]
  case object Nil extends List[Nothing]
  case class ::[A](val hd: A, val tl: List[A]) extends List[A]
  */
  // A LinearSeq immutable linked list
  //  - head, tail, isEmpty methods are fast: O(1)
  //  - most operations are O(N): length, reverse
  private val aList = List(1, 2, 3)
  private val prepended = 42 :: aList // +:
  println(prepended)
  private val appended = aList :+ 42
  println(appended)

  private val apples5 = List.fill(5)("apple")
  println(apples5)
  println(aList.mkString("-|-"))

  // Array
  /*
  final class Array[T] extends
    java.io.Serializable
    with java.lang.Cloneable
   */
  // The equivalent of simple Java arrays
  //  - can be manually constructed with predefined lengths
  //  - can be mutated(updated in placed)
  //  - are interoperable with Java's T[] arrays
  //  - indexing is fast
  private val numbers = Array(1, 2, 3, 4)
  private val threeElements = Array.ofDim[Int](3)
  // println(threeElements)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq - connection
  private val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq)

  // Vector
  /*
  final class Vector[+A]
  */
  // The default implementation for immutable sequences
  //  - effectively constant indexed read and write: O(log32(N))
  //  - fast element addition: append/prepend
  //  - implemented as a fixed-branched trie (branch factor 32)
  //  - good performance for large sizes
  private val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // vectors vs lists

  private val maxRuns = 1000
  private val maxCapacity = 10000000

  private def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random()
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  private val numbersList = (1 to maxCapacity).toList
  private val numbersVector = (1 to maxCapacity).toVector

  // pros - keeps reference to tail
  // cons - updating an element in the middle takes long time
  println(getWriteTime(numbersList))
  // pros - depth of the tree is small
  // cons - needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector))
}
