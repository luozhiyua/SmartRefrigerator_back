FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/food-service-0.0.1-SNAPSHOT.jar food-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/food-service-0.0.1-SNAPSHOT.jar"]