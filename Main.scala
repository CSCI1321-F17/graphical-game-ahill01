package graphicGame

import scalafx.Includes.eventClosureWrapperWithParam
import scalafx.Includes.jfxKeyEvent2sfx
import scalafx.application.JFXApp
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

object Main extends JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Graphic Game"
   
   scene = new Scene(800, 600) {
      
      val canvas = new Canvas(800, 600)
      
      content = canvas
      
      val gc = canvas.graphicsContext2D
      
      val renderer = new Renderer2D(gc, 10) 
      renderer 
     
      onKeyPressed = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => Level.player.upPressed
          case KeyCode.Down => Level.player.downPressed
          case KeyCode.Left => Level.player.leftPressed
          case KeyCode.Right => Level.player.rightPressed
          case _ =>
        }
      }
     val maze = new Maze (2,false,Array(Array(1,5,7,9)))
     val Level1 = new Level(maze, Level.entities)
   
      //new Level
      onKeyReleased = (ke: KeyEvent) => {
        ke.code match {
          case KeyCode.Up => Level.player.upReleased
          case KeyCode.Down =>Level.player.downReleased
          case KeyCode.Left => Level.player.leftReleased
          case KeyCode.Right => Level.player.rightReleased
          case _ =>
        }
      }
      
     //code for smooth motion
     // var lastTime = 0L
       // if(lastTime > 0) {
         // val dt = (time-lastTime)*1e-9
        // Level.updateAll(dt)
        //}
        //lastTime = time
    //}
      //timer.start()
    //}
    }
  }
}