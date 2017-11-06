package graphicGame

package graphicGame

import java.rmi.server.UnicastRemoteObject
import java.rmi.Naming
import java.rmi.registry.LocateRegistry


@remote trait RemoteServer {
  def connectPlayer(client:RemoteClient):Unit
  def disconnect(client:RemoteClient):Unit
  def updateLevel(level:Level):Unit
  }

object Server extends UnicastRemoteObject with RemoteServer {
  LocateRegistry.createRegistry(1099)
  Naming.rebind("GraphicGameServer", this)
  
  private val clients = new Buffer[RemoteClient]()
  
  def connectPlayer(client:RemoteClient):Unit = {
    clients += client
  }
  def disconnect(client:RemoteClient):Unit = {
    
  }
  def updateLevel(level:Level):Unit = {
  
  }
} 