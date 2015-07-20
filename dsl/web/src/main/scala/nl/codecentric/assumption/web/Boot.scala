package nl.codecentric.assumption.web

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import nl.codecentric.assumption.web.actor.ExperimentServiceActor
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by hylke on 07/07/15.
 */
object Boot extends App{

  implicit val system = ActorSystem("experiment")
  val service = system.actorOf(Props[ExperimentServiceActor], "experiment-rest-service")

  // IO requires an implicit ActorSystem, and ? requires an implicit timeout
  // Bind HTTP to the specified service.
  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "0.0.0.0", port = 9999)

}
