package nl.codecentric.assumption.web.json

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import nl.codecentric.assumption.dsl.api.model.{Success, AssumptionBlock, Experiment, Failure}
import nl.codecentric.assumption.dsl.core.context.ExperimentExecutionContext
import spray.json._

/**
 * Created by hylke on 07/07/15.
 */
object ExperimentJsonProtocol extends DefaultJsonProtocol {

  /*
  implicit object ExperimentJsonFormat extends RootJsonFormat[Experiment] {
    def write(experiment: Experiment) = JsObject(
      "name" -> JsString(experiment.experimentName),
      "assumptions" -> experiment.assumptions.toJson
    )

    def read(value: JsValue): Experiment = {
      throw new UnsupportedOperationException("Experiment can only be read from file")
    }
  }

  implicit object AssumptionBlockJsonFormat extends RootJsonFormat[AssumptionBlock] {
    override def read(json: JsValue): AssumptionBlock = {
      throw new UnsupportedOperationException("Assumption block can only be read from file")
    }

    override def write(assumptionBlock: AssumptionBlock) = JsObject(
      "assumption" -> JsString(assumptionBlock.assumption.glueLine),
      "baseline" -> JsString(assumptionBlock.baseline.glueLine),
      "time" -> JsString(assumptionBlock.time.glueLine)
    )
  }
  */

  implicit object ExperimentExecutionContextJsonFormat extends RootJsonFormat[ExperimentExecutionContext] {
    override def read(json: JsValue): ExperimentExecutionContext = {
      throw new UnsupportedOperationException("Experiment execution context can only be read from file")
    }

    override def write(experimentExecutionContext: ExperimentExecutionContext) = JsObject(
      "experiment_name" -> JsString(experimentExecutionContext.experimentName),
      "baseline" -> JsString(experimentExecutionContext.assumption.baseline.glueLine),
      "assumption" -> JsString(experimentExecutionContext.assumption.assumption.glueLine),
      "time" -> JsString(experimentExecutionContext.assumption.time.glueLine),
      "Success" -> JsString(experimentExecutionContext.assumption.success.getOrElse(Success("")).glueLine),
      "Failure" -> JsString(experimentExecutionContext.assumption.failure.getOrElse(Failure("")).glueLine),
      "baseline_execution" -> JsString(getReadableTimeRepresentation(experimentExecutionContext.baselineExecution)),
      "assumption_execution" -> JsString(getReadableTimeRepresentation(experimentExecutionContext.assumptionExecution)),
      "baseline_has_definition" -> JsBoolean(experimentExecutionContext.baselineHasDefinition),
      "assumption_has_definition" -> JsBoolean(experimentExecutionContext.assumptionHasDefinition),
      "time_has_definition" -> JsBoolean(experimentExecutionContext.timeHasDefinition),
      "baseline_has_exception" -> JsBoolean(experimentExecutionContext.baselineException.isDefined),
      "assumption_has_exception" -> JsBoolean(experimentExecutionContext.assumptionException.isDefined)
    )
  }


  private def getReadableTimeRepresentation(localDateTime: Option[LocalDateTime]): String = {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    var returnValue = ""
    if (localDateTime.isDefined) {
      returnValue = localDateTime.get.format(formatter);
    }
    return returnValue
  }


}
