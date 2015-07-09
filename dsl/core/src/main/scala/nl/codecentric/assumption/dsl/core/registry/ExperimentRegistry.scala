package nl.codecentric.assumption.dsl.core.registry

import nl.codecentric.assumption.dsl.api.model.Experiment

/**
 * Created by hylke on 06/07/15.
 */
trait ExperimentRegistry {

  def experiments: List[Experiment]


}
