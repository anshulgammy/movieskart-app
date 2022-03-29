# Start with a base image containing Java runtime
FROM openjdk:17

# Marking port 8705 available to the world outside this container
EXPOSE 8705

ADD target/movieskart-discovery-service-1.0-SNAPSHOT.jar movieskart-discovery-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-discovery-service-1.0-SNAPSHOT.jar"]