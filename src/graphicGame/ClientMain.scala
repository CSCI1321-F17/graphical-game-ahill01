package graphicGame

import java.rmi.server.UnicastRemoteObject
import java.rmi.Naming
import scalafx.scene.control.TextInputDialog
import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes.jfxKeyEvent2sfx
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyEvent
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.animation.AnimationTimer
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode

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
  
  
  