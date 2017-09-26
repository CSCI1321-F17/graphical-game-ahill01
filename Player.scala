package graphicGame

class Player (private var _cx: Double, private var _cy: Double, val width: Double, val height: Double) extends Entity {
  private var up = false
  private var down = false
  private var left = false
  private var right = false
  
  def cx = _cx
  def cy = _cy
  
  def upPressed = up = true
  def downPressed = down = true
  def leftPressed = left = true
  def rightPressed = right = true
  def upReleased = up = false
  def downReleased = down = false
  def leftReleased = left = false
  def rightReleased = right = false
  
  def update(dt: Double): Unit = {
    _cy -= dt
    _cx -= dt
    //if clear, move otherwise nah
    //if using smooth motion -> (something * delay) -> speed constant -> delay 
    
  }
  
  //add player to level
  
  def moveUpdate(): Unit = {
    if(up) move(0, -1)
    if(down) move(0, 1)
    if(left) move(-1, 0)
    if(right) move(1, 0)
    //move if clear
  }
  
  private def move(dx: Int, dy: Int): Unit = {
    _cx += dx
    _cy += dy
  }
}

object Player {
  
}