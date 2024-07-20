#!/bin/bash

export KAFKA_HEAP_OPTS="-Xmx1G -Xms512M"
export ZOOKEEPER_HEAP_OPTS="-Xmx512M -Xms256M"

/opt/kafka/bin/zookeeper-server-start.sh /opt/kafka/config/zookeeper.properties &
/opt/kafka/bin/kafka-server-start.sh /opt/kafka/config/server.properties
