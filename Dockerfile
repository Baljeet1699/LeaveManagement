FROM openjdk:18-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/leavemanagement.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","leavemanagement.jar"]
