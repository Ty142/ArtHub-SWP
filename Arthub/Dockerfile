FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/ArtHub-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7233
ENTRYPOINT ["java", "-jar", "app.jar"]
