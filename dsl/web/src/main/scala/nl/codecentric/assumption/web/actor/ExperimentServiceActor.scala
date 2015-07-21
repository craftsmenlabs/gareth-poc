package nl.codecentric.assumption.web.actor

import akka.actor.Actor
import nl.codecentric.assumption.dsl.core.ExperimentEngineBuilder
import spray.http.HttpHeaders.{`Access-Control-Allow-Origin`, Origin, `Content-Type`}
import spray.http.{AllOrigins, HttpOrigin, MediaTypes}
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

  private def init: Unit = {
    ExperimentEngineBuilder.loadExperiment("registration.experiment")
    ExperimentEngineBuilder.loadDefinition("nl.codecentric.assumption.dsl.RegistrationDefinition")
    ExperimentEngineBuilder.runExperiment("Increased registrations")
  }

  init


  val experimentRoute = path("experiments") {
    get {
      respondWithHeaders(Origin(Seq(HttpOrigin("http://localhost:9999"), HttpOrigin("http://33.33.33.60:8765"))), `Content-Type`(MediaTypes.`application/json`), `Access-Control-Allow-Origin`(AllOrigins)) {
        complete {
          import spray.httpx.SprayJsonSupport._
          ExperimentEngineBuilder.experimentExecutionContexts.toList
        }
      }
    }
  }
}
