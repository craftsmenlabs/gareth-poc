package nl.codecentric.assumption.dsl.api.model

case class Experiment(experimentName: String, assumptions: List[AssumptionBlock])

case class DescriptionBlock(description: String)

abstract class AssumptionPart(glueLine: String)

case class AssumptionBlock(baseline: Baseline, assumption: Assumption, time: Time) {
  var baselineTime: Option[Long] = None
  var assumptionTime: Option[Long] = None
}

case class Assumption(glueLine: String) extends AssumptionPart(glueLine)

case class Time(glueLine: String) extends AssumptionPart(glueLine)

case class Baseline(glueLine: String) extends AssumptionPart(glueLine)