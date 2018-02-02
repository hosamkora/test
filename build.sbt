name := """Test"""
organization := "com.test"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies ++= Seq(
  javaJdbc,
  ehcache,
  javaWs,
  "org.xerial" % "sqlite-jdbc" % "3.8.6"
)

fork in run := false
resolvers += "SQLite-JDBC Repository" at "https://oss.sonatype.org/content/repositories/snapshots"
