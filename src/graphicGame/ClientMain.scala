package graphicGame
package graphicGame

import java.rmi.server.UnicastRemoteObject

@remote trait RemoteClient {
  def name: String
  
  def clientUpdate(clients: Seq[RemoteClient]):Unit
}

object ClientMain extends UnicastRemoteObject with RemoteClient {
  val server = Naming.lookup("GraphicGameServer")
}