# Data Collector from REST to Kafka - A Kafka Producer
by João Carlos (https://www.linkedin.com/in/joaocarlosvale/)

## Description:

It is a microservice (data collector) that:
1. consume http requests with JSON in a body:
```json
{
"first_name": <text>,
"last_name": <text>,
"age": <number>
}
```

2. read calculation seed from application properties file (it can be just a random
number 0-1)
3. send all information to the queue (Kafka producer)

## Technologies used:
* Java 11
* Spring
* Maven 
* Kafka

##Setting up Kafka's Docker
The files used by me you can find inside the docker folder in the project.

    #starts the container
    docker-compose -f docker-compose.yml up -d
    
    #verify running
    docker-compose ps
    
    #stop the cluster
    docker-compose down
    
    #acess the container kafka
    docker exec -it kafka /bin/sh
    
    #acess the container REDIS
    docker exec -it redis redis-cli

Commands inside the container - Examples (Kafka / Redis)
    
    #KAFKA
    /opt/kafka_2.13-2.6.0 # ./bin/kafka-topics.sh --zookeeper zookeper:2181 --list
    
    keys *  #REDIS

To connect:
    
    docker run --rm -v /var/run/docker.sock:/var/run/docker.sock -e HOST_IP=127.0.0.1 -i -t wurstmeister/kafka /bin/bash

## Commands:

To generate JAR:

    mvn clean package

To run:

    java -jar target/collector-0.0.1-SNAPSHOT.jar
    
To run tests:

    mvn test
