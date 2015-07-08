package examples.definition

import nl.codecentric.assumption.dsl.definition.BaseDefinition

/**
 * Created by hylke on 06/07/15.
 */
class OtherDefinition extends BaseDefinition{

  "other baseline" baseline (() => {
    println("other")
  })

  "Check the log" baseline (() => {
    println("Checking the log")
  })

}
