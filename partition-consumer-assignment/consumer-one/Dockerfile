FROM java:8-jre
MAINTAINER Kara Satish Kumar <karasatishkumar@gmail.com>

ADD ./target/consumer-one-SNAPSHOT-1.0.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/consumer-one-SNAPSHOT-1.0.0.jar"]

EXPOSE 8071