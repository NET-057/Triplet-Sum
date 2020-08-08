name := """credit"""
organization := "creditSaison"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.3"

libraryDependencies += guice

libraryDependencies += jdbc
libraryDependencies += javaJdbc % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.44"
libraryDependencies += "com.google.code.gson" % "gson" % "2.3.1"

// https://mvnrepository.com/artifact/org.mockito/mockito-all
libraryDependencies += "org.mockito" % "mockito-all" % "1.10.19" % Test
