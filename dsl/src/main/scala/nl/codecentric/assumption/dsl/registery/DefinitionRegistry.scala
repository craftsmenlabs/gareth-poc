package nl.codecentric.assumption.dsl.registery

/**
 * Created by hylke on 06/07/15.
 */
trait DefinitionRegistry {

  def definitionMap: scala.collection.mutable.Map[String, () => Unit]

  def register(description: String, block: () => Unit): Unit = {
    definitionMap += description -> block
  }


}
