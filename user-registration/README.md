# user-registration (spring-kafka-microservice)
This is a repository for building a Spring Boot microservice(user-registration) using Eureka, Spring Config and Apache Kafka. 
User registration happens via a microservice which puts the user information to database as well as kafka queue. Email service pics the information from queue and sends a registration success mail.

#### Prerequisite
- Java 1.8
- Kafka
- docker 

#### Kafka
Follow the following article to setup kafka.

https://github.com/karasatishkumar/streaming-lab/wiki/Install-Apache-Kafka-in-Docker-Container

We need to set KAFKA_ADVERTISED_HOST_NAME to the docker machine IP otherwise the Kafka broker is not visible and our micro services cannot connect to Kafka broker. We need to start kafka using the following command.
    
    `docker run -d --name kafka --network kafka-net --publish 9092:9092 --publish 7203:7203 --env KAFKA_ADVERTISED_HOST_NAME=192.168.0.6 --env ZOOKEEPER_IP=zookeeper ches/kafka`

KAFKA_ADVERTISED_HOST_NAME is the machine IP on which you want to setup kafka.


#### Service Registry (EurekaServer)
- Build/Run
  - mvn clean install
  - java –jar target/EurekaServer-0.0.1-SNAPSHOT.jar
- Check
  - http://localhost:8761/

#### Email Service Config
Configuration files need to be checked in to git. Config Server reads the configurations from git repository.  
  
#### Config Server (ConfigServer)
  - Update properties 
    - SET **search-paths & git-url** in /spring-kafka-microservice/ConfigServer/src/main/resources/application.yml
    - SET **USERNAME** in spring-kafka-microservice/EmailServiceConfigProperties/email-service/dev/email-service.yml
    - SET **PASSWORD** in spring-kafka-microservice/EmailServiceConfigProperties/email-service/dev/email-service.yml
  - Build/Run
    - mvn clean install
    - java –jar target/ConfigServer-0.0.1-SNAPSHOT.jar
- Check
    - http://localhost:8888/email-service/dev

#### User Service (UserAccount)
- Run
  - mvn clean install
  - java -jar target/UserAccount-0.0.1-SNAPSHOT.jar
- Check
  - http://localhost:8081/member -- expect 0 records returned

#### Email Service (EmailService)
- Run
  - mvn clean install
  - java -jar target/EmailService-0.0.1-SNAPSHOT.jar
  
#### Test Microservice
Once each micro service is setup and started correctly, you can test the complete flow by
1. Create a new user by calling url – POST http://localhost:8081/register
    
    `{
       "id": 1,
       "userName": "karasatishkumar@gmail.com",
       "password": "password"
     }`
     
2. Verify that the new user is created.
3. You can also verify the user by calling – GET http://localhost:8081/member 
4. Verify that registration success email was received at your email address
