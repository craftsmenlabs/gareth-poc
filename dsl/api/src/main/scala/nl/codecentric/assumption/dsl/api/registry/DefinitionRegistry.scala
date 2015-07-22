package nl.codecentric.assumption.dsl.api.registry

import scala.concurrent.duration.FiniteDuration

/**
 * Created by hylke on 09/07/15.
 */
abstract class DefinitionRegistry {

  def registerBaseline(leftSide: String, block: () => Unit)

  def registerAssumption(leftSide: String, block: () => Unit)

  def registerSuccess(leftSide: String, block: () => Unit)

  def registerFailure(leftSide: String, block: () => Unit)

  def registerTime(leftSide: String, time: FiniteDuration)
}
