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

  "In one week" time (1 minutes)

  "Send a cake to the development team" success (() => {
    println("Sending cake")
  })

  "Send a message to the product owner" failure (() => {
    println("Having a firm talk with the product owner")
  })

  def getRegistrationCount(): Integer = {
    val response: HttpResponse[String] = Http("http://33.33.33.60:8888/count")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 5000)
      .asString
    val json = parse(response.body);
    (json \ "count").extract[Integer]
  }

}
