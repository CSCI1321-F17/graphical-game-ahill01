package graphicGame

import java.rmi.server.UnicastRemoteObject
import java.rmi.Naming
import java.rmi.registry.LocateRegistry
import scala.collection.mutable
import scala.collection.mutable
import java.rmi.RemoteException

@remote trait RemoteServer {
  def connectPlayer(client: RemoteClient): RemotePlayer
  def disconnect(client: RemoteClient): Unit
}

object Server extends UnicastRemoteObject with RemoteServer with App {
  LocateRegistry.createRegistry(1099)
  Naming.rebind("GraphicGameServer", this)

  private val clients = mutable.Buffer[RemoteClient]()
  val maze = Maze(6, false, 20, 20, 0.6)
  val level1 = new Level(maze, Nil)
  /*
   * connects player to server, adds them to the maze
   */
  def connectPlayer(client: RemoteClient): RemotePlayer = {
    clients += client
    new Player(15, 15, 2, 2, level1)
  }
  /*
   * @param Client
   * removes client from the list of clients if they disconnect or have gone inactive
   */
  def disconnect(client: RemoteClient): Unit = {
    clients -= client
  }

  def sendLevel(plevel: PassableLevel): Unit = {
   // println("sending")
    clients.foreach { c =>
    //  println(c)
      try { c.updateLevel(plevel) }
      catch {
        case ex: RemoteException => ex.printStackTrace
      }
    }
  }

  var lastTime = 0L

  while (true) {
    val time = System.nanoTime
    if (lastTime > 0) {
      val dt = (time - lastTime) * 1e-9
      level1.updateAll(dt)
      sendLevel(level1.buildLevel())
    }
    lastTime = time

  }
}