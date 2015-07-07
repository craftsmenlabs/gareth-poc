package nl.codecentric.assumption.dsl.definition

import nl.codecentric.assumption.dsl.ExperimentEngineBuilder
import nl.codecentric.assumption.dsl.word._

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
abstract class BaseDefinition extends GlueVerb {

  override def baselineDefinitionMap: mutable.Map[String, () => Unit] = ExperimentEngineBuilder.baselineDefinitionMap

  override def assumeDefinitionMap: mutable.Map[String, () => Unit] = ExperimentEngineBuilder.assumeDefinitionMap

  override def timeDefinitionMap: mutable.Map[String, () => Unit] = ExperimentEngineBuilder.timeDefinitionMap

}
