package nl.codecentric.assumption.dsl.core.parser

import java.io.FileNotFoundException

import nl.codecentric.assumption.dsl.api.model._
import org.scalatest._

/**
 * Created by hylke on 01/07/15.
 */
class ExperimentParserTest extends FlatSpec with Matchers {

  "Experiment-0001.experiment" should "have as Reduce failed logins as experiment name" in {

    val experiment0001 = ExperimentParser.parserExperiment("experiment-0001.experiment")
    experiment0001 should not be null
    experiment0001.experimentName should be("Reduce failed logins")

    val assumptions = experiment0001.assumptions
    assumptions should have size (1)

    validateAssumption(assumptions(0)
      , "By showing a caps-lock warning the failed logins are reduced by 5%"
      , "Failed logins are read from log"
      , "5 days"
      , None
      , None)

  }

  "Experiment-0002.experiment" should "have as Reduce failed logins as experiment name" in {

    val experiment0002 = ExperimentParser.parserExperiment("experiment-0002.experiment")
    experiment0002 should not be null
    experiment0002.experimentName should be("Reduce failed logins (multiple assumptions)")

    val assumptions = experiment0002.assumptions
    assumptions should have size (2)

    validateAssumption(assumptions(0)
      , "By showing a caps-lock warning the failed logins are reduced by 5%1"
      , "Failed logins are read from log1"
      , "5 days1"
      , None
      , None)

    validateAssumption(assumptions(1)
      , "By showing a caps-lock warning the failed logins are reduced by 5%2"
      , "Failed logins are read from log2"
      , "5 days2"
      , None
      , None)

  }


  "Experiment-0003.experiment" should "have as failure glue line" in {

    val experiment0003 = ExperimentParser.parserExperiment("experiment-0003.experiment")
    experiment0003 should not be null
    experiment0003.experimentName should be("Reduce failed logins (with failure)")

    val assumptions = experiment0003.assumptions
    assumptions should have size (1)

    validateAssumption(assumptions(0)
      , "By showing a caps-lock warning the failed logins are reduced by 5%"
      , "Failed logins are read from log"
      , "5 days"
      , None
      , Option(Failure("this is a failure")))
  }

  "Experiment-0004.experiment" should "have a success glue line" in {

    val experiment0004 = ExperimentParser.parserExperiment("experiment-0004.experiment")
    experiment0004 should not be null
    experiment0004.experimentName should be("Reduce failed logins (with success)")

    val assumptions = experiment0004.assumptions
    assumptions should have size (1)

    validateAssumption(assumptions(0)
      , "By showing a caps-lock warning the failed logins are reduced by 5%"
      , "Failed logins are read from log"
      , "5 days"
      , Option(Success("this is a success"))
      , None)
  }

  private def validateAssumption(assumption: AssumptionBlock, assumptionGlueExpectation: String, baselineGlueExpectation: String, timeGlueExpectation: String, successGlueExpectation: Option[Success], failureGlueExpecation: Option[Failure]): Unit = {
    assumption.assumption.glueLine should be(assumptionGlueExpectation)
    assumption.baseline.glueLine should be(baselineGlueExpectation)
    assumption.time.glueLine should be(timeGlueExpectation)
    assumption.success should be(successGlueExpectation)
    assumption.failure should be(failureGlueExpecation)
  }

  it should "throw FileNotFoundException when unknown file name is given" in {
    a[FileNotFoundException] should be thrownBy {
      ExperimentParser.parserExperiment("unknown")
    }
  }


}
