# Start with a base image containing Java runtime
FROM openjdk:17

# Marking port 8701 available to the world outside this container
EXPOSE 8701

ADD target/movieskart-metadata-service-1.0-SNAPSHOT.jar movieskart-metadata-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-metadata-service-1.0-SNAPSHOT.jar"]