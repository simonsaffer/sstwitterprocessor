package com.sstwitterprocessor.processing

import com.sstwitterprocessor.config.ApplicationConfig
import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by simonsaffer on 2015-11-06.
  */
class TwitterProcessor {

  val conf = new SparkConf().setMaster("local[2]").setAppName("TwitterWordCount")
  val ssc = new StreamingContext(conf, Seconds(1))
  val topicsSet = ApplicationConfig.Kafka.topics
  val kafkaParams = Map[String, String]("metadata.broker.list" -> ApplicationConfig.Kafka.brokers)

  def processMessages(): Unit = {
    val messages = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicsSet)
    doProcess(messages)
  }

  def doProcess(input: InputDStream[(String, String)]) = {
    // Get the lines, split them into words, count the words and print
    val lines = input.map(_._2)
    val words = lines.flatMap(_.split(" "))
    val count: DStream[WordCount] = TwitterProcessor.count(words)
    count.foreachRDD(wc => println(wc.collect().mkString("", "\n", "\n")))
  }

  def start(): Unit = {
    ssc.start()
    ssc.awaitTermination()
  }

}

case class WordCount(val word: String, val count: Long)

object TwitterProcessor {

  def count(words: DStream[String]): DStream[WordCount] = {
    words.map(x => (x, 1L)).reduceByKey(_ + _).map(wc => WordCount(wc._1, wc._2))
  }
}
