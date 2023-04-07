#FROM adoptopenjdk/openjdk11:alpine-jre
FROM openjdk:11

EXPOSE 8080

ARG JAR_FILE=target/security-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
