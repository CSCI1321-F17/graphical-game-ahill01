package graphicGame
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
  
  def clientUpdate(clients: Seq[RemoteClient]):Unit = {
  
  }
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
  def clientUpdate = {???}
  
  
  stage = new JFXApp.PrimaryStage {
    title = "Graphic Game"

    scene = new Scene(800, 600) {

      val canvas = new Canvas(800, 600)

      content = canvas

      val gc = canvas.graphicsContext2D

 
      val renderer = new Renderer2D(gc, 20)
      val maze = Maze(6, false, 20, 20, 0.6)
      val level1 = new Level(maze, Nil)
      val player = new Player(15,15, 2, 2, level1)
      val enemy = new Enemy(20, 20, level1)

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

      // Used for smooth motion
      var lastTime = 0L

      val timer = AnimationTimer(time => {
        renderer.render(level1, player.cx, player.cy)

        // Code for doing smooth motion
        if (lastTime > 0) {
          val dt = (time - lastTime) * 1e-9
          level1.updateAll(dt)
        }
        lastTime = time

      })
      timer.start()
    }
  }
  }
  