FROM openjdk:8-jre-alpine
ADD target/mailsender-0.0.1-SNAPSHOT.jar mailsender-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "mailsender-0.0.1-SNAPSHOT.jar"]
