package nl.codecentric.assumption.dsl.core.registry

import nl.codecentric.assumption.dsl.core.ExperimentEngineBuilder
import nl.codecentric.assumption.dsl.api.registry.DefinitionRegistry

import scala.concurrent.duration.FiniteDuration

/**
 * Created by hylke on 06/07/15.
 */
class CoreDefinitionRegistry extends DefinitionRegistry {

  def baselineDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = ExperimentEngineBuilder.baselineDefinitionMap

  def assumeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = ExperimentEngineBuilder.assumeDefinitionMap

  def timeDefinitionMap: scala.collection.mutable.Map[String, FiniteDuration] = ExperimentEngineBuilder.timeDefinitionMap

  override def registerBaseline(description: String, block: () => Unit): Unit = {
    register(baselineDefinitionMap, description, block)
  }

  override def registerAssumption(description: String, block: () => Unit): Unit = {
    register(assumeDefinitionMap, description, block)
  }

  override def registerTime(description: String, finiteDuration: FiniteDuration): Unit = {
    if (timeDefinitionMap.contains(description)) {
      throw new IllegalStateException(String.format("Cannot add glue line, because it is already added: %s", description))
    }
    timeDefinitionMap += description -> finiteDuration
  }

  def register(definitionMap: scala.collection.mutable.Map[String, () => Unit], description: String, block: () => Unit): Unit = {
    if (definitionMap.contains(description)) {
      throw new IllegalStateException(String.format("Cannot add glue line, because it is already added: %s", description))
    }
    definitionMap += description -> block
  }


}
