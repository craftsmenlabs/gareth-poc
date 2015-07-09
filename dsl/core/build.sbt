unmanagedClasspath in Runtime += baseDirectory.value / "experiment/src/scala"

unmanagedSourceDirectories in Test += baseDirectory.value / "src/experiment/scala"

resourceDirectory in Test := baseDirectory.value / "src/experiment/resources"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"

libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value

