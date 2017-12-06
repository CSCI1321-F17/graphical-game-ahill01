package graphicGame

class Level(val maze: Maze, private var _entities: Seq[Entity]) {
  var counter = 0.0
  
  def entities = _entities

  def players = _entities.collect { case p: Player => p }

  def updateAll(dt: Double): Unit = synchronized {
    entities.foreach(_.update(dt))
    _entities = _entities.filter(_.alive)
   
    counter -= dt
     pollution()
  }
  /*
   * @param entity
   * adds entity to level's list of entities
   */
  def +=(e: Entity): Unit = synchronized {
    _entities :+= e
  }
  /*
   * @param entity
   * removes entity from to level's list of entities
   */
  def -=(e: Entity): Unit = {
    val index = _entities.indexOf(e)
    _entities.patch(index, _entities, 1)
  }

  /*
 * checks if 2 things are intersecting
 */
  def intersect(e: Entity, p: Entity): Boolean = {
    if (e.cx == p.cx && e.cy == p.cy) true else false
  }
  
  
  /*
   * adds Trash to the level @ pre-set intervals
   */
  
   def pollution():Unit = {
     if(counter < 0) new Trash(20,20,this)
     counter = 2.0
   }
  
  /*
   * makes list of passable entities & builds a passable level from this level
   */
  def buildLevel(): PassableLevel = {
    val pEntities = entities.map(n => new PassableEntity(n.cx, n.cy, n.width, n.height, n.getStyle))
    val pLevel = new PassableLevel(maze, pEntities)
    pLevel
  }
  
  /*
   * breadth first search between 2 entities
   * @param entity that is moving, entity that is being chased
   * @return distance of shortest path
   */

  val offsets = Array((-1, 0), (1, 0), (0, -1), (0, 1))
  def bfs(e1x: Int, e1y: Int, e2x: Int, e2y: Int): Double = {
    if (!maze.isClear(e1x, e1y, 2, 2)) 100000000 else {
      val q = new ArrayQueue[(Int, Int, Double)]()
      q.enqueue((e1x, e1y, 0))
      val visited = collection.mutable.Set[(Int, Int)]()
      visited += (e1x -> e1y)
      while (!q.isEmpty) {
        val (x, y, steps) = q.dequeue()
        for ((dx, dy) <- offsets) {
          val (nx, ny) = (x + dx, y + dy)
          if (nx == e2x && ny == e2y) return steps.toDouble + 1
          if (!visited.contains(nx -> ny) && nx >= 0 && nx < maze.width && ny >= 0 && ny < maze.height && maze.isClear(nx, ny, 2, 2)) {
            q.enqueue((nx, ny, steps + 1))
            visited += (nx -> ny)
          }
        }
      }
      100000000
    }
  }
}

object Level {

}