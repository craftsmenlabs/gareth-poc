package nl.codecentric.assumption.dsl.definition

import nl.codecentric.assumption.dsl.ExperimentEngineBuilder

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
class SampleDefinition extends BaseDefinition {

  "this is baseline" baseline (() => {
    println("x")
  })

  "this is assume" assumes (() => {
    println("y")
  })

  "this is time" time (() => {
    println("z")
  })

}
