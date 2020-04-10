FROM openjdk:8-jdk-alpine

EXPOSE 8888

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} service-config-server.jar

ENTRYPOINT ["java","-jar","/service-config-server.jar"]