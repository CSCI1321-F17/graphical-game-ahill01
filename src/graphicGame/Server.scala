package graphicGame

import java.rmi.server.UnicastRemoteObject
import java.rmi.Naming
import java.rmi.registry.LocateRegistry
import scala.collection.mutable
import scala.collection.mutable
import java.rmi.RemoteException

@remote trait RemoteServer {
  def connectPlayer(client:RemoteClient):Unit
  def disconnect(client:RemoteClient):Unit
  def buildLevel(level:Level):Unit
}

object Server extends UnicastRemoteObject with RemoteServer with App {
  LocateRegistry.createRegistry(1099)
  Naming.rebind("GraphicGameServer", this)
  
  private val clients = mutable.Buffer[RemoteClient]()
  
  /*
   * connects player to server, adds them to the maze
   */
  def connectPlayer(client:RemoteClient):Unit = {
    clients += client
  
  }
  /*
   * @param Client
   * removes client from the list of clients if they disconnect or have gone inactive
   */
  def disconnect(client:RemoteClient):Unit = {
    clients -= client
  }
 /*
  * 
 
  def sendLevel(c:RemoteClient, level:Level):Unit = {
    buildLevel(level)
    clients.foreach{c => 
    try { c.updateLevel(clients) }
    catch {
      case ex: RemoteException => "Couldn't update level"
      }  
    }
  }
   */
  /*
   * @param Level
   * converts level to a PassableLevel
   */
  def buildLevel(level:Level):Unit = {
    val pEntities = level.entities.map(n => new PassableEntity(n.cx,n.cy,n.width,n.height))
    val pLevel = new PassableLevel(level.maze, pEntities)
    
  }
 
 }