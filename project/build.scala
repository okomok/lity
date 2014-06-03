import sbt._
import Keys._

object build extends Build {
    lazy val theSettings = Project.defaultSettings ++ Seq(
        organization := "com.github.okomok"
        , version := "0.1.0-SNAPSHOT"
        , scalaVersion := "2.11.0"

        , scalacOptions ++=
            Seq("-unchecked", "-deprecation", "-feature")
            ++ Seq("-language", "experimental.macros")

        ,libraryDependencies ++= Seq(
            "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"
            , "junit" % "junit" % "4.4" % "test"
        )

        , parallelExecution := false
        , publishArtifact in packageDoc := false
//        , offline := true
    )

    lazy val root = Project(
        "lity-root"
        , file(".")
        , settings = theSettings ++ Seq(
            publish := ()
            , publishLocal := ()
        )
    ) aggregate(core)

    lazy val core = Project(
        "lity-core"
        , file("core")
        , settings = theSettings ++ Seq(
            libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-reflect" % _)
        )
    )
}
