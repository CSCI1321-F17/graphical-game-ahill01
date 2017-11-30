package graphicGame

case class PassableLevel(val maze:Maze, val entities:Seq[PassableEntity]) extends Serializable {
  
}