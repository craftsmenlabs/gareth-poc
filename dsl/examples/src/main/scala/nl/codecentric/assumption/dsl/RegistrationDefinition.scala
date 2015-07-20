package nl.codecentric.assumption.dsl

import nl.codecentric.assumption.dsl.core.definition.BaseDefinition
import org.json4s.native.JsonMethods._
import scala.concurrent.duration._

import scalaj.http.{Http, HttpResponse}

/**
 * Created by hylke on 20/07/15.
 */
class RegistrationDefinition extends BaseDefinition {

  implicit lazy val formats = org.json4s.DefaultFormats

  "Get the current count of registrations" baseline (() => {
    val baselineCount = getRegistrationCount()
    println("Baseline count is", baselineCount)
  })

  "The amount of registrations is increased with 50" assumes (() => {
    val assumeCount = getRegistrationCount()
    println("Assume count is", assumeCount)
  })

  "In one week" time (2 minutes)

  def getRegistrationCount(): Integer = {
    val response: HttpResponse[String] = Http("http://33.33.33.60:8888/count").asString
    val json = parse(response.body);
    (json \ "count").extract[Integer]
  }

}
