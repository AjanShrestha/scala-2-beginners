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

  // higher-order functions
  // receive functions as parameters
  // return functions
  def map[B](transformer: A => B): MyList[B]

  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def filter(predicate: A => Boolean): MyList[A]

  // concatenation
  def ++[B >: A](list: MyList[B]): MyList[B]

  // HOFs
  def foreach(f: A => Unit): Unit

  def sort(compare: (A, A) => Int): MyList[A]

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]

  def fold[B](start: B)(operator: (B, A) => B): B
}


// Nothing is sub type of everything
case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException

  def tail: MyList[Nothing] = throw new NoSuchElementException

  def isEmpty: Boolean = true

  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)

  def printElements: String = ""

  def map[B](transformer: Nothing => B): MyList[B] = Empty

  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  // HOFs
  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h

  def tail: MyList[A] = t

  def isEmpty: Boolean = false

  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  /*
    [1,2,3].filter(n % 2 == 0)
      = [2,3].filter(n % 2 == 0)
      = new Cons(2, [3].filter(n % 2 == 0))
      = new Cons(2, Empty.filter(n % 2 == 0))
      = new Cons(2, Empty)
   */
  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  /*
    [1,2,3].map(n * 2)
      = new Cons(2, [2,3].map(n * 2))
      = new Cons(2, new Cons(4, [3].map(n * 2)))
      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
      = new Cons(2, new Cons(4, new Cons(6, Empty)))
   */
  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  /*
    [1,2] ++ [3,4,5]
      = new Cons(1, [2] ++ [3,4,5]
      = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
      = new Cons(1, new Cons(2, [3,4,5]))
      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
   */
  def ++[B >: A](list: MyList[B]): MyList[B] =
    new Cons(h, t ++ list)

  /*
    [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty
      = [1,2,2,3]
   */
  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  // HOFs
  /*
    [1,2,3].foreach(f)
      = f(1); [2,3].foreach(f)
      = f(2); [3].foreach(f)
      = f(3); Empty.foreach(f)
   */
  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {
    // Insertion sort
    def insert(x: A, sortedList: MyList[A]): MyList[A] = {
      if (sortedList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
      else new Cons(sortedList.head, insert(x, sortedList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] =
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))

  /*
    [1,2,3].fold(0)(+)
      = [2,3].fold(1)(+)
      = [3].fold(3)(+)
      = [].fold(6)(+)
      = 6
   */
  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
}

object ListTest extends App {
  //  val listOfIntegers: MyList[Int] = Empty
  //  val listOfStrings: MyList[String] = Empty
  private val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  private val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  private val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
  private val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

  println("toString")
  println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println("map")
  //  println(listOfIntegers.map((elem: Int) => elem * 2).toString)
  println(listOfIntegers.map(_ * 2).toString)

  println("filter")
  //  println(listOfIntegers.filter((elem: Int) => elem % 2 == 0).toString)
  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println("extend list")
  println((listOfIntegers ++ anotherListOfIntegers).toString)

  println("flatMap")
  println(listOfIntegers.flatMap((elem: Int) =>
    new Cons(elem, new Cons(elem + 1, Empty))).toString
  )

  println("list equality")
  println(cloneListOfIntegers == listOfIntegers)

  println("foreach")
  listOfIntegers.foreach(x => println(x))

  println("sort")
  println(listOfIntegers.sort((x, y) => y - x))

  println("zipWith")
  println(anotherListOfIntegers.zipWith[String, String](listOfStrings, _ + "-" + _))

  println("fold is also called reduce\nfold is one of the form of reduce")
  println(listOfIntegers.fold(0)(_ + _))
}


///*
//  1.  Generic trait MyPredicate[-T]
//      sub method that passes the condition for T
//      - with a little method test(T) => Boolean
//  2.  Generic trait MyTransformer[-A, B]
//      convert a value of type A to type B
//      - with a method transform(A) => B
//  3.  MyList:
//      - map(transformer) -> MyList
//      - filter(predicate) -> MyList
//      - flatMap(transformer from A to MyList[B]) => MyList[B]
//
//      class EvenPredicate extends MyPredicate[Int]
//      class StringToIntTransformer extends MyTransformer[String, Int]
//
//      [1,2,3].map(n * 2) = [2,4,6]
//      [1,2,3,4].filter(n % 2) = [2,4]
//      [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
// */
//
//trait MyPredicate[-T] {
//  def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A, B] {
//  def transform(elem: A): B
//}
//
//
//abstract class MyList[+A] {
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
//  def head: A
//
//  def tail: MyList[A]
//
//  def isEmpty: Boolean
//
//  def add[B >: A](element: B): MyList[B]
//
//  def printElements: String
//
//  // polymorphic call
//  override def toString: String = "[" + printElements + "]"
//
//  def map[B](transformer: MyTransformer[A, B]): MyList[B]
//
//  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
//
//  def filter(predicate: MyPredicate[A]): MyList[A]
//
//  def ++[B >: A](list: MyList[B]): MyList[B]
//}
//
//
//// Nothing is sub type of everything
//case object Empty extends MyList[Nothing] {
//  def head: Nothing = throw new NoSuchElementException
//
//  def tail: MyList[Nothing] = throw new NoSuchElementException
//
//  def isEmpty: Boolean = true
//
//  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
//
//  def printElements: String = ""
//
//  def map[B](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
//
//  def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[B] = Empty
//
//  def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
//
//  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
//}
//
//case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
//  def head: A = h
//
//  def tail: MyList[A] = t
//
//  def isEmpty: Boolean = false
//
//  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
//
//  def printElements: String =
//    if (t.isEmpty) "" + h
//    else h + " " + t.printElements
//
//  /*
//    [1,2,3].filter(n % 2 == 0)
//      = [2,3].filter(n % 2 == 0)
//      = new Cons(2, [3].filter(n % 2 == 0))
//      = new Cons(2, Empty.filter(n % 2 == 0))
//      = new Cons(2, Empty)
//   */
//  def filter(predicate: MyPredicate[A]): MyList[A] =
//    if (predicate.test(h)) new Cons(h, t.filter(predicate))
//    else t.filter(predicate)
//
//  /*
//    [1,2,3].map(n * 2)
//      = new Cons(2, [2,3].map(n * 2))
//      = new Cons(2, new Cons(4, [3].map(n * 2)))
//      = new Cons(2, new Cons(4, new Cons(6, Empty.map(n * 2))))
//      = new Cons(2, new Cons(4, new Cons(6, Empty)))
//   */
//  def map[B](transformer: MyTransformer[A, B]): MyList[B] =
//    new Cons(transformer.transform(h), t.map(transformer))
//
//  /*
//    [1,2] ++ [3,4,5]
//      = new Cons(1, [2] ++ [3,4,5]
//      = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
//      = new Cons(1, new Cons(2, [3,4,5]))
//      = new Cons(1, new Cons(2, new Cons(3, new Cons(4, new Cons(5, Empty)))))
//   */
//  def ++[B >: A](list: MyList[B]): MyList[B] =
//    new Cons(h, t ++ list)
//
//  /*
//    [1,2].flatMap(n => [n, n+1])
//      = [1,2] ++ [2].flatMap(n => [n, n+1])
//      = [1,2] ++ [2,3] ++ Empty.flatMap(n => [n, n+1])
//      = [1,2] ++ [2,3] ++ Empty
//      = [1,2,2,3]
//   */
//  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] =
//    transformer.transform(h) ++ t.flatMap(transformer)
//}
//
//object ListTest extends App {
//  //  val listOfIntegers: MyList[Int] = Empty
//  //  val listOfStrings: MyList[String] = Empty
//  private val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  private val cloneListOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  private val anotherListOfIntegers: MyList[Int] = new Cons(4, new Cons(5, Empty))
//  private val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
//
//  println(listOfIntegers.toString)
//  println(listOfStrings.toString)
//
//  println(listOfIntegers.map(new MyTransformer[Int, Int] {
//    override def transform(elem: Int): Int = elem * 2
//  })).toString
//
//  println(listOfIntegers.filter(new MyPredicate[Int] {
//    override def test(elem: Int): Boolean = elem % 2 == 0
//  })).toString
//
//  println(listOfIntegers ++ anotherListOfIntegers).toString
//
//  println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
//    override def transform(elem: Int): MyList[Int] =
//      new Cons(elem, new Cons(elem + 1, Empty))
//  })).toString
//
//  println(cloneListOfIntegers == listOfIntegers)
//}

// Generic Single Linked List
//abstract class MyList[+A] {
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
//  def head: A
//
//  def tail: MyList[A]
//
//  def isEmpty: Boolean
//
//  def add[B >: A](element: B): MyList[B]
//
//  def printElements: String
//
//  // polymorphic call
//  override def toString: String = "[" + printElements + "]"
//}
//
//
//// Nothing is sub type of everything
//object Empty extends MyList[Nothing] {
//  def head: Nothing = throw new NoSuchElementException
//
//  def tail: MyList[Nothing] = throw new NoSuchElementException
//
//  def isEmpty: Boolean = true
//
//  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
//
//  def printElements: String = ""
//}
//
//class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
//  def head: A = h
//
//  def tail: MyList[A] = t
//
//  def isEmpty: Boolean = false
//
//  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
//
//  def printElements: String =
//    if (t.isEmpty) "" + h
//    else h + " " + t.printElements
//}
//
//object ListTest extends App {
//  //  val listOfIntegers: MyList[Int] = Empty
//  //  val listOfStrings: MyList[String] = Empty
//  private val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
//  private val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))
//
//  println(listOfIntegers.toString)
//  println(listOfStrings.toString)
//}

// Singe Linked List for Int
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