package com.sstwitterprocessor.processing

import com.holdenkarau.spark.testing.{RDDGenerator, SharedSparkContext}
import org.scalatest.FunSuite

/**
  * Created by simonsaffer on 2015-11-06.
  */
class TwitterProcessorTest extends FunSuite with SharedSparkContext {

  /*test("really simple transformation") {
    val input = List("hi", "hi cloudera", "bye")
    val expected = List(List("hi"), List("hi", "cloudera"), List("bye"))
    assert(SampleRDD.tokenize(sc.parallelize(input)).collect().toList === expected)
  }

  test("really simple transformation") {
    val input = List(List("hi"), List("hi holden"), List("bye"))
    val expected = List(List("hi"), List("hi", "holden"), List("bye"))
    testOperation[String, String](input, tokenize _, expected, useSet = true)
  }

  test("map should not change number of elements") {
    forAll(RDDGenerator.genRDD[String](sc)){
      rdd => rdd.map(_.length).count() == rdd.count()
    }
  }*/


}
