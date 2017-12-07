package graphicGame

class Trash(private var _x: Double, private var _y: Double, level: Level) extends Entity {
  /*
   * Pollution of the ocean is a global problem
   * just like in real life this piece of trash does not break down anytime soon
   * floats around with random motion
   * and getting tangled up for it is not good for your health (automatically kills you)
   */
  level += this
  def cx: Double = _x
  def cy: Double = _y
  def width: Double = 2
  def height: Double = 2
  val speed = 2
  val dx = math.random()
  val dy = math.random()
  def damageplayer:Boolean = true
  def update(dt: Double): Unit = move(dx * dt, dy* dt)

  override def move(dx: Double, dy: Double): Unit = {
    if (level.maze.isClear(cx + dx, cy + dy, width, height)) {
      _x += dx
      _y += dy
    }
  }
  
  def intersect(other: Entity): Boolean = {
    val intersectX = (cx - other.cx).abs < (width + other.width) / 2
    val intersectY = (cy - other.cy).abs < (height + other.height) / 2
    if (intersectX && intersectY) true else false
  }

  def getStyle: Int = 3

  def alive: Boolean = true
}