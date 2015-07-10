
def commonSettings: Seq[Def.Setting[_]] = Seq(
  organization := "nl.codecentric",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.7"
)

lazy val root = project.in(file(".")).aggregate(api, core, web, examples).settings(commonSettings: _*)

lazy val api = project.settings(commonSettings: _*)

lazy val core = project.dependsOn(api).settings(commonSettings: _*)

lazy val web = project.dependsOn(core, examples).settings(commonSettings: _*)

lazy val examples = project.dependsOn(core).settings(commonSettings: _*)
