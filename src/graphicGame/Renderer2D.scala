
package graphicGame
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image



/**
 * This is a 2D renderer that with draw your game elements to a Canvas. You should change the
 * images to fit the style of your game. Also, alter the entities to match what you have in
 * your game.
 */
class Renderer2D(gc: GraphicsContext, blockSize: Double) {
  private var lastCenterX = 0.0
  private var lastCenterY = 0.0

  // Put variables for images here
  private val floorImage = new Image("file:ocean.jpg")
  private val wallImage = new Image("file:reefborder.png")
  private val playerImage = new Image("file:pythagaras.png")
  private val enemyImage = new Image("file:eel.png")
  private val trashImage = new Image("file:trash.png")
  private val bulletImage = new Image("file:lightning.png")

  /**
   * These two methods are used to figure out where to draw things. They are used by the render.
   */
  def blocksToPixelsX(bx: Double): Double = gc.canvas.getWidth / 2 + (bx - lastCenterX) * blockSize
  def blocksToPixelsY(by: Double): Double = gc.canvas.getHeight / 2 + (by - lastCenterY) * blockSize

  /**
   * These two methods are used to go from coordinates to blocks. You need them if you have mouse interactions.
   */
  def pixelsToBlocksX(px: Double): Double = (px - gc.canvas.getWidth / 2) / blockSize + lastCenterX
  def pixelsToBlocksY(py: Double): Double = (py - gc.canvas.getHeight / 2) / blockSize + lastCenterY

  /**
   * This method is called to render things to the screen.
   */
  def render(level: PassableLevel, cx: Double, cy: Double): Unit = {
     println("Renderer"+level)
    lastCenterX = cx
    lastCenterY = cy

    val drawWidth = (gc.canvas.getWidth / blockSize).toInt + 1
    val drawHeight = (gc.canvas.getWidth / blockSize).toInt + 1

    // Draw walls and floors
    for {
      x <- cx.toInt - drawWidth / 2 - 1 to cx.toInt + drawWidth / 2 + 1
      y <- cy.toInt - drawHeight / 2 - 1 to cy.toInt + drawHeight / 2 + 1
    } {
      val img = if (level.maze(x, y)) {
        wallImage
      } else {
        floorImage
      }
      gc.drawImage(img, blocksToPixelsX(x), blocksToPixelsY(y), blockSize, blockSize)
    }

    // Draw entities
    for (e <- level.entities) {
      val img = e.style match {
        case 2 => playerImage
        case 1  => enemyImage
        case 4 => bulletImage
        case 3 => trashImage
        // case g: Generator => generatorImage
      }

      if (level.maze.wrap) {
        for (rx <- -1 to 1; ry <- -1 to 1)
          gc.drawImage(img, blocksToPixelsX(e.cx - e.width / 2 + rx * level.maze.width), blocksToPixelsY(e.cy - e.height / 2 + ry * level.maze.height), e.width * blockSize, e.height * blockSize)
      } else {
        println(e.style, e.cy)
        gc.drawImage(img, blocksToPixelsX(e.cx - e.width / 2), blocksToPixelsY(e.cy - e.height / 2), e.width * blockSize, e.height * blockSize)
      }
    }
  }
}