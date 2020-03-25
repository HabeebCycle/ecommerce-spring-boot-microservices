FROM openjdk:8-jdk-alpine

EXPOSE 8761

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} service-discovery-server.jar

ENTRYPOINT ["java","-jar","/service-discovery-server.jar"]