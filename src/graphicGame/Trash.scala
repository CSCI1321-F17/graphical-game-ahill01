package graphicGame

class Trash (private var _x:Double, private var _y: Double, level:Level) extends Entity {
  /*
   * trash just floats around and takes away health points if it touches you 
   */
  level += this
  def cx: Double = _x
  def cy: Double = _y
  def width: Double = 2
  def height: Double = 2
  val speed = 2
  val dx = math.random()
  val dy = math.random()
  def update(dt:Double):Unit = move(math.random()*dt, math.random()*dt)
  override def move(dx: Double, dy: Double): Unit = {
    if (level.maze.isClear(cx+dx, cy+dy, width, height)) {
      _x += dx
      _y += dy
    }
}
}