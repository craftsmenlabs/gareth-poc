scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

mainClass := Some("nl.codecentric.assumption.web.Boot")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  val json4sversion = "3.2.11"
  Seq(
    "io.spray" %% "spray-can" % sprayV,
    "io.spray" %% "spray-routing" % sprayV,
    "io.spray" %% "spray-json" % "1.3.2",
    "io.spray" %% "spray-testkit" % sprayV % "test",
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-testkit" % akkaV % "test",
    "org.specs2" %% "specs2-core" % "2.3.11" % "test",
    "org.scalaj" %% "scalaj-http" % "1.1.5",
    "org.json4s" %% "json4s-native" % json4sversion
  )
}

Revolver.settings