package nl.codecentric.assumption.dsl.core.definition

import nl.codecentric.assumption.dsl.api.registry.DefinitionRegistry
import nl.codecentric.assumption.dsl.core.registry.CoreDefinitionRegistry
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.mutable
import scala.concurrent.duration.FiniteDuration

/**
 * Created by hylke on 06/07/15.
 */
class SampleDefinitionTest extends FlatSpec with Matchers {

  "load sample definition" should "load definition" in {

    val mockBaselineDefinitionMap: mutable.Map[String, () => Unit] = collection.mutable.Map[String, () => Unit]()
    val mockAssumeDefinitionMap: mutable.Map[String, () => Unit] = collection.mutable.Map[String, () => Unit]()
    val mockTimeDefinitionMap: mutable.Map[String, FiniteDuration] = collection.mutable.Map[String, FiniteDuration]()

    val mockDefinitionRegistry: DefinitionRegistry = new CoreDefinitionRegistry(){
      override def assumeDefinitionMap = mockAssumeDefinitionMap;
      override def baselineDefinitionMap = mockBaselineDefinitionMap;
      override def timeDefinitionMap = mockTimeDefinitionMap;
    }

    new SampleDefinition {

      override def definitionRegistry = mockDefinitionRegistry
    }

    mockBaselineDefinitionMap should have size (1)
    mockBaselineDefinitionMap should contain key ("this is baseline")

    mockAssumeDefinitionMap should have size (1)
    mockAssumeDefinitionMap should contain key ("this is assume")

    mockTimeDefinitionMap should have size (1)
    mockTimeDefinitionMap should contain key ("this is time")
  }

}
