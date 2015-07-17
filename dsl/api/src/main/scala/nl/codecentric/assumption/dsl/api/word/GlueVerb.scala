package nl.codecentric.assumption.dsl.api.word

import nl.codecentric.assumption.dsl.api.registry.DefinitionRegistry

import scala.concurrent.duration.FiniteDuration

/**
 * Created by hylke on 02/07/15.
 */
trait GlueVerb {

  def definitionRegistry: DefinitionRegistry

  trait GlueVerbWrapper {
    val leftSide: String

    def baseline(block: () => Unit) = {
      definitionRegistry.registerBaseline(leftSide, block)
    }

    def assumes(block: () => Unit) = {
      definitionRegistry.registerAssumption(leftSide, block)
    }

    def time(block: FiniteDuration) = {
      definitionRegistry.registerTime(leftSide, block)
    }
  }

  implicit def convertTo(o: String): GlueVerbWrapper =
    new GlueVerbWrapper {
      val leftSide = o.trim
    }


}
