# partition-consumer-assignment
[![Build Status](https://travis-ci.org/karasatishkumar/streaming-lab.svg?branch=master)](https://travis-ci.org/karasatishkumar/streaming-lab)

This project is to test various consumer partition strategies and rebalance concepts. To start with we have four producers, six consumers and one topic with four partitions.

#### Prerequisite

 - Java 1.8
 - Kafka
 - Docker
 - Maven
 - Git

#### Running the Application

1. Fork the project

1. Clone the source code of the forked one.

    `git clone https://github.com/karasatishkumar/streaming-lab.git`

2. Go to the partition-consumer-assignment folder.

    `cd partition-consumer-assignment`

3. Modify the IP address in the following files. In case of Mac use the IP address, but windows and linux users can use IP address as localhost.

    1. config-properties/consumer-*/dev/consumer-*.yml

        `bootstrap-servers: 192.168.1.9:9092`

    2. config-properties/producer-*/dev/producer-*.yml

        `bootstrap-servers: 192.168.1.9:9092`

    3. docker-compose.yml

        `KAFKA_ADVERTISED_HOST_NAME: 192.168.1.9`

4. Compile the code

    `mvn clean install -Dmaven.test.skip=true`

5. Bring up the docker container with applications

    `docker-compose up --build`

6. To remove the container

    `docker-compose down`

#### Service Registry (EurekaServer)

Check the following URL for all the registered services.
 
  `http://localhost:8761/`

#### Test Application

Testing is done through logging.

#### Contribution

Please raise issues and give code pull requests to improve the application.
