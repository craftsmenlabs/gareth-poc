package nl.codecentric.assumption.dsl.core.context

import java.time.LocalDateTime

import nl.codecentric.assumption.dsl.api.model.AssumptionBlock

/**
 * Created by hylke on 21/07/15.
 */
class ExperimentExecutionContext(val experimentName: String, val assumption: AssumptionBlock) {

  var baselineExecution, assumptionExecution: Option[LocalDateTime] = None
  var baselineHasDefinition, assumptionHasDefinition, timeHasDefinition: Boolean = false;
  var assumptionException, baselineException: Option[Exception] = None


}
