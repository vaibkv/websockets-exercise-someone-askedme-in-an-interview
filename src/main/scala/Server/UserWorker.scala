package Server

import java.net.Socket
import scala.concurrent.Future
import Utils._
import InitServer._

import scala.concurrent.ExecutionContext.Implicits.global

case class UserWorker(socket: Socket) {

  var userName = ""
  var isLoggedIn = false
  //list - her maitaining groups you are on

  Future {
    handleConnection(socket)
  }

  private def handleConnection(socket: Socket): Unit = {
    var msg = ""
    val iStream = getInputStream(socket)
    val oStream = getOutputStream(socket)

    while({msg = iStream.readLine(); msg} != ":quit") {
      val tokens = msg.split("\\s+")
      val cmd = tokens.head

      cmd match {
        case ":login" => handleLogin(tokens)
        case ":msg" => handleDirectMessages(tokens, msg)
        case ":broadcast" => handleBroadcast(tokens, msg)
        case wrongCommand => oStream.println(s"Hey! that's not even a command: $wrongCommand")
      }
    }
  }

  private def handleBroadcast(tokens: Array[String], msg: String): Unit = {
    if (tokens != null && tokens.length >= 1) {
      val actualMsg = msg.split("\\s+", 2).last

      userList.filter(kvPair => !kvPair._1.equalsIgnoreCase(this.userName)).foreach(kv => {
        getOutputStream(kv._2).println(s"Message from $userName: $actualMsg")
      })
    }
  }

  private def handleLogin(tokens: Array[String]): Unit = {
    if (tokens != null && tokens.length >= 2) {
      val username = tokens(1)
      this.userName = username
      this.isLoggedIn = true

      //update central list
      InitServer.userList.update(username, socket)
    } else {
      throw new Exception("you don't know how to login!")
    }
  }

  private def handleDirectMessages(tokens: Array[String], msg: String): Unit = {
    if(tokens != null && tokens.length >= 2) {
      val actualMsg = msg.split("\\s+", 3).last
      val sendTo = tokens(1)

      val optTargetSocket = InitServer.userList.get(sendTo)
      if(optTargetSocket.isDefined) {
        val targetOutputStream = getOutputStream(optTargetSocket.get)
        targetOutputStream.println(s"$actualMsg : msg from ${this.userName}")
      }
    }
  }
}

//https://serverip:port/login?params=user&pad=
//ws starts out as http -- upgrade
//wss starts out as https -- upgrade