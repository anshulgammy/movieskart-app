# Start with a base image containing Java runtime
FROM openjdk:17

# Marking port 8080 available to the world outside this container
EXPOSE 8080

ADD target/movieskart-gateway-service-1.0-SNAPSHOT.jar movieskart-gateway-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-gateway-service-1.0-SNAPSHOT.jar"]