package nl.codecentric.assumption.dsl.storage

/**
 * Created by hylke on 06/07/15.
 */
class BaselineStorage extends Storage {

  private val baselineMap: scala.collection.mutable.Map[String, Any] = scala.collection.mutable.Map();

  override def store(key: String, value: Any): Unit = {
    baselineMap += key -> value
  }

  override def get(key: String): Any = {
    baselineMap.get(key)
  }
}
