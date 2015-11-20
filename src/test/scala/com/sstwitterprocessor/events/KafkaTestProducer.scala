package com.sstwitterprocessor.events

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

/**
  * Created by simonsaffer on 2015-11-07.
  */
class KafkaTestProducer {

  val props = new Properties()
  props.put("broker.list", KafkaTestUtil.LOCALHOST_BROKER)
  props.put("serializer.class", "kafka.serializer.StringEncoder")
  val config: ProducerConfig = new ProducerConfig(props)

  def sendMessage(message: String) = {
    val producer = new Producer[String, String](config);
    producer.send(new KeyedMessage[String, String](KafkaTestUtil.TEST_TOPIC, message))
  }

}
