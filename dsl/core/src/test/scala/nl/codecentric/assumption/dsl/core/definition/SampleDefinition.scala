package nl.codecentric.assumption.dsl.core.definition

import scala.concurrent.duration._

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

  "this is failure" failure (() => {
    println("y")
  })

  "this is success" success (() => {
    println("y")
  })

  "this is time" time (60 seconds)

}
