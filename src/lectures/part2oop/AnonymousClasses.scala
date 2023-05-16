package lectures.part2oop

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  private val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahaha")
  }
  /*
    equivalent with

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("hahahahaha")
    }
    val funnyAnimal = new AnonymousClasses$$anon$1

    compiler does this behind the scene
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  // should pass parameters on Anonymous classes
  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }

  // Anonymous classes work for abstract as well as normal classes
}
