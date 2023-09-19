ThisBuild / version := "0.0.1"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name             := "Lexer",
    idePackagePrefix := Some("com.graphene"),
    libraryDependencies ++= List(
      "org.typelevel"     %% "cats-core"     % "2.9.0" ,
      "org.typelevel"     %% "cats-effect"   % "3.5.0" % Test,
//      "org.typelevel"     %% "kittens"       % "3.0.0",
      "dev.optics"        %% "monocle-core"  % "3.2.0",
      "dev.optics"        %% "monocle-macro" % "3.2.0",
//      "com.github.j-mie6" %% "parsley"       % "4.3.1"
    )
  )
