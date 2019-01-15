# user-registration (spring-kafka-microservice)

    This is a repository for building a Spring Boot microservice(user-registration) using Eureka, Spring Config and Apache Kafka.
User registration happens via a microservice which puts the user information to database as well as kafka queue. Email service pics the information from queue and sends a registration success mail.
To make this developer friendly, we have made use of docker for run all the services

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

    2. Go to the user-registration folder.

        `cd user-registration`

    3. Modify the IP address in the following files. In case of Mac use the IP address, but windows and linux users can use IP address as localhost.

        1. ConfigProperties/email-service/dev/email-service.yml

            `bootstrap-servers: 192.168.43.77:9092`

        2. ConfigProperties/user-service/dev/user-service.yml

            `bootstrap-servers: 192.168.43.77:9092`

        3. docker-compose.yml

            `KAFKA_ADVERTISED_HOST_NAME: 192.168.43.77`

    4. Update SMTP UserName and Password in user-registration/ConfigProperties/email-service/dev/email-service.yml

        `username: xxx@xxx.com`
        `password: xxxxxx`

    5. Compile the code

        `mvn clean install -Dmaven.test.skip=true`

    6. Bring up the docker container with applications

        `docker-compose up --build`

    7. To remove the container

        `docker-compose down`

#### Service Registry (EurekaServer)

    - Check
        - http://localhost:8761/

#### Test Application
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

#### Contribution

    Please raise issues and give code pull requests to improve the application.
