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
import scalafx.Includes._
import scalafx.event.ActionEvent
import java.rmi.RemoteException
import scalafx.collections.ObservableBuffer
import scalafx.application.Platform

@remote trait RemoteClient {
  def updateLevel(pLevel: PassableLevel): Unit
  //  def clientUpdate(clients: Seq[RemoteClient]):Unit  
}

object ClientMain extends UnicastRemoteObject with JFXApp with RemoteClient {
  val dialog = new TextInputDialog("localhost")
  dialog.title = "Server Machine"
  dialog.contentText = "What server do you want to connect to?"
  dialog.headerText = "Server Name"
  val server = dialog.showAndWait() match {
    case Some(machine) =>
      Naming.lookup(s"rmi://$machine/GraphicGameServer") match {
        case server: RemoteServer => server

        case _ =>
          println("There were problems.")
          sys.exit(0)
      }
    case None => sys.exit(0)
  }
  val canvas = new Canvas(800, 600)
  val gc = canvas.graphicsContext2D
  val renderer = new Renderer2D(gc, 20)
  val player = server.connectPlayer(this)

  stage = new JFXApp.PrimaryStage {
    title = "Graphic Game"

    scene = new Scene(800, 600) {

      content = canvas

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up    => player.upPressed
          case KeyCode.Down  => player.downPressed
          case KeyCode.Left  => player.leftPressed
          case KeyCode.Right => player.rightPressed
          case _             =>
        }
      }

      //new Level
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up    => player.upReleased
          case KeyCode.Down  => player.downReleased
          case KeyCode.Left  => player.leftReleased
          case KeyCode.Right => player.rightReleased
          case _             =>
        }
      }

    }
  }

  def updateLevel(level: PassableLevel): Unit = {
    if(renderer != null) {Platform.runLater { renderer.render(level, player.cx, player.cy) }}
  }

}