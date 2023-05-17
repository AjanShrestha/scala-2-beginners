package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  /*
  try {
    val config: Map[String, String] = loadConfig(path)
  } catch {
    case _: IOException => ??? // handle IOException
    case _: Exception => ??? // handle other Exception
  }
  */
  // - multiple/nested try's make the code hard to follow
  // - we can't chain multiple operations prone to failure

  // Try[T]
  // A Try is a wrapper for a computation that might fail or not
  /*
  sealed abstract class Try[+T]
  case class Failure[+T](t: Throwable) extends Try[T]
  case class Success[+T](value: T) extends Try[T]
  */

  // create success and failure - explicitly
  private val aSuccess = Success(3)
  private val aFailure = Failure(new RuntimeException(("SUPER FAILURE")))

  println(aSuccess)
  println(aFailure)

  private def unsafeMethod(): String =
    throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via the apply method
  private val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  private def backupMethod(): String = "A valid result"

  private val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
  println(fallbackTry)

  // IF you design the API
  // wrap computation in Try if you suspect it might throw exception
  private def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  private def betterBackupMethod(): Try[String] = Success("A valid result")

  private val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  println(betterFallback)

  // map,flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // for-comprehensions

  /*
    Exercise
    if you get the html page from the connection,
    print it to the console i.e. call renderHTML
   */
  private val host = "localhost"
  private val port = "8080"

  private def renderHTML(page: String) = println(page)

  private class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  private object HttpService {
    private val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }

    def getSafeConnection(host: String, port: String): Try[Connection] =
      Try(getConnection(host, port))
  }

  private val connection = Try {
    HttpService.getConnection(host, port)
  }
  private val html = connection.flatMap(c => Try {
    c.get("/home")
  })
  println("Without safe method")
  html.foreach(renderHTML)

  // When wrapped in Try
  private val possibleConnection = HttpService.getSafeConnection(host, port)
  private val possibleHTML =
    possibleConnection
      .flatMap(connection => connection.getSafe("/home"))
  println("With safe method")
  possibleHTML.foreach(renderHTML)

  // shorthand version
  println("shorthand version")
  HttpService.getSafeConnection(host, port)
    .flatMap(connection => connection.getSafe(".home"))
    .foreach(renderHTML)

  // for-comprehension version
  println("for-comprehension")
  for {
    connection <- HttpService.getSafeConnection(host, port)
    html <- connection.getSafe("/home")
  } renderHTML(html)

  // imperative
  /*
  try {
    val connection = HttpService.getConnection(host, port)
    try {
      val page =connection.get("/home")
      renderHTML(page)
    } catch (some other exception) {
    }
  } catch (exception) {
  }
  */
}
