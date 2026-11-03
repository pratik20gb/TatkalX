# TatkalX

A high-concurrency railway ticket booking platform built to explore backend engineering, distributed systems, concurrency control, and scalability.

## Tech Stack

- Java 25
- Spring Boot 4.1.2
- PostgreSQL (planned)
- Redis (planned)
- Kafka (planned)
- Docker and Kubernetes (planned)
- Prometheus and Grafana (planned)
- React and TypeScript (planned)

## Run Locally

From the `backend` directory:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Health endpoint: `http://localhost:8080/api/health`

## Goal

Build and benchmark a fault-tolerant booking system under high concurrent load, with particular emphasis on preventing overselling and handling traffic spikes safely.
