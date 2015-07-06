package nl.codecentric.assumption.dsl.definition

import nl.codecentric.assumption.dsl.ExperimentEngineBuilder

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
class SampleDefinition extends BaseDefinition {



  "this is glue" baseline (() => {
    println("x")
  })

  override def definitionMap: mutable.Map[String, () => Unit] = ExperimentEngineBuilder.definitionMap
}
