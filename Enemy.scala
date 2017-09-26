

package graphicGame

class Enemy(private var _x:Double, private var _y: Double) {
  def update(dt:Double): Unit = {
    _y -=dt
    _x -=dt
  }
}