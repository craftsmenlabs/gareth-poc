package nl.codecentric.assumption.dsl.registery

import nl.codecentric.assumption.dsl.model.Experiment

/**
 * Created by hylke on 06/07/15.
 */
class ExperimentRegistry {

  val baselineDefinitions: scala.collection.mutable.Map[String, () => Unit] = scala.collection.mutable.Map()

  val experiments: List[Experiment] = List()

  def registerGlue(glueLine: String, blockType: String, block: () => Unit): Unit = {
    registerBaseline(glueLine, block)
  }


  private def registerBaseline(glueLine: String, block: () => Unit) {
    baselineDefinitions += glueLine -> block
  }


}
