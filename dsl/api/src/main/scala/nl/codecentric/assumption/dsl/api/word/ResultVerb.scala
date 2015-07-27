package nl.codecentric.assumption.dsl.api.word

/**
 * Created by hylke on 22/07/15.
 */
trait ResultVerb {

  implicit val message = ""

  def success()(implicit message: String): Unit = {

  }

  def fail()(implicit message: String): Unit = {

  }

}
