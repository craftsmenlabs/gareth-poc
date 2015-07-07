lazy val root = project

scalaVersion := "2.11.7"

unmanagedClasspath in Test += baseDirectory.value / "experiment"

unmanagedSourceDirectories in Test += baseDirectory.value / "src/experiment/scala"

resourceDirectory in Test := baseDirectory.value / "src/experiment/resources"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value