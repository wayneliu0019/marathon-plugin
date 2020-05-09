
organization in Global := "mesosphere.marathon"

name in Global := "example-plugins"

scalaVersion in Global := "2.12.7"

resolvers += Resolver.sonatypeRepo("releases")

lazy val plugins = project.in(file("."))
  .aggregate(executorimage)
  .dependsOn(executorimage)

lazy val executorimage = project

packAutoSettings

packExcludeJars := Seq("scala-.*\\.jar")
