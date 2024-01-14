FROM debian:latest

WORKDIR /app

RUN apt-get update && apt-get install -y curl tar

RUN curl -L https://github.com/VictoriaMetrics/VictoriaMetrics/releases/download/v1.96.0/victoria-metrics-linux-arm64-v1.96.0.tar.gz | tar xz --strip-components 1

COPY victoria-metrics.yml /app/victoria-metrics.yml

EXPOSE 8428

CMD ["/app/victoria-metrics", "-promscrape.config=/app/victoria-metrics.yml"]
