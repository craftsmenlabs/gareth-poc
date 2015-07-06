package nl.codecentric.assumption.dsl.definition

import nl.codecentric.assumption.dsl.ExperimentEngineBuilder
import nl.codecentric.assumption.dsl.word._

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
trait BaseDefinition extends GlueVerb {

  override def definitionMap: mutable.Map[String, () => Unit] = ExperimentEngineBuilder.definitionMap

}
