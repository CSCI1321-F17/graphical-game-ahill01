

package graphicGame

import graphicGame.Level

import graphicGame.Entity

class Enemy(private var _x:Double, private var _y: Double, level:Level) extends Entity {
  level += this
 def cx: Double = _x
  def cy: Double = _y
  def width: Double = 2
  def height: Double = 2
  
  def update(dt:Double): Unit = {
    _y -=dt
    _x -=dt
  }
  def intersect(other: Entity): Boolean = {
    val intersectX = (cx - other.cx).abs < (width + other.width) / 2
    val intersectY = (cy - other.cy).abs < (height + other.height) / 2
    if (intersectX && intersectY) true else false
  }

 
 // def newBolt: Entity = {
//    while( new Bolt
//  }
}

object Enemy {
  
}