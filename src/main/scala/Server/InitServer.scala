package Server

import java.io.InputStreamReader
import java.net.{ServerSocket, Socket}
import Utils._

import java.util.concurrent.ConcurrentHashMap
import scala.collection.JavaConverters.mapAsScalaConcurrentMapConverter
import scala.collection.mutable.ListBuffer

object InitServer extends App {
  //initial list

  val port = 6000
  val userList = new ConcurrentHashMap[String, Socket]().asScala //new mutable.HashSet[Int] with mutable.SynchronizedSet[Int]new ListBuffer[UserWorker]()

  val serverSocket = new ServerSocket(port)

  println("Started to listen")
  while(true) {
    val clientSocket = serverSocket.accept() //blocking call, server is listening for messages
    val userWorker = UserWorker(clientSocket)
  }
  serverSocket.close()
}