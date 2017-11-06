

package graphicGame

trait Entity {
  def cx: Double
  def cy: Double
  def width: Double
  def height: Double
  def update(dt: Double): Unit
  def move(dx: Double, dy: Double): Unit
 
}
object Entity {

}