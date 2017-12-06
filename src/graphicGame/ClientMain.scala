package graphicGame

import java.rmi.Naming
import java.rmi.server.UnicastRemoteObject

import scalafx.application.JFXApp
import scalafx.scene.control.TextInputDialog
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
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.TextArea
import scalafx.scene.control.Label
import scalafx.scene.paint.Color

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
    val scoreboard = new Label("Health Score: " + player.score)
    scoreboard.layoutX = 20
    scoreboard.layoutY = 20
    scoreboard.textFill = Color.White
    scene = new Scene(800, 600) {
      content = List(canvas, scoreboard)

      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => player.upPressed
          case KeyCode.Down => player.downPressed
          case KeyCode.Left => player.leftPressed
          case KeyCode.Right => player.rightPressed
          case _ =>
        }
      }

      //new Level
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => player.upReleased
          case KeyCode.Down => player.downReleased
          case KeyCode.Left => player.leftReleased
          case KeyCode.Right => player.rightReleased
          case _ =>
        }
      }

    }
  }

  def updateLevel(level: PassableLevel): Unit = {
   
    Platform.runLater {
      renderer.render(level, player.cx, player.cy)
    }
  }

}
