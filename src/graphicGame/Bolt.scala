package graphicGame
/*
 * Bolts are made by the Evil Eels, reduce your health points if they hit you
 */

class Bolt(private var _x: Double, private var _y: Double, level: Level) extends Entity {
 level += this
 var hit = false 
 var counter = 0.0
  def cx: Double = _x
  def cy: Double = _y
  def width: Double = 2
  def height: Double = 2
  def update(dt: Double): Unit = {
   _y -= dt
   _x -= dt
  counter += dt
  }
 override def move(dx: Double, dy: Double): Unit = {
   if (level.maze.isClear(cx+dx, cy+dy, width, height)) {
      _x += dx
      _y += dy
    } else alive == false
   }
  
 def hitSomething():Boolean = {
   for (i <- 0 until this.level.entities.length) {
    if(this.intersect(this.level.entities(i))) hit |= true
  }
 hit
 }
 
  def intersect(other: Entity): Boolean = {
    val intersectX = (cx - other.cx).abs < (width + other.width) / 2
    val intersectY = (cy - other.cy).abs < (height + other.height) / 2
    if (intersectX && intersectY) true else false
  }
  
def getStyle:Int = 4
/*
 * Bolts dissappear when they hit another entity 
 */
def alive:Boolean = if((counter < 3.0)|| this.hitSomething == false) true else false

}

object Bolt {
 
}