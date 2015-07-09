package nl.codecentric.assumption.dsl.storage

/**
 * Created by hylke on 06/07/15.
 */
abstract class Storage {

  def store(key: String, value: Any)

  def get(key: String): Any

}
