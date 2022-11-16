lazy val root = project.in(file("."))
  .settings(
    name := "deconstructing-lambdas",
    version := "1.0.0",
    scalaVersion := "3.2.1",
    
    scalacOptions ++= Seq(
      "-language:strictEquality",
      "-Ykind-projector:underscores"
    )
  )
