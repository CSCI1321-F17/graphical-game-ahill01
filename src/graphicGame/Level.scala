package graphicGame

class Level (val maze:Maze, private var _entities:Seq[Entity]) {
  
  def entities = _entities
  
  def updateAll(dt: Double): Unit = {
   entities.foreach(_.update(dt))
  }
  def +=(e:Entity):Unit = {
    _entities :+= e
  }
  def -=(e:Entity):Unit = {
   val index = _entities.indexOf(e) 
   _entities.patch(index,_entities,1)
  }

////def follow(ex: Double, ey: Double, px: Double, py: Double): Double = { 
//  val offsets = Array((-1, 0), (1, 0), (0, -1), (0, 1))
//    
//    val q = new ArrayQueue[(Double, Double, Double)]()
//    q.enqueue((ex, ey, 0))
//    val visited = collection.mutable.Set[(Double, Double)]()
//    visited += ex -> ey
//    while (!q.isEmpty) {
//      val (x, y, steps) = q.dequeue()
//      for ((dx, dy) <- offsets) {
//        val (nx, ny) = (x + dx, y + dy)
//        if (nx == ex && ny == ey) return steps.toDouble + 1
//      if (!visited.contains(nx -> ny) && ! Enemy.intersect(Player)) {
//          q.enqueue((nx, ny, steps + 1))
//          visited += nx -> ny
//      }
//      }
//    }
//    -1.0
//  }
}

object Level {

}