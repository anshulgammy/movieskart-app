# Start with a base image containing Java runtime
FROM openjdk:11

# Marking port 8702 available to the world outside this container
EXPOSE 8702

ADD target/movieskart-inventory-service-1.0-SNAPSHOT.jar movieskart-inventory-service-1.0-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","movieskart-inventory-service-1.0-SNAPSHOT.jar"]