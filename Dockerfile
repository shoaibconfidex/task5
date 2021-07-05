FROM openjdk:11.0-jre
COPY build/libs/dvd-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]