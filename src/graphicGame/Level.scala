package graphicGame

class Level(val maze: Maze, private var _entities: Seq[Entity]) {

  def entities = _entities
  
  def players = _entities.collect{case p: Player => p}
  
  def updateAll(dt: Double): Unit = {
    entities.foreach(_.update(dt))
  }
  def +=(e: Entity): Unit = {
    _entities :+= e
  }
  def -=(e: Entity): Unit = {
    val index = _entities.indexOf(e)
    _entities.patch(index, _entities, 1)
  }

  def intersect(e: Entity, p: Entity): Boolean = {
    if (e.cx == p.cx && e.cy == p.cy) true else false
  }
   def buildLevel():PassableLevel = {
    val pEntities = entities.map(n => new PassableEntity(n.cx,n.cy,n.width,n.height,n.getStyle))
    val pLevel = new PassableLevel(maze, pEntities)
    pLevel
  }

  /*
   * breadth first search between 2 entities
   * @param entity that is moving, entity that is being chased
   * @return distance of shortest path
   */
  
  val offsets = Array((-1, 0), (1, 0), (0, -1), (0, 1))
  def bfs(e1x: Int, e1y: Int, e2x: Int, e2y:Int): Double = {

    val q = new ArrayQueue[(Int, Int, Double)]()
    q.enqueue((e1x, e1y, 0))
    val visited = collection.mutable.Set[(Int, Int)]()
    visited += (e1x -> e1y)
    while (!q.isEmpty) {
      val (x, y, steps) = q.dequeue()
      for ((dx, dy) <- offsets) {
        val (nx, ny) = (x + dx, y + dy)
        if (nx == e2x && ny == e2y) return steps.toDouble + 1
        if (!visited.contains(nx -> ny) && nx >= 0 && nx < maze.width && ny >= 0 && ny < maze.height) {
          q.enqueue((nx, ny, steps + 1))
          visited += (nx -> ny)
        }
      }
    }
    100000000
  }
}

object Level {

}