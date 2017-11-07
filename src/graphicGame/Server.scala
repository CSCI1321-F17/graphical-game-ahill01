package graphicGame

package graphicGame

import java.rmi.server.UnicastRemoteObject
import java.rmi.Naming
import java.rmi.registry.LocateRegistry
import scala.collection.mutable


@remote trait RemoteServer {
  def connectPlayer(client:RemoteClient):Unit
  def disconnect(client:RemoteClient):Unit
  def updateLevel(clients:Seq[RemoteClient]):Unit
  }

object Server extends UnicastRemoteObject with RemoteServer {
  LocateRegistry.createRegistry(1099)
  Naming.rebind("GraphicGameServer", this)
  
  private val clients = mutable.Buffer[RemoteClient]()
  
  def connectPlayer(client:RemoteClient):Unit = {
    clients += client
  }
  def disconnect(client:RemoteClient):Unit = {
    clients -= client
  }
  def addClients(clients:Seq[RemoteClient]):Unit = {
     clients.foreach()
    for (i <- clients.length) {
        level += clients(i) 
   } 
  }
} 