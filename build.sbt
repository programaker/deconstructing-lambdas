lazy val root = project.in(file("."))
  .settings(
    name := "deconstructing-lambdas",
    version := "1.0.0",
    scalaVersion := "3.0.0",
    
    scalacOptions ++= Seq(
      "-Yexplicit-nulls",
      "-language:strictEquality"
    )
  )
