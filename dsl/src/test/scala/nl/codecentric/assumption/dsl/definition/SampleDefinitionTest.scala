package nl.codecentric.assumption.dsl.definition

import nl.codecentric.assumption.dsl.ExperimentEngine
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
class SampleDefinitionTest extends FlatSpec with Matchers {

  "load sample definition" should "load definition" in {

    val testDefinitionMap: mutable.Map[String, () => Unit] = collection.mutable.Map[String, () => Unit]()

    new SampleDefinition {
      override def definitionMap = testDefinitionMap
    }

    testDefinitionMap should have size (1)
    testDefinitionMap should contain key ("this is glue")
  }

}
