FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/register-0.0.1-SNAPSHOT.jar register-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/register-0.0.1-SNAPSHOT.jar"]