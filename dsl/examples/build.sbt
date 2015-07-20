libraryDependencies ++= {
  val json4sversion = "3.2.11"
  Seq(
    "org.scalaj" %% "scalaj-http" % "1.1.5",
    "org.json4s" %% "json4s-native" % json4sversion,
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  )
}