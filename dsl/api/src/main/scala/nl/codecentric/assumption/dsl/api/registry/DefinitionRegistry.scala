package nl.codecentric.assumption.dsl.api.registry

/**
 * Created by hylke on 09/07/15.
 */
abstract class DefinitionRegistry {

  def registerBaseline(leftSide: String, block: () => Unit)

  def registerAssumption(leftSide: String, block: () => Unit)

  def registerTime(leftSide: String, block: () => Unit)
}
