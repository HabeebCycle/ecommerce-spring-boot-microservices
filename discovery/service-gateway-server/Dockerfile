FROM openjdk:8-jdk-alpine

EXPOSE 8080

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} service-gateway-server.jar

ENTRYPOINT ["java","-jar","/service-gateway-server.jar"]