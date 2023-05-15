package lectures.part2oop

object AbstractDataTypes extends App {
  // abstract
  abstract class Animal {
    val creatureType: String = "wild"

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch")
  }

  // traits - ultimate abstract data type in Scala
  private trait Carnivore {
    def eat(animal: Animal): Unit

    val preferredMeal: String = "fresh meat"
  }

  private trait ColdBlooded

  // trait can be inherited along classes
  private class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"

    def eat: Unit = println("nom nom nom")

    def eat(animal: Animal): Unit =
      println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  private val dog = new Dog
  private val croc = new Crocodile
  croc.eat(dog)

  // trait vs abstract classes
  // both can have abstract and non-abstract members
  // 1  - traits do not have constructor parameters
  // 2  - multiple traits may be inherited by the same class
  // 3  - traits are(=) behavior(what they do), abstract class are(=) "thing"
}
