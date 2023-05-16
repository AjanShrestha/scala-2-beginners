package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
      A = Cat
      B = Dog = Animal
      MyList[Animal]
     */
  }

  class MyMap[Key, Value]

  // trait can be type parameterized as well
  trait MyNewList[B]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  // generic objects
  // objects cannot be type parameterized
  object MyList {
    // generic method
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal

  class Cat extends Animal

  class Dog extends Animal

  // 1. YES, List[Cat] extends List[Animal] => COVARIANCE
  class CovariantList[+A]

  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION. => we return a list of Animal
  // would pollute the animal list which is list of cat

  // 2. NO = INVARIANCE
  // each on its on world
  // cannot substitute for one another
  class InvariantList[A]

  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class ContravariantList[-A]

  // not natural; not intuitive
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A]

  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  // allow to use generic classes only for
  // - subclass of a different type
  // - superclass of a different type

  // upper bounded type
  // class Cage only accepts type params A which are subtypes of Animal
  class Cage[A <: Animal](animal: A)

  val cage = new Cage(new Dog)

  //  class Car
  //  val newCage = new Cage(new Car)

  // lower bounded type
  // class Cage only accepts type params A which are supertypes of Animal
  //  class Cage[A >: Animal](animal: A)

  // bounded type solve Covariance problem
}
