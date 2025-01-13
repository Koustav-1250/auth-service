# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jdk
# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY target/service.jar app.jar

# Expose the port the application runs on
EXPOSE 8050

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]