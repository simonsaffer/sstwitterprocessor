package com.sstwitterprocessor.events

import kafka.consumer.SimpleConsumer
import kafka.message.MessageAndOffset
import kafka.utils.Utils
import org.apache.kafka.common.requests.FetchRequest

/**
  * Created by simonsaffer on 2015-11-07.
  */
class KafkaTestConsumer(val clientId: String) {

  val consumer = new SimpleConsumer("localhost", 9092, 10000, 1024000, clientId);

  def fetch(offset: Long) = {
    //val fetchRequest = new FetchRequest(KafkaTestUtil.TEST_TOPIC, 0, offset, 1000000);

    // get the message set from the consumer and print them out
   /* val messages = consumer.fetch(fetchRequest)
    for(msg <- messages) {
      println("consumed: " + Utils.toString(msg.message().payload(), "UTF-8"))
      offset = msg.offset()
    }*/
  }

  def stop = {
    consumer.close()
  }

}
