package nl.codecentric.assumption.dsl.core.parser

import nl.codecentric.assumption.dsl.api.model._

import scala.util.parsing.combinator.RegexParsers
import scala.util.parsing.input.Positional

/**
 * Created by hylke on 01/07/15.
 */
object ExperimentTextParser extends RegexParsers with Positional {

  private val eol = "(\\r?\\n)+".r ^^ {
    _.toString
  }
  private val eoi = "\\z".r ^^ {
    _.toString
  } // end of input


  override val skipWhitespace = false;

  def assumptionBlockStart = keyword_baseline | keyword_assumption | keyword_time


  def experimentOutlineBlock: Parser[Experiment] = experimentBlock ~ rep1(assumptionOutlineBlock) <~ (eoi | keyword_experiment) ^^ { case a =>
    Experiment(a._1, a._2)
  }

  def assumptionOutlineBlock: Parser[AssumptionBlock] = assumptionOutlineParts ^^ {
    case b ~ a ~ t => AssumptionBlock(b, a, t)
  }

  def assumptionOutlineParts = baselineBlock ~ assumptionBlock ~ timeBlock

  def experimentBlock: Parser[String] = keyword_experiment ~> experiment_name <~ eol ^^ {
    case en => en.trim
  }

  def assumptionBlock: Parser[Assumption] = keyword_assumption ~> experiment_name <~ (eol | eoi) ^^ {
    case propertyValue => Assumption(propertyValue.trim)
  }

  def timeBlock: Parser[Time] = keyword_time ~> experiment_name <~ (eol | eoi) ^^ {
    case propertyValue => Time(propertyValue.trim)
  }

  def baselineBlock: Parser[Baseline] = keyword_baseline ~> experiment_name <~ (eol | eoi) ^^ {
    case propertyValue => Baseline(propertyValue.trim)
  }


  def keyword_experiment: Parser[String] = "^\\s*Experiment:".r ^^ {
    _.toString
  }

  def keyword_baseline: Parser[String] = "^\\s*Baseline:".r ^^ {
    _.toString
  }

  def keyword_assumption: Parser[String] = "^\\s*Assumption:".r ^^ {
    _.toString
  }

  def keyword_time: Parser[String] = "^\\s*Time:".r ^^ {
    _.toString
  }

  def experiment_name: Parser[String] = ".*".r ^^ {
    _.toString
  }

  def parseExperimentText(values: String): Experiment = {
    parseAll(experimentOutlineBlock, values) match {
      case Success(x, _) => x
      case failure: NoSuccess => scala.sys.error(failure.msg)
    }
  }

}
