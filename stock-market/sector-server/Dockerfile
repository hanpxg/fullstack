FROM openjdk:8-jdk-alpine
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
EXPOSE 8858
ENTRYPOINT ["java","-jar","/app.jar"]