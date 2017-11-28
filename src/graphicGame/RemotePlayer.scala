package graphicGame

@remote trait RemotePlayer {
 private var up = false
  private var down = false
  private var left = false
  private var right = false
  private var s = false
  def upPressed = up = true
  def downPressed = down = true
  def leftPressed = left = true
  def rightPressed = right = true
 def sPressed = s = true
  def upReleased = up = false
  def downReleased = down = false
  def leftReleased = left = false
  def rightReleased = right = false
  def sReleased = s = false
}