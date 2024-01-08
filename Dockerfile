FROM maven:3-openjdk-17-slim as builder

WORKDIR /app
COPY . .
RUN mvn package

FROM openjdk:17-alpine as runner

WORKDIR /app
COPY --from=builder /app/springboot-rest-demo-ws/target/*.jar .
COPY --from=builder /app/springboot-rest-demo-config/src/main/resources/springboot-rest-demo.yml .
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "-Dspring.config.location=springboot-rest-demo.yml", "springboot-rest-demo-ws-1.0.0-SNAPSHOT.jar"]
