# Use an OpenJDK runtime as a base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/MacedonianVineyardJourney-0.0.1-SNAPSHOT.jar /app/

# Specify the command to run on container startup
CMD ["java", "-jar", "MacedonianVineyardJourney-0.0.1-SNAPSHOT.jar"]
