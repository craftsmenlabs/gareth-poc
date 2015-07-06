lazy val root = project

scalaVersion := "2.11.7"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies += "com.softwaremill.macwire" %% "macros" % "1.0.5"

libraryDependencies += "com.softwaremill.macwire" %% "runtime" % "1.0.5"