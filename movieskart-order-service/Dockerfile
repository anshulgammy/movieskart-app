# Start with a base image containing Java runtime
FROM openjdk:11

# Marking port 8703 available to the world outside this container
EXPOSE 8703

ADD target/movieskart-order-service-1.0-SNAPSHOT.jar movieskart-order-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-orchestrator-order-1.0-SNAPSHOT.jar"]