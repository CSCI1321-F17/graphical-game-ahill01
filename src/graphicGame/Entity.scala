package graphicGame

trait Entity {
  def cx: Double
  def cy: Double
  def width: Double
  def height: Double
  def update(dt: Double): Unit
  def move(dx: Double, dy: Double): Unit
  def getStyle:Int
  
}
object Entity {
 def intersect(e1:Entity, e2:Entity):Boolean = {
   if (e1.cx == e2.cx && e1.cy == e2.cy) true else false
 }

}