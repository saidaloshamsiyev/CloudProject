FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
LABEL authors="Victus"
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
