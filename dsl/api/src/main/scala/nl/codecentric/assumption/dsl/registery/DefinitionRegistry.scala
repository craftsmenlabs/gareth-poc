package nl.codecentric.assumption.dsl.registery

/**
 * Created by hylke on 06/07/15.
 */
trait DefinitionRegistry {

  def baselineDefinitionMap: scala.collection.mutable.Map[String, () => Unit]

  def assumeDefinitionMap: scala.collection.mutable.Map[String, () => Unit]

  def timeDefinitionMap: scala.collection.mutable.Map[String, () => Unit]


  def registerBaseline(description: String, block: () => Unit): Unit = {
    register(baselineDefinitionMap, description, block)
  }

  def registerAssume(description: String, block: () => Unit): Unit = {
    register(assumeDefinitionMap, description, block)
  }

  def registerTime(description: String, block: () => Unit): Unit = {
    register(timeDefinitionMap, description, block)
  }

  def register(definitionMap: scala.collection.mutable.Map[String, () => Unit], description: String, block: () => Unit): Unit = {
    if (definitionMap.contains(description)) {
      throw new IllegalStateException(String.format("Cannot add glue line, because it is already added: %s", description))
    }
    definitionMap += description -> block
  }


}
