package nl.codecentric.assumption.dsl.core

import java.io.File
import java.time.LocalDateTime

import akka.actor.ActorSystem
import nl.codecentric.assumption.dsl.core.context.ExperimentExecutionContext
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import nl.codecentric.assumption.dsl.api.model.{Failure, Success, Experiment}
import nl.codecentric.assumption.dsl.core.parser.ExperimentParser
import nl.codecentric.assumption.dsl.core.registry.CoreDefinitionRegistry
import nl.codecentric.assumption.dsl.api.registry.DefinitionRegistry

import scala.collection.mutable
import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader

/**
 * Created by hylke on 06/07/15.
 */
object ExperimentEngineBuilder {

  import ExecutionContext.Implicits.global

  val system = ActorSystem("ExperimentEngine")

  val experimentClassLoader = new URLClassLoader(Array(new File("src/experiment/scala").toURI.toURL), getClass().getClassLoader)

  val ru = scala.reflect.runtime.universe

  val definitionRegistry: DefinitionRegistry = new CoreDefinitionRegistry()

  val baselineDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val assumeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val successDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val failureDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val timeDefinitionMap: scala.collection.mutable.Map[String, FiniteDuration] = scala.collection.mutable.Map[String, FiniteDuration]()

  val experiments: mutable.MutableList[Experiment] = mutable.MutableList[Experiment]()

  val experimentExecutionContexts: mutable.MutableList[ExperimentExecutionContext] = mutable.MutableList[ExperimentExecutionContext]()

  val voidWork: () => Unit = (() => {
    println("Some void work")
  })

  def loadDefinition(clsName: String): Any = {
    Class.forName(clsName, true, experimentClassLoader).newInstance()
  }

  def loadExperiment(fileName: String): Unit = {
    val experiment = ExperimentParser.parserExperiment(fileName)
    experiments += experiment

    // Create experiment execution context foreach assumption
    experiment.assumptions.foreach(ab => {
      experimentExecutionContexts += new ExperimentExecutionContext(experiment.experimentName, ab);
    })
  }

  def runExperiment(experimentName: String): Unit = {
    findExperimentByName(experimentName).foreach(eec => {
      println("Running experiment", eec.experimentName)
      runBaselineForExperiments(eec.assumption.baseline.glueLine, eec)
      planAssumptions(eec.assumption.assumption.glueLine
        , eec.assumption.time.glueLine
        , eec.assumption.success.getOrElse(Success("")).glueLine
        , eec.assumption.failure.getOrElse(Failure("")).glueLine
        , eec)
    });

  }

  private def runBaselineForExperiments(glueLine: String, experimentExecutionContext: ExperimentExecutionContext): Unit = {

    experimentExecutionContext.baselineHasDefinition = baselineDefinitionMap.contains(glueLine)

    if (baselineDefinitionMap.contains(glueLine)) {
      try {
        baselineDefinitionMap.get(glueLine).get()
        experimentExecutionContext.baselineExecution = Option(LocalDateTime.now())
      } catch {
        case e: Exception => experimentExecutionContext.baselineException = Option(e)
      }
    } else {
      println(String.format("Cannot find baseline with glueLine \"%s\" as definition", glueLine))
    }
  }

  private def planAssumptions(assumptionGlueLine: String, timeGlueLine: String, successGlueLine: String, failedGlueLine: String, experimentExecutionContext: ExperimentExecutionContext): Unit = {

    experimentExecutionContext.assumptionHasDefinition = assumeDefinitionMap.contains(assumptionGlueLine)
    experimentExecutionContext.timeHasDefinition = timeDefinitionMap.contains(timeGlueLine)

    if (assumeDefinitionMap.contains(assumptionGlueLine) && timeDefinitionMap.contains(timeGlueLine)) {
      // Get work from definitions
      val assumptionWork = assumeDefinitionMap.get(assumptionGlueLine)
      val successWork = successDefinitionMap.get(successGlueLine)
      val failedWork = failureDefinitionMap.get(failedGlueLine)
      // Schedule work
      system.scheduler.scheduleOnce(timeDefinitionMap.get(timeGlueLine).get) {
        try {
          assumptionWork.get()
          experimentExecutionContext.assumptionExecution = Option(LocalDateTime.now())
          if(successWork.isDefined){
            successWork.get()
          }else{
            println("No success work, but completed")
          }
        } catch {
          case e: Exception => {
            if(failedWork.isDefined){
              failedWork.get()
            }else{
              println("No failed work, but completed")
            }
            experimentExecutionContext.assumptionException = Option(e)
          }
        }
      }
    }
  }

  def cleanDefinitions(): Unit = {
    assumeDefinitionMap.clear()
    baselineDefinitionMap.clear()
    timeDefinitionMap.clear()
  }

  def cleanExperiments(): Unit = {
    experiments.clear()
    experimentExecutionContexts.clear()
  }

  def findExperimentByName(experimentName: String): List[ExperimentExecutionContext] = {
    experimentExecutionContexts.filter(ex => experimentName.equals(ex.experimentName)).toList
  }


}
