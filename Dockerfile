FROM openjdk:11-jre-slim
ENV KAFKA_VERSION=3.7.1
ENV SCALA_VERSION=2.13
ENV KAFKA_HOME=/opt/kafka

RUN apt-get update && \
    apt-get install -y wget net-tools dnsutils && \
    apt-get clean

RUN wget https://downloads.apache.org/kafka/${KAFKA_VERSION}/kafka_${SCALA_VERSION}-${KAFKA_VERSION}.tgz -O /tmp/kafka.tgz && \
    tar -xzf /tmp/kafka.tgz -C /opt && \
    mv /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION} /opt/kafka && \
    rm /tmp/kafka.tgz

COPY config/server.properties /opt/kafka/config/
COPY config/zookeeper.properties /opt/kafka/config/
COPY scripts/start-kafka.sh /usr/bin/start-kafka.sh

CMD ["sh", "-c", "/usr/bin/start-kafka.sh"]
