lazy val root = project.in(file("."))
  .settings(
    name := "deconstructing-lambdas",
    version := "1.0.0",
    scalaVersion := "3.1.0",
    
    scalacOptions ++= Seq(
      "-encoding", "utf8",
      "-deprecation",
      "-explain",

      "-language:strictEquality",

      "-Yexplicit-nulls",
      "-Ykind-projector:underscores"
    )
  )
