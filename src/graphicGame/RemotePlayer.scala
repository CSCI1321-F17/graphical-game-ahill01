package graphicGame

@remote trait RemotePlayer {
  def cx:Double
  def cy:Double
  def upPressed:Unit
  def downPressed:Unit 
  def leftPressed:Unit
  def rightPressed:Unit
  def sPressed:Unit 
  def upReleased:Unit
  def downReleased:Unit
  def leftReleased:Unit
  def rightReleased:Unit
  def sReleased:Unit
  def score:Int
}