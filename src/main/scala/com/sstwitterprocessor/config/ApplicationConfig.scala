package com.sstwitterprocessor.config

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by simonsaffer on 2015-11-06.
  */
object ApplicationConfig {

  private val config = ConfigFactory.load()

  private val credentials = ConfigFactory.load("credentials.properties")

  object Credentials {
    val consumerKey = credentials.getString("consumerKey")
    val consumerSecret = credentials.getString("consumerSecret")
    val accessToken = credentials.getString("accessToken")
    val accessTokenSecret = credentials.getString("accessTokenSecret")
  }

  object Kafka {
    import scala.collection.JavaConversions._
    val topics: Set[String] = Set(config.getString("topics"))
    val brokers: String = config.getString("brokers")
  }

}
