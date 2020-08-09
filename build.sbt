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
libraryDependencies += "org.mockito" % "mockito-core" % "2.10.0" % "test"

// Compile the project before generating Eclipse files, so
// that generated .scala or .class files for views and routes are present

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java           // Java project. Don't expect Scala IDE
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)  // Use .class files instead of generated .scala files for views and routes

