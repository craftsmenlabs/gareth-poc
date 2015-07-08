package nl.codecentric.assumption.web.actor

import akka.actor.Actor
import nl.codecentric.assumption.dsl.ExperimentEngineBuilder
import spray.http.MediaTypes
import spray.routing.HttpService
import spray.json._
import nl.codecentric.assumption.web.json.ExperimentJsonProtocol._
import spray.httpx.marshalling._

/**
 * Created by hylke on 07/07/15.
 */
class ExperimentServiceActor extends Actor with ExperimentService {
  def actorRefFactory = context

  def receive = runRoute(experimentRoute)
}

trait ExperimentService extends HttpService {

  val experimentRoute = path("") {
    get {
      respondWithMediaType(MediaTypes.`application/json`) {
        complete {
          import spray.httpx.SprayJsonSupport._
          ExperimentEngineBuilder.loadExperiment("experiment-0001.experiment")
          ExperimentEngineBuilder.experiments.toList
        }
      }
    }
  }

}
