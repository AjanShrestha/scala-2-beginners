package lectures.part3fp

import scala.util.Random

object Options extends App {

  // An Option is a wrapper for a value that might be present or not.
  /*
  sealed abstract class Option[+A]
  case class Some[+A](x: A) extends Option[A]
  case object None extends Option[Nothing]
  */

  private val myFirstOption: Option[Int] = Some(4)
  private val noOption: Option[Int] = None

  println(myFirstOption)

  // WORK with unsafe APIs
  private def unsafeMethod(): String = null

  // val result = Some(unsafeMethod()) // WRONG
  // WRONG
  // Why?
  // Because it could be Some(null)
  // This would be incorrect
  // coz Some should always return a value
  // Instead use
  private val result = Option(unsafeMethod()) // Some or None
  println(result)

  // The whole point of Option is that
  // we should never do the null check
  // Option would do it for us

  // chained methods
  private def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod())
    .orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  private def betterUnsafeMethod(): Option[String] = None

  private def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()

  // Functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
    Exercise
    try to establish a connection
   */
  private val config: Map[String, String] = Map(
    // fetched from elsewhere
    // data may or may not be present
    "host" -> "174.126.5.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }

  // Someone else wrote this
  private object Connection {
    private val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection
  // if so - print the connect method

  // 1st Way
  private val host = config.get("host")
  private val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)
    return null
   */
  private val connection =
    host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  private val connectionStatus = connection.map(c => c.connect)
  /*
    if (connectionStatus == null)
      println(None)
    else
      println(Some(connectionStatus.get))
   */
  println(connectionStatus)
  /*
    if (status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // 2nd way
  // chained solution
  println("chained")
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  println("for-comprehensions")
  private val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
