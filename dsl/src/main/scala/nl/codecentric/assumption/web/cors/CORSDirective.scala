package nl.codecentric.assumption.web.cors

import spray.http.HttpHeaders.{`Access-Control-Allow-Credentials`, `Access-Control-Allow-Headers`, `Access-Control-Allow-Methods`, `Access-Control-Allow-Origin`}
import spray.http.{HttpMethods, HttpOrigin, SomeOrigins, StatusCodes}
import spray.routing.{Directives, Route}
import HttpMethods._

/**
 * Created by hylke on 08/07/15.
 */
trait CORSSupport extends Directives {
  private val CORSHeaders = List(
    `Access-Control-Allow-Methods`(GET, POST, PUT, DELETE, OPTIONS),
    `Access-Control-Allow-Headers`("Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent"),
    `Access-Control-Allow-Credentials`(true)
  )

  def respondWithCORS(origin: String)(routes: => Route) = {
    val originHeader = `Access-Control-Allow-Origin`(SomeOrigins(Seq(HttpOrigin(origin))))
    respondWithHeaders(originHeader :: CORSHeaders) {
      routes ~ options { complete(StatusCodes.OK) }
    }
  }
}
