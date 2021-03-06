package nl.codecentric.assumption.dsl.core.parser

import java.io.{File, BufferedReader, FileNotFoundException}

import nl.codecentric.assumption.dsl.api.model.Experiment

import scala.collection.immutable.TreeSet
import scala.collection.mutable
import scala.io.Source
import scala.reflect.internal.util.ScalaClassLoader.URLClassLoader
import scala.util.parsing.input.{StreamReader, CharArrayReader}
;

/**
 * Created by hylke on 01/07/15.
 */
object ExperimentParser {

  val KEYWORD_EXPERIMENT = "Experiment:"

  val EXPERMINTDIR = "experiments/"

  def parserExperiment(fileName: String): Experiment = {
    val experimentLines = readFile(EXPERMINTDIR + fileName)
    ExperimentTextParser.parseExperimentText(experimentLines)
  }

  /**
   * Read file and convert to lines
   * @param fileName
   * @return
   */
  private def readFile(fileName: String): String = {
    var lines: String = null
    try {
      val experimentClassLoader = new URLClassLoader(Array(new File("src/experiment/resources").toURI.toURL), getClass().getClassLoader())
      val experimentFile = Source.fromInputStream(experimentClassLoader.getResourceAsStream(fileName))
      try
        lines = Source.fromInputStream(experimentClassLoader
          .getResourceAsStream(fileName))
          .mkString
      finally
        experimentFile.close()
    } catch {
      case npe: NullPointerException => throw new FileNotFoundException("File cannot be found")
    }
    lines
  }

}
