package nl.codecentric.assumption.dsl

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader

/**
 * Created by hylke on 06/07/15.
 */
class ExperimentEngineBuilder$Test extends FlatSpec with Matchers {

  "Definitions" should "be loaded when a class name is given" in {
    ExperimentEngineBuilder.getClassInstance(new URLClassLoader(Array(new File("experiment").toURI.toURL), getClass().getClassLoader), "examples.definition.OtherDefinition")

    val baselineDefinitions = ExperimentEngineBuilder.baselineDefinitionMap

    baselineDefinitions should have size (1)
    baselineDefinitions should contain key ("other baseline")

  }

}
