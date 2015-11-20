package com.sstwitterprocessor.events

import java.util.Properties

import org.apache.zookeeper.server.{ServerConfig, ZooKeeperServerMain}
import org.apache.zookeeper.server.quorum.QuorumPeerConfig

/**
  * Created by simonsaffer on 2015-11-07.
  */
class KafkaTestZookeeper(zooKeeperProperties: Properties) {

  val quorumConfig = new QuorumPeerConfig
  quorumConfig.parseProperties(zooKeeperProperties)

  val zooKeeperServer = new ZooKeeperServerMain

  val serverConfig = new ServerConfig
  serverConfig.readFrom(quorumConfig)

  val zooKeeperThread = new Thread() {
    override def run = {
      zooKeeperServer.runFromConfig(serverConfig)
    }
  }.start()

}
