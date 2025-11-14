FROM maven:3.9-amazoncorretto-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn -B -q dependency:go-offline

COPY src ./src

# Run tests – build fails here if tests fail
RUN mvn -B test

# Build the fat JAR (or whatever your packaging is)
RUN mvn -B package