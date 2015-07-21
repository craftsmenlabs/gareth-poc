package nl.codecentric.assumption.dsl.core

import java.io.File

import nl.codecentric.assumption.dsl.core.ExperimentEngineBuilder
import nl.codecentric.assumption.dsl.core.context.ExperimentExecutionContext
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

    ExperimentEngineBuilder.runExperiment("This another experiment")


  }

  "ExperimentExecutionContext list" should "contain 1 item when filtered on name" in {
    ExperimentEngineBuilder.loadExperiment("other.experiment")
    val list: List[ExperimentExecutionContext] = ExperimentEngineBuilder.findExperimentByName("This another experiment")
    list should have size (1)
  }

  before {
    ExperimentEngineBuilder.cleanDefinitions()
    ExperimentEngineBuilder.cleanExperiments()
  }

  def loadDefinitionFromClass(fullClassName: String): Unit = {
    ExperimentEngineBuilder.loadDefinition(fullClassName)
  }


}
