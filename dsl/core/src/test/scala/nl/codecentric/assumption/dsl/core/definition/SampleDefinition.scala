package nl.codecentric.assumption.dsl.core.definition

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
