FROM openjdk:8-jdk-alpine

EXPOSE 9999

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} service-authorization-server.jar

ENTRYPOINT ["java","-jar","/service-authorization-server.jar"]