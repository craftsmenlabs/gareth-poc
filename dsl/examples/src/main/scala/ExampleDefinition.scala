import nl.codecentric.assumption.dsl.core.definition.BaseDefinition

/**
 * Created by hylke on 09/07/15.
 */
class ExampleDefinition extends BaseDefinition {

  "Example baseline" baseline (() => {
    println("Example baseline :o)")
  })

  "Example assume" assumes  (() => {
    println("Example assume")
  })

  "Example time" time (() => {
    println("Example time")
  })

  "Example baseline2" baseline (() => {
    println("Example baseline 2 :o)")
  })

  "Example assume2" assumes  (() => {
    println("Example assume 2")
  })

  "Example time2" time (() => {
    println("Example time 2")
  })


}
