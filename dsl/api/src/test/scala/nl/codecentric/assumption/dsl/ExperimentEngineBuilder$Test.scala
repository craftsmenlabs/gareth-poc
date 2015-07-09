package nl.codecentric.assumption.dsl

import java.io.File

import org.scalatest.{BeforeAndAfter, FlatSpec, Matchers}

import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader

/**
 * Created by hylke on 06/07/15.
 */
class ExperimentEngineBuilder$Test extends FlatSpec with Matchers with BeforeAndAfter {

  "Definitions" should "be loaded when a class name is given" in {
    loadDefinitionFromClass("examples.definition.OtherDefinition")

    val baselineDefinitions = ExperimentEngineBuilder.baselineDefinitionMap

    baselineDefinitions should have size (2)
    baselineDefinitions should contain key ("other baseline")
    baselineDefinitions should contain key ("Check the log")

  }



  "Run experiment" should "execute the baseline code" in {
    loadDefinitionFromClass("examples.definition.OtherDefinition")

    ExperimentEngineBuilder.loadExperiment("other.experiment")

    ExperimentEngineBuilder.runBaselinesForExperiment("This another experiment")


  }

  before {
    ExperimentEngineBuilder.cleanDefinitions()
  }

  def loadDefinitionFromClass(fullClassName: String): Unit = {
    ExperimentEngineBuilder.loadDefinition(fullClassName)
  }


}
