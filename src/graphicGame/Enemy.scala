

package graphicGame


class Enemy(private var _x:Double, private var _y: Double, level:Level) extends Entity {
  level += this
 def cx: Double = _x
  def cy: Double = _y
  def width: Double = 2
  def height: Double = 2
  val speed = 2
  
  
  def update(dt:Double): Unit = {
   val p = level.players.head
   val up = level.bfs(cx.toInt, (cy-1).toInt, p.cx.toInt, p.cy.toInt)
   val down = level.bfs(cx.toInt, (cy+1).toInt, (p.cx).toInt, (p.cy).toInt)
   val left = level.bfs((cx +1).toInt, cy.toInt, p.cx.toInt, p.cy.toInt)
   val right = level.bfs((cx-1).toInt, cy.toInt, p.cx.toInt, p.cy.toInt)
   if(up <= down && up <= left && up <= right) move(0,-dt*speed) 
   else if(down <= left && down <= right && down <= up) move(0,dt*speed) 
   else if (left <= right && left <= up && left <= down) move(dt*speed,0)
   else move(-dt*speed,0)
  }
  
  def intersect(other: Entity): Boolean = {
    val intersectX = (cx - other.cx).abs < (width + other.width) / 2
    val intersectY = (cy - other.cy).abs < (height + other.height) / 2
    if (intersectX && intersectY) true else false
  }

  override def move(dx: Double, dy: Double): Unit = {
    if (level.maze.isClear(cx+dx, cy+dy, width, height)) {
      _x += dx
      _y += dy
    }
  }
  def shock():Unit = {
    
  }
}

object Enemy {
  
}