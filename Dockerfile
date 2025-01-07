# Builder stage
FROM bellsoft/liberica-runtime-container:jdk-21-stream-musl as builder
WORKDIR /app
ADD . /app
WORKDIR /app
RUN chmod +x mvnw
RUN ./mvnw clean package -Dmaven.test.skip=true

# Runtime stage
FROM bellsoft/liberica-runtime-container:jre-21-musl
WORKDIR /app

# Expose the necessary port
EXPOSE 9093

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the application
CMD ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]