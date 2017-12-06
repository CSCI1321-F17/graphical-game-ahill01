package graphicGame

import java.rmi.server.UnicastRemoteObject

class Player(private var _x: Double, private var _y: Double, val width: Double, val height: Double, level: Level) extends UnicastRemoteObject with Entity with RemotePlayer {
  level += this

  private var up = false
  private var down = false
  private var left = false
  private var right = false
  private var s = false
  var score = 10
  def getStyle: Int = 2

  def cx = _x
  def cy = _y
  val speed = 3
  def upPressed = up = true; println("up")
  def downPressed = down = true
  def leftPressed = left = true
  def rightPressed = right = true
  def sPressed = s = true
  def upReleased = up = false
  def downReleased = down = false
  def leftReleased = left = false
  def rightReleased = right = false
  def sReleased = s = false
  /*
 * @param: current x or y location
 * @return: 
 */
  def update(dt: Double): Unit = {
    if (up) { move(0, -dt * speed); println("moving up" + cy) }
    if (down) move(0, dt * speed)
    if (left) move(-dt * speed, 0)
    if (right) move(dt * speed, 0)

    //if clear, move otherwise nah
    //if using smooth motion -> (something * delay) -> speed constant -> delay 

  }

  override def move(dx: Double, dy: Double): Unit = {
    if (level.maze.isClear(cx + dx, cy + dy, width, height)) {
      _x += dx
      _y += dy
    }
  }
  def alive: Boolean = true

  def intersect(other: Entity): Boolean = {
    val intersectX = (cx - other.cx).abs < (width + other.width) / 2
    val intersectY = (cy - other.cy).abs < (height + other.height) / 2
    if (intersectX && intersectY) true else false
  }

  def minusPoints(e: Entity): Int = {
    if (intersect(e)) this.score - 1
    score
  }
  
}
object Player {

}