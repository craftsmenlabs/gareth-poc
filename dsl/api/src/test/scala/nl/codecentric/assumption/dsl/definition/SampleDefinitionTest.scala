package nl.codecentric.assumption.dsl.definition

import nl.codecentric.assumption.dsl.ExperimentEngine
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
class SampleDefinitionTest extends FlatSpec with Matchers {

  "load sample definition" should "load definition" in {

    val mockBaselineDefinitionMap: mutable.Map[String, () => Unit] = collection.mutable.Map[String, () => Unit]()
    val mockAssumeDefinitionMap: mutable.Map[String, () => Unit] = collection.mutable.Map[String, () => Unit]()
    val mockTimeDefinitionMap: mutable.Map[String, () => Unit] = collection.mutable.Map[String, () => Unit]()

    new SampleDefinition {
      override def baselineDefinitionMap = mockBaselineDefinitionMap
      override def assumeDefinitionMap = mockAssumeDefinitionMap
      override def timeDefinitionMap = mockTimeDefinitionMap
    }

    mockBaselineDefinitionMap should have size (1)
    mockBaselineDefinitionMap should contain key ("this is baseline")

    mockAssumeDefinitionMap should have size (1)
    mockAssumeDefinitionMap should contain key ("this is assume")

    mockTimeDefinitionMap should have size (1)
    mockTimeDefinitionMap should contain key ("this is time")
  }

}
