name := "akka-sample-twitter-streaming"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-stream-experimental" % "1.0",
	"com.typesafe.akka" %% "akka-http-experimental" % "1.0",
  "org.apache.kafka" %% "kafka"  % "0.8.2.2", /*exclude("log4j", "log4j") exclude("org.slf4j","slf4j-log4j12")*/
	"com.hunorkovacs" %% "koauth" % "1.1.0",
	"org.json4s" %% "json4s-native" % "3.3.0"
)
