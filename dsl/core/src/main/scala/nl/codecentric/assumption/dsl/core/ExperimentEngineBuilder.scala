package nl.codecentric.assumption.dsl.core

import java.io.File

import nl.codecentric.assumption.dsl.api.model.Experiment
import nl.codecentric.assumption.dsl.core.parser.ExperimentParser
import nl.codecentric.assumption.dsl.core.registry.CoreDefinitionRegistry
import nl.codecentric.assumption.dsl.api.registry.DefinitionRegistry

import scala.collection.mutable
import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader

/**
 * Created by hylke on 06/07/15.
 */
object ExperimentEngineBuilder {


  val experimentClassLoader = new URLClassLoader(Array(new File("src/experiment/scala").toURI.toURL), getClass().getClassLoader)


  val ru = scala.reflect.runtime.universe

  val definitionRegistry: DefinitionRegistry = new CoreDefinitionRegistry()

  val baselineDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val assumeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val timeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val experiments: mutable.MutableList[Experiment] = mutable.MutableList[Experiment]()

  def loadDefinition(clsName: String): Any = {
    Class.forName(clsName, true, experimentClassLoader).newInstance()
  }

  def loadExperiment(fileName: String): Unit = {
    val experiment = ExperimentParser.parserExperiment(fileName)
    experiments += experiment
  }

  def runBaselinesForExperiment(experimentName: String): Unit = {
    val experiment = findExperimentByName(experimentName)
    experiment.assumptions.foreach(ab => {
      if (baselineDefinitionMap.contains(ab.baseline.glueLine)) {
        val baselineWork = baselineDefinitionMap.get(ab.baseline.glueLine)
        baselineWork.get()
      } else {
        println(String.format("Cannot find baseline with glueLine \"%s\" as definition", ab.baseline.glueLine))
      }

    })
  }

  def cleanDefinitions(): Unit = {
    assumeDefinitionMap.clear()
    baselineDefinitionMap.clear()
    timeDefinitionMap.clear()
  }

  def cleanExperiments(): Unit = {
    experiments.clear()
  }

  private def findExperimentByName(experimentName: String): Experiment = {
    experiments.find(ex => experimentName.equals(ex.experimentName)).get
  }


}
