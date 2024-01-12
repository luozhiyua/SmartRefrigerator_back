FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY sentinel-dashboard-1.6.3.jar sentinel-dashboard-1.6.3.jar
ENTRYPOINT ["java", "-jar", "/sentinel-dashboard-1.6.3.jar", "-Dserver.port=8080", "-Dcsp.sentinel.app.type=1", "-Dcsp.sentinel.dashboard.server=localhost:8080", "-Dproject.name=sentinel-dashboard"]
