package Server

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.Socket

object Utils {

  def getInputStream(socket: Socket): BufferedReader = {
    new BufferedReader(new InputStreamReader(socket.getInputStream))
  }

  def getOutputStream(socket: Socket): PrintStream = {
    new PrintStream(socket.getOutputStream)
  }
}
