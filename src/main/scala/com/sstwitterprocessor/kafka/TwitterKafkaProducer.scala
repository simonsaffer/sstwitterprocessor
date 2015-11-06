package com.sstwitterprocessor.kafka

import java.util.Properties

import kafka.producer.{KeyedMessage, Producer, ProducerConfig}

/**
  * Created by simonsaffer on 2015-11-05.
  */
class TwitterKafkaProducer(val topic: String) {

  private val props = new Properties();
  props.put("metadata.broker.list", "localhost:9092");
  props.put("zk.connect", "localhost:2181");
  props.put("serializer.class", "kafka.serializer.StringEncoder");
  props.put("request.required.acks", "1");

  private val config = new ProducerConfig(props)

  private val producer = new Producer[String, String](config)

  def send(message: String): Unit = {
    val keyedMessage: KeyedMessage[String, String] = new KeyedMessage[String, String](topic, message)
    producer.send(keyedMessage)
  }

}
