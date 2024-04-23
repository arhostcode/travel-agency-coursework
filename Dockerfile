FROM openjdk:21
WORKDIR /app
COPY build/libs/travel-agency-coursework-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java", "-jar", "app.jar"]