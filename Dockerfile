FROM alpine:latest

WORKDIR /app

RUN wget https://github.com/VictoriaMetrics/VictoriaMetrics/releases/download/v1.78.1/victoria-metrics-v1.78.1.tar.gz \
    && tar -xzf victoria-metrics-v1.78.1.tar.gz \
    && mv victoria-metrics-v1.78.1 victoria-metrics \
    && rm victoria-metrics-v1.78.1.tar.gz

COPY victoria-metrics.yml /app/victoria-metrics/victoria-metrics.yml

EXPOSE 8428

CMD ["/app/victoria-metrics/victoria-metrics", "-config", "/app/victoria-metrics/victoria-metrics.yml"]
