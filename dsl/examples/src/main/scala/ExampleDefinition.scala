import java.io.{File, FileNotFoundException}

import nl.codecentric.assumption.dsl.core.ExperimentEngineBuilder
import nl.codecentric.assumption.dsl.core.definition.BaseDefinition
import nl.codecentric.assumption.dsl.core.parser.ExperimentParser._

import scala.io.Source
import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader

/**
 * Created by hylke on 09/07/15.
 */
class ExampleDefinition extends BaseDefinition {

  val logParseRegex = """\w+: (\d{3})""".r

  "Example baseline" baseline (() => {
    println("Example baseline :o)")
    parserExperiment("logs/baseline.log")
  })

  "Example assume" assumes (() => {
    println("Example assume")
    val okPercentage = parserExperiment("logs/final.log")
    if (okPercentage > 70) {
      println("Experiment OK")
    } else {
      println("Experiment Pass")
    }

  })

  "Example time" time (() => {
    println("Example time")
  })

  "Example baseline2" baseline (() => {
    println("Example baseline 2 :o)")
  })

  "Example assume2" assumes (() => {
    println("Example assume 2")
  })

  "Example time2" time (() => {
    println("Example time 2")
  })

  def parserExperiment(fileName: String): Int = {
    val experimentLines = readFile(fileName)

    val statusCodes = experimentLines
      .get
      .map(line => parseLogLine(line))

    val okPercentage = (100 / statusCodes.length) * (statusCodes.filter(statusCode => statusCode == 200).length)

    println(s"Request OK: $okPercentage %")

    okPercentage
  }

  def parseLogLine(logLine: String): Int = {

    val value = logParseRegex.findFirstMatchIn(logLine).map(_ group 1).get
    value.toInt

  }

  /**
   * Read file and convert to lines
   * @param fileName
   * @return
   */
  private def readFile(fileName: String): Option[List[String]] = {
    var lines: Option[List[String]] = None
    try {
      val experimentClassLoader = new URLClassLoader(Array(new File("src/main/resources").toURI.toURL), getClass().getClassLoader())
      val experimentFile = Source.fromInputStream(experimentClassLoader.getResourceAsStream(fileName))
      try
        lines = Option(experimentFile.getLines().toList)
      finally
        experimentFile.close()
    } catch {
      case npe: NullPointerException => throw new FileNotFoundException("File cannot be found")
    }
    lines
  }

}
