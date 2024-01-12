FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/menu-service-0.0.1-SNAPSHOT.jar menu-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/menu-service-0.0.1-SNAPSHOT.jar"]