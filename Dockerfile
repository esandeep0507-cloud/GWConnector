FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app

# copy maven files
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
