# Gareth
Gareth is a framework that allows you to do automatic validation of your business goals that are part of a user story.

## Why
We believe that in the trend of delivering software fast using continuous delivery and DevOps that the delivery of
software is mainly focussed on delivering functionality and not on **achieving business goals**. We want to create and
delivery tools that helps you to automate and validate the value of your software from a business perspective.

## How
We want to offer a DSL that allows you to describe your business goals, this DSL can automatically be parsed and glued
to code. This code run in production and allows you to set baselines and validate your goals automatically.

## What
We want to deliver these parts:
- A dashboard that gives you a overview of your experiments (goals you want to achieve)
- A DSL
- Parsers for this DSL in different languages. (currently Scala)


## Key benefits
- Agility: detect and act upon unused code
- Act upon success and failure automatically
- Gain maturity of user-stories

## Example
A example of the framework given below:

### The DSL

*A experiment as part of a user story, in this experiment the the registrations are expected to increase with 50 in one
week and with 300 within a month. (A experiment with two assumptions)*
```
Experiment: Increased registrations

Baseline: The current amount of registrations is 200
Assumption: The amount of registrations is increased with 50
Time: In one week

Baseline: The current amount of registrations is 200
Assumption: The amount of registrations is increased with 300
Time: In one month
```
In this example description above a product owner for instance describes what the functionality is expected to do in
production.

### The definition
The definition is actual glue code that is written by the programmers to validate the assumptions. This code can for
example check the count of registrations in a database or check log files. There is no limit because the definitions
allow you to connect to any framework or any other services to get your data.

*A example definition written in scala*
```scala
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
      println("Experiment Fail")
    }

  })

  "Example time" time (60 seconds)
}
```