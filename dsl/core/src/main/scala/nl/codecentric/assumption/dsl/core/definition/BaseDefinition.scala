package nl.codecentric.assumption.dsl.core.definition

import nl.codecentric.assumption.dsl.core.ExperimentEngineBuilder
import nl.codecentric.assumption.dsl.api.registry.DefinitionRegistry
import nl.codecentric.assumption.dsl.api.word.GlueVerb

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
abstract class BaseDefinition extends GlueVerb {

  def definitionRegistry: DefinitionRegistry = ExperimentEngineBuilder.definitionRegistry

}
