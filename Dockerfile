FROM openjdk:8-jdk-alpine

ADD build/libs/spring-boot-webflux-reactive-mongo-0.1.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]