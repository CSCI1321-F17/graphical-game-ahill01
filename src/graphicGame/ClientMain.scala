package graphicGame

import java.rmi.Naming
import java.rmi.server.UnicastRemoteObject

import scalafx.application.JFXApp
import scalafx.scene.control.TextInputDialog

@remote trait RemoteClient {
  def name: String
  
  //def clientUpdate(clients: Seq[RemoteClient]):Unit = {
  
 // }
}

object ClientMain extends UnicastRemoteObject with JFXApp with RemoteClient {
  val dialog = new TextInputDialog("localhost") 
  dialog.title = "ServerMachine"
  dialog.contentText = "What machine is hosting the server?"
  dialog.headerText = "Server Name"
  val server = dialog.showAndWait() match {
    case Some(machine) => val server = Naming.lookup(s"rmi://$machine/GraphicGameServer") 
      case _ => {
        println("cannot connect")
        sys.exit(0)
      }
      case None => sys.exit(0)
    }
}
 // def clientUpdate = {???}
  
  
  