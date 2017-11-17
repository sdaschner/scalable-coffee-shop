#!/bin/bash
cd ${0%/*}
set -eu

# jsonb
wget http://central.maven.org/maven2/org/apache/johnzon/johnzon-jsonb/1.0.0/johnzon-jsonb-1.0.0.jar -O modules/jsonb/johnzon-jsonb-1.0.0.jar
wget http://central.maven.org/maven2/org/apache/johnzon/johnzon-core/1.0.0/johnzon-core-1.0.0.jar -O modules/jsonb/johnzon-core-1.0.0.jar
wget http://central.maven.org/maven2/org/apache/johnzon/johnzon-mapper/1.0.0/johnzon-mapper-1.0.0.jar -O modules/jsonb/johnzon-mapper-1.0.0.jar
wget http://central.maven.org/maven2/org/apache/johnzon/jsonb-api/1.0.0/jsonb-api-1.0.0.jar -O modules/jsonb/jsonb-api-1.0.0.jar
wget http://central.maven.org/maven2/org/apache/kafka/kafka-clients/1.0.0/kafka-clients-1.0.0.jar -O modules/kafka/kafka-clients-1.0.0.jar

# kafka
wget http://central.maven.org/maven2/org/lz4/lz4-java/1.4/lz4-java-1.4.jar -O modules/kafka/lz4-java-1.4.jar
wget http://central.maven.org/maven2/org/xerial/snappy/snappy-java/1.1.4/snappy-java-1.1.4.jar -O modules/kafka/snappy-java-1.1.4.jar
wget http://central.maven.org/maven2/org/slf4j/slf4j-api/1.7.25/slf4j-api-1.7.25.jar -O modules/kafka/slf4j-api-1.7.25.jar

docker build -t localhost:5000/wildfly-kafka:1 .
