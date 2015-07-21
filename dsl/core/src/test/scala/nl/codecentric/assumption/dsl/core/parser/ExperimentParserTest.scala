package nl.codecentric.assumption.dsl.core.parser

import java.io.FileNotFoundException

import nl.codecentric.assumption.dsl.api.model.AssumptionBlock
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
      , "5 days")

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
      , "5 days1")

    validateAssumption(assumptions(1)
      , "By showing a caps-lock warning the failed logins are reduced by 5%2"
      , "Failed logins are read from log2"
      , "5 days2")

  }

  private def validateAssumption(assumption: AssumptionBlock, assumptionGlueExpectation: String, baselineGlueExpectation: String, timeGlueExpectation: String): Unit = {
    assumption.assumption.glueLine should be(assumptionGlueExpectation)
    assumption.baseline.glueLine should be(baselineGlueExpectation)
    assumption.time.glueLine should be(timeGlueExpectation)
  }

  it should "throw FileNotFoundException when unknown file name is given" in {
    a[FileNotFoundException] should be thrownBy {
      ExperimentParser.parserExperiment("unknown")
    }
  }


}
