package nl.codecentric.assumption.web.model

/**
 * Created by hylke on 07/07/15.
 */
case class Experiment(name: String, assumption: Assumption)

case class Assumption(assumption: String, baseline: String, time: String)
