package lectures.part2oop

object Inheritance extends App {

  // Single class Inheritance
  //  private final class Animal {
  private sealed class Animal {
    val creatureType: String = "wild"

    //    protected def eat: Unit = println("nom nom")

    //    final def eat: Unit = println("nom nom")
    def eat: Unit = println("nom nom")

  }

  private class Cat extends Animal {
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  private val cat = new Cat
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  // JVM rule requires to call parent constructor first
  // Scala compiler forces there is a correct super constructor
  // to call on derived class
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  // valid for auxiliary constructor
  //  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  private class Dog(override val creatureType: String = "domestic") extends Animal {
    //    override val creatureType: String = "domestic"

    //    override def eat = {
    //      super.eat
    //      println("crunch crunch")
    //    }
    override def eat = {
      super.eat
      println("crunch crunch")
    }
  }

  private val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  private val unknownAnimal: Animal = new Dog("K9")
  // method call goes to the most overridden version
  unknownAnimal.eat

  // overRIDING vs overLOADING
  // - supplying different implementation in derived classes
  // - supplying multiple methods with different signatures with same name in same class

  // super
  // to reference a field or method from parent class

  // preventing overrides
  // 1  - use final on member
  // 2  - use final on the entire class
  // 3  - seal the class - extend classes in THIS FILE,
  //      prevents extension in other files
  //      used to show exhaustive in type hierarchy
}
