name := "ss-twitter-processor"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-stream-experimental"  % "1.0",
	"com.typesafe.akka" %% "akka-http-experimental"    % "1.0",
  "org.apache.kafka"  %% "kafka"                     % "0.8.2.2", //exclude("log4j", "log4j") exclude("org.slf4j","slf4j-log4j12")
  "com.hunorkovacs"   %  "koauth_2.11"               % "1.1.0",
	"org.json4s"        %% "json4s-native"             % "3.3.0",
  "org.apache.spark"  %  "spark-core_2.11"           % "1.5.1",
	"org.apache.spark"  %  "spark-streaming_2.11"      % "1.5.1",
	"org.apache.spark"  %  "spark-streaming-kafka_2.11"% "1.5.1",
  "com.holdenkarau"   %  "spark-testing-base_2.11"   % "1.5.1_0.2.1",
  "org.scalatest"     %% "scalatest"                 % "2.2.5" % "test"
)
