FROM java:8-jre
MAINTAINER Kara Satish Kumar <karasatishkumar@gmail.com>

ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/EurekaServer-0.0.1-SNAPSHOT.jar"]

HEALTHCHECK --interval=10s --timeout=5s CMD curl -f http://localhost:8761/health || exit 1

EXPOSE 8761