# Start with a base image containing Java runtime
FROM openjdk:11

# Marking port 8704 available to the world outside this container
EXPOSE 8704

ADD target/movieskart-orchestrator-service-1.0-SNAPSHOT.jar movieskart-orchestrator-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-orchestrator-service-1.0-SNAPSHOT.jar"]