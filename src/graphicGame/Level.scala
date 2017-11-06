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

  def intersect(e:Entity,p:Entity):Boolean = {
    if(e.cx == p.cx && e.cy == p.cy) true else false
  }
def follow(e1: Entity, e2: Entity): Double = { 
  val offsets = Array((-1, 0), (1, 0), (0, -1), (0, 1))
   
 val q = new ArrayQueue[(Double, Double, Double)]()
q.enqueue((e1.cx, e1.cy, 0))
val visited = collection.mutable.Set[(Double, Double)]()
 visited += (e1.cx -> e1.cy)
    while (!q.isEmpty) {
      val (x, y, steps) = q.dequeue()
      for ((dx, dy) <- offsets) {
        val (nx, ny) = (x + dx, y + dy)
        if (nx == e2.cx && ny == e2.cy) return steps.toDouble + 1
        if (!visited.contains(nx -> ny) && nx >= 0 && nx < maze.width && ny >= 0 && ny < maze.height && maze.isClear(nx,ny,e,ey)){
          q.enqueue((nx, ny, steps + 1))
          visited += (nx -> ny)
        }
      }
    }
  -1.0
 }
}

object Level {

}