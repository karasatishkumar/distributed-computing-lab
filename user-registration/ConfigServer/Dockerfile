FROM java:8-jre
MAINTAINER Kara Satish Kumar <karasatishkumar@gmail.com>

ADD ./target/ConfigServer-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/ConfigServer-0.0.1-SNAPSHOT.jar"]

HEALTHCHECK --interval=10s --timeout=10s CMD curl -f http://localhost:8888/health || exit 1

EXPOSE 8888