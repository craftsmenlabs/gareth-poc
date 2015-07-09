package nl.codecentric.assumption.web.json

import nl.codecentric.assumption.dsl.api.model.{AssumptionBlock, Experiment}
import spray.json._

/**
 * Created by hylke on 07/07/15.
 */
object ExperimentJsonProtocol extends DefaultJsonProtocol {

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


}
