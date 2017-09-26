

package graphicGame

trait Entity {
  def cx: Double
  def cy: Double
  def width: Double
  def height: Double
  
  
}
object Entity {
  
  //def intersect(other:Entity): Boolean = {
 //   val intersectX = (cx - other.cx).abs < (width + other.width) / 2
   // val intersectY = (cy - other.cy).abs < (height + other.height) / 2
   // intersectX && intersectY
  //}
}