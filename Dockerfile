FROM openjdk:13-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
#ADD ${JAR_FILE} app.jar
ADD /target/home-office-api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=integration","-jar","/app.jar"]