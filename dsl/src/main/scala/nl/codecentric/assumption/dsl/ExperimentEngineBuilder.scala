package nl.codecentric.assumption.dsl

import nl.codecentric.assumption.dsl.model.Experiment
import nl.codecentric.assumption.dsl.parser.ExperimentParser

import scala.collection.mutable

/**
 * Created by hylke on 06/07/15.
 */
object ExperimentEngineBuilder {


  val ru = scala.reflect.runtime.universe

  val baselineDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val assumeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val timeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val experiments: mutable.MutableList[Experiment] = mutable.MutableList[Experiment]()

  def loadDefinition(className: String): Unit = {

  }

  def getClassInstance(classLoader: ClassLoader, clsName: String): Any = {
    val mirror = ru.runtimeMirror(classLoader)
    Class.forName(clsName).newInstance()
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
