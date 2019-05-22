name := "trackconverter"

version := "1.0"

lazy val `trackconverter` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.12.2"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
libraryDependencies ++= Seq("com.netaporter" %% "scala-uri" % "0.4.16", "net.logstash.logback" % "logstash-logback-encoder" % "5.2")
libraryDependencies += ws
libraryDependencies += ehcache

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

