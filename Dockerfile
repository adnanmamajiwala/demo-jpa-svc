# FROM openjdk:8-jdk-alpine

# WORKDIR /app

# ARG JAR_FILE=target/demo-jpa-svc-0.0.1-SNAPSHOT.jar
# ADD ${JAR_FILE} app.jar

# ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]

FROM openjdk:11-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
