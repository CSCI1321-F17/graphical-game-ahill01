package graphicGame

class Level (val maze: Maze, private var _entities:Seq[Entity]) {
  val player = new Player(0,0,1,1) 
  val enemy = new Enemy(0,0)
  val entities = _entities
  
  def updateAll(dt: Double): Unit = {
   player.update(dt)
   enemy.update(dt)
  }
  def +=(e:Entity):Unit = {
    _entities :+ e
  }
  def -=(e:Entity):Unit = {
   val index = _entities.indexOf(e) 
   _entities.patch(index,_entities,1)
  }
}


object Level {
 val player = new Player(0,0,1,1)
 val enemy = new Enemy(0,0)
 val entities = Seq(player)
}