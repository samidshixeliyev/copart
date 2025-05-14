
FROM gradle:8.13-jdk21 as builder

WORKDIR /copart

COPY gradle/wrapper /copart/gradle/wrapper
COPY settings.gradle /copart/settings.gradle
COPY build.gradle /copart/build.gradle
COPY src /copart/src

RUN gradle build -x test

FROM openjdk:25-ea-21-jdk-oracle

WORKDIR /copart

COPY --from=builder /copart/build/libs/*.jar copart.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "copart.jar"]
