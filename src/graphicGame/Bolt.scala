package graphicGame
/*
 * Bolts are made by the Evil Eels, reduce your health points if they hit you
 */

class Bolt(private var _x: Double, private var _y: Double, level: Level, spawnEel:Enemy) extends Entity {
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
this.hitSomething
  }
 override def move(dx: Double, dy: Double): Unit = {
   if (level.maze.isClear(cx+dx, cy+dy, width, height)) {
      _x += dx
      _y += dy
    } else alive == false
   }
  
 /*
   * runs through list of entities, checks if it overlaps with any of them, then sets hit = true
   */
 def hitSomething():Boolean = {
   for (p <- this.level.players) {
    if(this.intersect(p)) hit |= true
  }
 hit
 }
 
 /*
  * @param: Entity
  * @return boolean, true if intersecting false if not
  */
 def intersect(other: Entity): Boolean = {
    val intersectX = (cx - other.cx).abs < (width + other.width) / 2
    val intersectY = (cy - other.cy).abs < (height + other.height) / 2
    if (intersectX && intersectY) true else false
  }
  
def getStyle:Int = 4

/*
 * Bolts dissappear when they hit another entity or have been around for at least 3 time units
 */
def alive:Boolean = this.hitSomething == false && counter < 3.0

def damageplayer:Boolean = true
}

object Bolt {
 
}