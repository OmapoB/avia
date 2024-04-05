FROM openjdk:22-ea-21-jdk-slim-bullseye
COPY app.jar app.jar
CMD ["java", "-jar", "/app.jar"]