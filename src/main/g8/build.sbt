resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val commonSettings = Seq(
// Refine scalac params from tpolecat
  scalacOptions --= Seq(
    "-Xfatal-warnings"
  )
)

lazy val catsDeps = libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % Version.cats
)

lazy val zioDeps = libraryDependencies ++= Seq(
  "dev.zio" %% "zio"              % Version.zio,
  "dev.zio" %% "zio-test"         % Version.zio % "test",
  "dev.zio" %% "zio-test-sbt"     % Version.zio % "test",
  "dev.zio" %% "zio-interop-cats" % Version.zioInteropCats
)

lazy val root = (project in file("."))
  .settings(
    organization := "Neurodyne",
    name := "top",
    version := "0.0.1",
    scalaVersion := "2.13.2",
    maxErrors := 3,
    commonSettings,
    zioDeps,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

// Aliases
addCommandAlias("rel", "reload")
addCommandAlias("com", "all compile test:compile it:compile")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")

scalafixDependencies in ThisBuild += "com.nequissimus" %% "sort-imports" % "0.5.0"
