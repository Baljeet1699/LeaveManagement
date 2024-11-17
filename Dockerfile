
#FIRST STAGE
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app

# copy pom.xml and install dependencies first to leverage the cache
COPY pom.xml .
RUN mvn dependency:go-offline

# copy the rest of the application and build it
COPY src /app/src
RUN mvn clean package -DskipTests

# SECOND STAGE: Create the final image with the JAR
FROM openjdk:17-slim
COPY --from=build /app/target/*.jar /app/leavemanagement.jar

ENTRYPOINT ["java", "-jar", "/app/leavemanagement.jar"]


## SECOND STAGE
#FROM openjdk:17 AS build
#WORKDIR /app
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} /app/leavemanagement.jar
#
#
##THIRD STAGE
#FROM openjdk:17-slim
#COPY --from=build /app/leavemanagement.jar /app/leavemanagement.jar
#ENTRYPOINT ["java","-jar","/app/leavemanagement.jar"]
