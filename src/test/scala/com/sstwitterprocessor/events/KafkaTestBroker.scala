package com.sstwitterprocessor.events

import java.util.Properties

import kafka.producer.{KeyedMessage, ProducerConfig, Producer}
import kafka.server.{KafkaServer, KafkaConfig}

import scalaz.std.int

/**
  * Created by simonsaffer on 2015-11-06.
  */
class KafkaTestBroker(val topics: Seq[String], val properties: Properties){

  val kafkaConfig = new KafkaConfig(properties)
  val kafkaServer = new KafkaServer(kafkaConfig)
  kafkaServer.startup()

  def stop() = {
    kafkaServer.shutdown()
  }

  private def createProperties(logDir: String, port: Int, brokerId: Int): Properties = {
    val properties = new Properties()
    properties.put("port", port.toString)
    properties.put("brokerid", brokerId.toString)
    properties.put("log.dir", logDir)
    properties.put("enable.zookeeper", "false")
    properties
  }
}
