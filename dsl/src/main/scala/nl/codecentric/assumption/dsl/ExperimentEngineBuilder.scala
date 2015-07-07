package nl.codecentric.assumption.dsl

import nl.codecentric.assumption.dsl.definition.BaseDefinition
import nl.codecentric.assumption.dsl.registery.ExperimentRegistry

/**
 * Created by hylke on 06/07/15.
 */
object ExperimentEngineBuilder {

  val ru = scala.reflect.runtime.universe

  val baselineDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val assumeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  val timeDefinitionMap: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map[String, () => Unit]()

  def loadDefinition(className: String): Unit = {

  }

  def getClassInstance(classLoader: ClassLoader, clsName: String): Any = {
    val mirror = ru.runtimeMirror(classLoader)
    Class.forName(clsName).newInstance()
  }


}
