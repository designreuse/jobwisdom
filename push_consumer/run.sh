#!/bin/bash
echo exec mvn clean install
mvn clean install

echo cd to target and start consumer.
cd ./target
nohup java -jar push_consumer-0.0.1-SNAPSHOT.jar &