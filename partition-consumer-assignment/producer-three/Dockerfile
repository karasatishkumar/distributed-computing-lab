FROM java:8-jre
MAINTAINER Kara Satish Kumar <karasatishkumar@gmail.com>

ADD ./target/producer-three-SNAPSHOT-1.0.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/producer-three-SNAPSHOT-1.0.0.jar"]

EXPOSE 8083