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
Failure: Send a e-mail

Baseline: The current amount of registrations is 200
Assumption: The amount of registrations is increased with 3000
Time: In one year
Success: Sent cake to developers
```
In this example description above a product owner for instance describes what the functionality is expected to do in
production.

#### DSL keywords
The following DSL keywords are available.

##### Experiment:
The title of the experiment, a meaningful text for other to understand

##### Baseline:
The **Baseline:** keyword, is part of a assumption block and describes to the first measurement you want to do at the
beginning.

##### Assumption:
The **Assumption:** keyword, is part of a assumption block and describes the measurement you want to do after the time
defined in the **Time:** keyword.

##### Time:
The **Time:** keyword, is part of a assumption block and describes in how much time the assumption must be validated.

##### Success: (optional)
The optional **Success:** keyword, is part of the assumption block and describes to code when the assumption is met.

##### Failure: (optional)
The optional **Failure:** keyword, is part of the assumption block and describes to code when the assumption is not met.

### The definition
The definition is actual glue code that is written by the programmers to validate the assumptions. This code can for
example check the count of registrations in a database or check log files. There is no limit because the definitions
allow you to connect to any framework or any other services to get your data.

*A example definition written in scala*
```scala
import nl.codecentric.assumption.dsl.core.definition.BaseDefinition
import org.json4s.native.JsonMethods._
import scala.concurrent.duration._

import scalaj.http.{Http, HttpResponse}

/**
 * Created by hylke on 20/07/15.
 */
class RegistrationDefinition extends BaseDefinition {

  implicit lazy val formats = org.json4s.DefaultFormats

  "Get the current count of registrations" baseline (() => {
    val baselineCount = getRegistrationCount()
    println("Baseline count is", baselineCount)
  })

  "The amount of registrations is increased with 50" assumes (() => {
    val assumeCount = getRegistrationCount()
    println("Assume count is", assumeCount)
  })

  "In one week" time (1 minutes)

  "Send a cake to the development team" success (() => {
    println("Sending cake")
  })

  "Send a message to the product owner" failure (() => {
    println("Having a firm talk with the product owner")
  })

  def getRegistrationCount(): Integer = {
    val response: HttpResponse[String] = Http("http://33.33.33.60:8888/count")
      .timeout(connTimeoutMs = 1000, readTimeoutMs = 5000)
      .asString
    val json = parse(response.body);
    (json \ "count").extract[Integer]
  }

}
```