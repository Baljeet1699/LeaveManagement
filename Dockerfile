#FROM openjdk:18-alpine
#WORKDIR /app
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} /app/leavemanagement.jar
#EXPOSE 8081
#ENTRYPOINT ["java","-jar","leavemanagement.jar"]


# FIRST STAGE
FROM openjdk:17 AS build
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/leavemanagement.jar


#SECOND STAGE
FROM openjdk:17-slim
COPY --from=build /app/leavemanagement.jar /app/leavemanagement.jar
ENTRYPOINT ["java","-jar","/app/leavemanagement.jar"]
