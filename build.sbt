enablePlugins(ScalaJSPlugin)

name := "zarn"

scalaVersion := "2.12.8"

libraryDependencies += "me.shadaj" %%% "slinky-native" % "0.6.3"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.3"
libraryDependencies += "dev.zio" %%% "zio" % "1.0.0-RC17"

scalacOptions += "-P:scalajs:sjsDefinedByDefault"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

scalaJSModuleKind := ModuleKind.CommonJSModule
