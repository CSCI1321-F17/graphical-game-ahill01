package graphicGame


class Bolt(private var _x: Double, private var _y: Double, level: Level, vx: Double, vy: Double) extends Entity {
  def cx: Double = _x
  def cy: Double = _y
  def width: Double = 2
  def height: Double = 2
  def update(dt: Double): Unit = {
    _y -= dt
    _x -= dt
  }
 def move(dx: Double, dy: Double): Unit = {???}
  // def shock: Unit = {
  // if (Bolt.intersect(Player)) {
  //  Player.score-1
  //}
  //}

  

}

object Bolt {
 
}