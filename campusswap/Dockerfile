FROM eclipse-temurin:17-jdk-alpine
COPY target/campusswap-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]