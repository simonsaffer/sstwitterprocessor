package com.sstwitterprocessor.processing

import java.nio.file.Files

import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.util.TestManualClock
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkContext, SparkConf}
import org.scalatest.concurrent.Eventually
import org.scalatest.{Matchers, GivenWhenThen, BeforeAndAfter, FlatSpec}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

import language.postfixOps

/**
  * Created by simonsaffer on 2015-11-13.
  */
class TwitterProcessorSpec extends FlatSpec with GivenWhenThen with BeforeAndAfter with Matchers with Eventually {

  private val master: String = "localhost[2]"
  private val appName: String = "twitter-processor-spec"
  private val batchDuration = Seconds(1)
  private val checkPointDir = Files.createTempDirectory(appName).toString

  private var sc: SparkContext = _
  private var ssc: StreamingContext = _

  before {

    val conf = new SparkConf()
      .setMaster(master)
      .setAppName(appName)

    ssc = new StreamingContext(conf, batchDuration)
    ssc.checkpoint(checkPointDir)

    sc = ssc.sparkContext
  }

  after {
    if (ssc != null) ssc.stop()
  }

  "Sample set" should "be counted" in {
    Given("streaming context is initialized")
    val lines = mutable.Queue[RDD[String]]()

    var results = ListBuffer.empty[Array[WordCount]]

    val clock = new TestManualClock

    TwitterProcessor.count(ssc.queueStream(lines))
    
    /*WordCount.count(ssc.queueStream(lines), windowDuration, batchDuration) { (wordsCount: RDD[WordCount], time: Time) =>
      results += wordsCount.collect()
    }*/

    ssc.start()

    When("first set of words queued")
    lines += sc.makeRDD(Seq("a", "b"))

    Then("words counted after first slide")
    clock.advance(batchDuration.milliseconds)
    eventually(timeout(1 second)) {
      results.last should equal(Array(
        WordCount("a", 1),
        WordCount("b", 1)))
    }

    When("second set of words queued")
    lines += sc.makeRDD(Seq("b", "c"))

    Then("words counted after second slide")
    clock.advance(batchDuration.milliseconds)
    eventually(timeout(1 second)) {
      results.last should equal(Array(
        WordCount("a", 1),
        WordCount("b", 2),
        WordCount("c", 1)))
    }

    When("nothing more queued")

    Then("word counted after third slide")
    clock.advance(batchDuration.milliseconds)
    eventually(timeout(1 second)) {
      results.last should equal(Array(
        WordCount("a", 0),
        WordCount("b", 1),
        WordCount("c", 1)))
    }

    When("nothing more queued")

    Then("word counted after fourth slide")
    clock.advance(batchDuration.milliseconds)
    eventually(timeout(1 second)) {
      results.last should equal(Array(
        WordCount("a", 0),
        WordCount("b", 0),
        WordCount("c", 0)))
    }
  }

}
