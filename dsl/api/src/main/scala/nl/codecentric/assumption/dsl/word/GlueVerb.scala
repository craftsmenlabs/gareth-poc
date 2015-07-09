package nl.codecentric.assumption.dsl.word

import nl.codecentric.assumption.dsl.registery.DefinitionRegistry

/**
 * Created by hylke on 02/07/15.
 */
trait GlueVerb extends DefinitionRegistry {

  trait GlueVerbWrapper {
    val leftSide: String

    def baseline(block: () => Unit) = {
      registerBaseline(leftSide, block)
    }

    def assumes(block: () => Unit) = {
      registerAssume(leftSide, block)
    }

    def time(block: () => Unit) = {
      registerTime(leftSide, block)
    }
  }

  implicit def convertTo(o: String): GlueVerbWrapper =
    new GlueVerbWrapper {
      val leftSide = o.trim
    }


}
