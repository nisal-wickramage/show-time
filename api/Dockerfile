FROM openjdk:17-jdk-alpine as builder
WORKDIR /service

COPY build.gradle settings.gradle gradlew gradlew.bat /service/
COPY gradle /service/gradle
COPY . .
RUN ./gradlew clean build

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /service/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]