FROM openjdk:21-jdk-slim

ENV KAFKA_VERSION=3.7.1
ENV SCALA_VERSION=2.13
ENV KAFKA_HOME=/opt/kafka
ENV PATH=$PATH:$KAFKA_HOME/bin

RUN apt-get update && \
    apt-get install -y wget tar && \
    rm -rf /var/lib/apt/lists/*

RUN wget https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz && \
    tar -xzf kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -C /opt && \
    mv /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} ${KAFKA_HOME} && \
    rm kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz

EXPOSE 2181 9092

COPY config/zookeeper.properties /opt/kafka/config/
COPY config/server.properties /opt/kafka/config/

CMD ["sh", "-c", "zkServer.sh start-foreground & kafka-server-start.sh /opt/kafka/config/server.properties"]
