# Use a minimal Alpine Linux base image
FROM alpine:latest

# Set the working directory
WORKDIR /app

# Install curl
RUN apk --no-cache add curl

# Download and install VictoriaMetrics
RUN curl -LJO https://github.com/VictoriaMetrics/VictoriaMetrics/releases/download/v1.78.1/victoria-metrics-v1.78.1.tar.gz \
    && tar -xzf victoria-metrics-v1.78.1.tar.gz \
    && mv victoria-metrics-v1.78.1 victoria-metrics \
    && rm victoria-metrics-v1.78.1.tar.gz

# Copy the configuration file into the container
COPY victoria-metrics.yml /app/victoria-metrics/victoria-metrics.yml

# Expose the port used by VictoriaMetrics
EXPOSE 8428

# Run VictoriaMetrics
CMD ["/app/victoria-metrics/victoria-metrics", "-config", "/app/victoria-metrics/victoria-metrics.yml"]
