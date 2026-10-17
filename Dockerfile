# 1. build the jar ( no local Java / Maven needed )
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# download dependencies first, so they are cached when only the code changes
COPY pom.xml .
RUN mvn -q dependency:go-offline

COPY src ./src
RUN mvn -q package -DskipTests


# 2. run the jar on a small Java 17 runtime image
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/target/ticketing-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
