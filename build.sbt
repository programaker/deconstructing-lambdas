val scala3Version = "3.0.0-RC2"

lazy val root = project.in(file("."))
  .settings(
    name := "deconstructing-lambdas",
    version := "1.0.0",
    scalaVersion := scala3Version
  )
