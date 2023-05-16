package exercises

abstract class MyList[+A] {

  /*
    Singly Linked List
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: A

  def tail: MyList[A]

  def isEmpty: Boolean

  def add[B >: A](element: B): MyList[B]

  def printElements: String

  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}


// Nothing is sub type of everything
object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""
}

class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest extends App {
  //  val listOfIntegers: MyList[Int] = Empty
  //  val listOfStrings: MyList[String] = Empty
  private val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  private val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println(listOfIntegers.toString)
  println(listOfStrings.toString)
}

//abstract class MyList {
//
//  /*
//    Singly Linked List
//    head = first element of the list
//    tail = remainder of the list
//    isEmpty = is this list empty
//    add(int) => new list with this element added
//    toString => a string representation of the list
//   */
//
//  def head: Int
//
//  def tail: MyList
//
//  def isEmpty: Boolean
//
//  def add(element: Int): MyList
//
//  def printElements: String
//
//  // polymorphic call
//  override def toString: String = "[" + printElements + "]"
//}
//
//object Empty extends MyList {
//  def head: Int = throw new NoSuchElementException
//
//  def tail: MyList = throw new NoSuchElementException
//
//  def isEmpty: Boolean = true
//
//  def add(element: Int): MyList = new Cons(element, Empty)
//
//  def printElements: String = ""
//}
//
//class Cons(h: Int, t: MyList) extends MyList {
//  def head: Int = h
//
//  def tail: MyList = t
//
//  def isEmpty: Boolean = false
//
//  def add(element: Int): MyList = new Cons(element, this)
//
//  def printElements: String =
//    if (t.isEmpty) "" + h
//    else h + " " + t.printElements
//}
//
//object ListTest extends App {
//  private val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  println(list.head)
//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.isEmpty)
//  println(list.toString)
//}