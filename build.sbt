ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "inscape-etl"
  )

libraryDependencies ++= {
  List(
    "org.apache.spark" %% "spark-sql" % "3.2.1" % "provided",
    "org.typelevel" %% "cats-core" % "2.7.0",
    "org.scalacheck" %% "scalacheck" % "1.16.0" % Test
  )
}


