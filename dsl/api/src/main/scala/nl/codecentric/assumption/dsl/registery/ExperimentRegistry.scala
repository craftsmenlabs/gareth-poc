package nl.codecentric.assumption.dsl.registery

import nl.codecentric.assumption.dsl.model.Experiment

/**
 * Created by hylke on 06/07/15.
 */
trait ExperimentRegistry {

  def experiments: List[Experiment]


}
