# Start with a base image containing Java runtime
FROM openjdk:11

# Marking port 8706 available to the world outside this container
EXPOSE 8706

ADD target/movieskart-config-service-1.0-SNAPSHOT.jar movieskart-config-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-config-1.0-SNAPSHOT.jar"]