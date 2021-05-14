val scala3Version = "3.0.0-RC3"

lazy val root = project.in(file("."))
  .settings(
    name := "deconstructing-lambdas",
    version := "1.0.0",
    scalaVersion := scala3Version,

    // https://scalacenter.github.io/scala-3-migration-guide/docs/compiler-options/compiler-options-table.html
    // https://scalacenter.github.io/scala-3-migration-guide/docs/compiler-options/new-compiler-options.html
    scalacOptions ++= Seq(
      "-Yexplicit-nulls",
      "-language:strictEquality",
      "-Ykind-projector:underscores"
    )
  )
