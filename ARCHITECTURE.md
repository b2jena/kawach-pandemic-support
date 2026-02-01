# Kawach Microservices Architecture

## System Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────┐
│                         Client Layer                                 │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐           │
│  │ Patients │  │ Doctors  │  │Volunteers│  │  Admin   │           │
│  └────┬─────┘  └────┬─────┘  └────┬─────┘  └────┬─────┘           │
└───────┼─────────────┼─────────────┼─────────────┼──────────────────┘
        │             │             │             │
        └─────────────┴─────────────┴─────────────┘
                      │
        ┌─────────────▼─────────────┐
        │     API Gateway           │
        │   (Port 8086)             │
        │   Spring Cloud Gateway    │
        │   - Routing               │
        │   - Load Balancing        │
        │   - Circuit Breaker       │
        └─────────────┬─────────────┘
                      │
        ┌─────────────▼─────────────┐
        │   Service Discovery       │
        │   Eureka Server           │
        │   (Port 8076)             │
        │   - Registration          │
        │   - Health Monitoring     │
        └─────────────┬─────────────┘
                      │
        ┌─────────────▼─────────────────────────────────────┐
        │              Microservices Layer                   │
        ├────────────────────────────────────────────────────┤
        │                                                    │
        │  ┌──────────────────┐  ┌──────────────────┐      │
        │  │ User Management  │  │ Authentication   │      │
        │  │    Service       │  │    Service       │      │
        │  │   (MongoDB)      │  │    (MySQL)       │      │
        │  └────────┬─────────┘  └────────┬─────────┘      │
        │           │                     │                 │
        │           └──────────┬──────────┘                 │
        │                      │                            │
        │              ┌───────▼────────┐                   │
        │              │   RabbitMQ     │                   │
        │              │ Message Broker │                   │
        │              └───────┬────────┘                   │
        │                      │                            │
        │  ┌───────────────────┼───────────────────┐       │
        │  │                   │                   │       │
        │  ▼                   ▼                   ▼       │
        │  ┌──────────┐  ┌──────────┐  ┌──────────┐      │
        │  │ Patient  │  │ Resource │  │ Doctor   │      │
        │  │   OTP    │  │ Request  │  │Consult   │      │
        │  │ Service  │  │ Service  │  │ Service  │      │
        │  │  (H2)    │  │(MongoDB) │  │(MongoDB) │      │
        │  └──────────┘  └──────────┘  └──────────┘      │
        │                                                  │
        │  ┌──────────┐  ┌──────────┐  ┌──────────┐      │
        │  │   Chat   │  │Volunteer │  │  Info    │      │
        │  │ Service  │  │  Revert  │  │ Service  │      │
        │  │(MongoDB) │  │ Service  │  │(External)│      │
        │  │ (Redis)  │  │(MongoDB) │  │   API    │      │
        │  └──────────┘  └──────────┘  └──────────┘      │
        │                                                  │
        └──────────────────────────────────────────────────┘
                      │
        ┌─────────────▼─────────────────────────────────────┐
        │              Data Layer                            │
        ├────────────────────────────────────────────────────┤
        │  ┌──────────┐  ┌──────────┐  ┌──────────┐        │
        │  │ MongoDB  │  │  MySQL   │  │  Redis   │        │
        │  │  NoSQL   │  │  RDBMS   │  │  Cache   │        │
        │  └──────────┘  └──────────┘  └──────────┘        │
        │                                                    │
        │  ┌──────────┐                                     │
        │  │    H2    │                                     │
        │  │ In-Memory│                                     │
        │  └──────────┘                                     │
        └────────────────────────────────────────────────────┘
                      │
        ┌─────────────▼─────────────────────────────────────┐
        │         Observability Layer                        │
        ├────────────────────────────────────────────────────┤
        │  ┌──────────┐  ┌──────────┐  ┌──────────┐        │
        │  │Actuator  │  │Micrometer│  │Prometheus│        │
        │  │Endpoints │  │ Metrics  │  │  Export  │        │
        │  └──────────┘  └──────────┘  └──────────┘        │
        └────────────────────────────────────────────────────┘
```

## Component Details

### 1. API Gateway (Port 8086)

**Technology**: Spring Cloud Gateway (Reactive)

**Responsibilities**:
- Single entry point for all client requests
- Dynamic routing to microservices
- Load balancing across service instances
- Circuit breaker pattern implementation
- Request/response transformation
- Authentication and authorization (future)

**Key Features**:
- Non-blocking reactive architecture
- WebFlux-based for high throughput
- Predicates and filters for routing logic
- Integration with Eureka for service discovery

**Endpoints**:
- Health: `/actuator/health`
- Routes: `/actuator/gateway/routes`
- Metrics: `/actuator/metrics`
- Prometheus: `/actuator/prometheus`

### 2. Eureka Server (Port 8076)

**Technology**: Spring Cloud Netflix Eureka

**Responsibilities**:
- Service registration and discovery
- Health monitoring of registered services
- Service instance management
- Load balancing information provider

**Key Features**:
- Self-preservation mode
- Peer-to-peer replication (multi-node)
- REST-based service registry
- Dashboard for monitoring

**Endpoints**:
- Dashboard: `/`
- Apps: `/eureka/apps`
- Health: `/actuator/health`
- Metrics: `/actuator/metrics`

### 3. Config Server (Port 8888)

**Technology**: Spring Cloud Config Server

**Responsibilities**:
- Centralized configuration management
- Git-based configuration repository
- Environment-specific configurations
- Dynamic configuration updates

**Key Features**:
- Git backend support
- Multiple environment profiles
- Encryption/decryption support
- Refresh scope for dynamic updates

**Endpoints**:
- Config: `/{application}/{profile}`
- Health: `/actuator/health`

### 4. Product Webapp (Port 8099)

**Technology**: Spring Boot + Angular

**Responsibilities**:
- Frontend application serving
- Static resource management
- User interface delivery
- Client-side routing

**Key Features**:
- Angular-based SPA
- Responsive design
- Integration with backend services
- Static resource optimization

**Endpoints**:
- Application: `/`
- Health: `/actuator/health`
- Static resources: `/index.html`, `/styles.css`, etc.

## Microservices Details

### User Management Service

**Database**: MongoDB

**Responsibilities**:
- User registration (doctors, volunteers)
- User profile management
- User data storage
- Integration with authentication service via RabbitMQ

**Data Flow**:
1. User registers → Store in MongoDB
2. Send credentials to Authentication Service via RabbitMQ
3. Return registration confirmation

### User Authentication Service

**Database**: MySQL

**Responsibilities**:
- User credential storage
- Authentication and authorization
- JWT token generation
- Session management

**Data Flow**:
1. Receive credentials from User Management via RabbitMQ
2. Store in MySQL securely
3. Validate login attempts
4. Issue JWT tokens

### Patient OTP Service

**Database**: H2 (In-Memory)

**Responsibilities**:
- OTP generation for patients
- Email-based OTP delivery
- OTP validation
- Quick patient authentication

**Data Flow**:
1. Patient enters email
2. Generate and send OTP via email
3. Validate OTP
4. Grant access

### Resource Request Service

**Database**: MongoDB

**Responsibilities**:
- Medical resource requests (beds, oxygen, medicines)
- Resource availability tracking
- Request-volunteer matching
- Resource verification

**Data Flow**:
1. Patient creates resource request
2. Store in MongoDB
3. Notify volunteers
4. Track fulfillment

### Doctor Consultation Service

**Database**: MongoDB

**Responsibilities**:
- Consultation request management
- Doctor-patient matching
- Consultation history
- Appointment scheduling

**Data Flow**:
1. Patient requests consultation
2. Match with available doctor
3. Create consultation session
4. Store consultation records

### Chat Service

**Database**: MongoDB + Redis

**Responsibilities**:
- Real-time chat between doctors and patients
- Message persistence
- Online status tracking (Redis)
- Chat history

**Data Flow**:
1. Establish WebSocket connection
2. Store online doctors in Redis
3. Exchange messages in real-time
4. Persist chat history in MongoDB

### Volunteer Revert Service

**Database**: MongoDB

**Responsibilities**:
- Volunteer response management
- Volunteer scoring and leaderboard
- Resource verification
- Volunteer activity tracking

**Data Flow**:
1. Volunteer responds to requests
2. Update volunteer score
3. Verify resource information
4. Update leaderboard

### Information Service

**Database**: External API

**Responsibilities**:
- Fetch COVID-19 statistics
- Display pandemic data
- Real-time updates
- Data aggregation

**Data Flow**:
1. Fetch data from external API
2. Transform and cache
3. Serve to frontend
4. Periodic updates

## Communication Patterns

### Synchronous Communication

**Protocol**: REST over HTTP

**Flow**:
```
Client → API Gateway → Eureka (service lookup) → Microservice
```

**Use Cases**:
- Real-time requests
- Immediate responses needed
- User-facing operations

### Asynchronous Communication

**Protocol**: AMQP via RabbitMQ

**Flow**:
```
Service A → RabbitMQ → Service B
```

**Use Cases**:
- User registration flow
- Event-driven updates
- Decoupled operations
- Background processing

## Data Storage Strategy

### MongoDB (NoSQL)

**Services**: User Management, Resource Request, Doctor Consultation, Chat, Volunteer

**Rationale**:
- Flexible schema for evolving data models
- Excellent read performance
- Document-based storage for complex objects
- Horizontal scalability

**Use Cases**:
- User profiles
- Resource requests
- Consultation records
- Chat messages
- Volunteer data

### MySQL (RDBMS)

**Services**: User Authentication

**Rationale**:
- ACID compliance for sensitive data
- Strong consistency guarantees
- Built-in authentication
- Relational integrity

**Use Cases**:
- User credentials
- Authentication tokens
- Sensitive security data

### Redis (Cache)

**Services**: Chat Service

**Rationale**:
- In-memory speed
- Pub/Sub capabilities
- TTL support
- Session management

**Use Cases**:
- Online doctor status
- Active chat sessions
- Temporary data
- Real-time presence

### H2 (In-Memory)

**Services**: Patient OTP

**Rationale**:
- Fast access
- Temporary data
- No persistence needed
- Lightweight

**Use Cases**:
- OTP storage (temporary)
- Session data
- Testing

## Security Architecture

### Current Implementation

1. **OTP-based Authentication** (Patients)
   - Email-based OTP
   - Time-limited validity
   - Single-use tokens

2. **JWT Authentication** (Doctors/Volunteers)
   - Token-based authentication
   - Stateless sessions
   - Secure token storage

3. **Service-to-Service**
   - Eureka-based discovery
   - Internal network communication
   - Future: mTLS support

### Recommended Production Security

1. **API Gateway Security**
   - OAuth 2.0 / OpenID Connect
   - Rate limiting
   - IP whitelisting
   - DDoS protection

2. **Service Security**
   - Spring Security
   - Method-level security
   - Role-based access control (RBAC)
   - Audit logging

3. **Data Security**
   - Encryption at rest
   - Encryption in transit (TLS)
   - Database encryption
   - Secrets management (Vault)

4. **Network Security**
   - Private subnets
   - Security groups
   - Network policies
   - Firewall rules

## Observability Architecture

### Metrics Collection

**Tool**: Micrometer + Prometheus

**Metrics**:
- JVM metrics (memory, threads, GC)
- HTTP metrics (requests, latency, errors)
- Custom business metrics
- Database connection pool metrics

### Health Monitoring

**Tool**: Spring Boot Actuator

**Health Checks**:
- Application health
- Database connectivity
- Eureka registration status
- Disk space
- Custom health indicators

### Logging

**Strategy**: Structured logging

**Levels**:
- ERROR: Critical issues
- WARN: Warning conditions
- INFO: Informational messages
- DEBUG: Detailed debugging

**Aggregation**: (Future)
- ELK Stack (Elasticsearch, Logstash, Kibana)
- Splunk
- CloudWatch Logs

### Tracing

**Tool**: (Future) Spring Cloud Sleuth + Zipkin

**Features**:
- Distributed tracing
- Request correlation
- Latency analysis
- Dependency mapping

## Deployment Architecture

### Development Environment

```
Local Machine
├── Eureka Server (mvn spring-boot:run)
├── Config Server (mvn spring-boot:run)
├── API Gateway (mvn spring-boot:run)
└── Product Webapp (mvn spring-boot:run)
```

### Docker Environment

```
Docker Compose
├── eureka-server (container)
├── config-server (container)
├── api-gateway (container)
├── product-webapp (container)
├── mongodb (container)
├── mysql (container)
├── redis (container)
└── rabbitmq (container)
```

### Production Environment (Recommended)

```
Kubernetes Cluster
├── Namespace: kawach-prod
│   ├── Deployment: eureka-server (replicas: 2)
│   ├── Deployment: config-server (replicas: 2)
│   ├── Deployment: api-gateway (replicas: 3)
│   ├── Deployment: product-webapp (replicas: 3)
│   ├── StatefulSet: mongodb (replicas: 3)
│   ├── StatefulSet: mysql (replicas: 2)
│   ├── Deployment: redis (replicas: 1)
│   └── Deployment: rabbitmq (replicas: 1)
├── Services (LoadBalancer, ClusterIP)
├── Ingress (HTTPS, routing)
└── ConfigMaps & Secrets
```

## Scalability Considerations

### Horizontal Scaling

**Stateless Services** (Can scale freely):
- API Gateway
- Product Webapp
- All business microservices

**Stateful Services** (Require coordination):
- Eureka Server (peer-to-peer)
- Config Server (shared Git repo)
- Databases (replication/sharding)

### Load Balancing

**Client-Side**: Ribbon (via Eureka)
**Server-Side**: Kubernetes Service, AWS ELB

### Caching Strategy

**Levels**:
1. Browser cache (static resources)
2. CDN cache (global distribution)
3. Redis cache (application data)
4. Database query cache

## Resilience Patterns

### Circuit Breaker

**Tool**: Resilience4j (future integration)

**Purpose**: Prevent cascading failures

**States**:
- Closed: Normal operation
- Open: Failing, reject requests
- Half-Open: Testing recovery

### Retry Pattern

**Strategy**: Exponential backoff

**Use Cases**:
- Transient failures
- Network timeouts
- Service unavailability

### Fallback Pattern

**Strategy**: Graceful degradation

**Use Cases**:
- Service unavailable
- Timeout exceeded
- Circuit breaker open

### Bulkhead Pattern

**Strategy**: Resource isolation

**Use Cases**:
- Thread pool isolation
- Connection pool limits
- Rate limiting

## Technology Stack Summary

### Backend
- Java 21 (LTS)
- Spring Boot 3.2.2
- Spring Cloud 2023.0.0
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Cloud Config

### Frontend
- Angular
- TypeScript
- HTML5/CSS3

### Databases
- MongoDB 4.x+
- MySQL 8.x+
- Redis 6.x+
- H2 (embedded)

### Message Broker
- RabbitMQ 3.x+

### Build & Quality
- Maven 3.8+
- JaCoCo 0.8.11
- SpotBugs 4.8.3.1
- Checkstyle 3.3.1
- PMD 3.21.2

### Observability
- Spring Boot Actuator
- Micrometer
- Prometheus (export)

### Containerization
- Docker
- Docker Compose

## Future Enhancements

### Short-term
1. Complete test coverage to 100%
2. Implement circuit breakers
3. Add distributed tracing
4. Enhanced security (OAuth 2.0)
5. API documentation (Swagger/OpenAPI)

### Medium-term
1. Kubernetes deployment
2. Service mesh (Istio)
3. Advanced monitoring (Grafana)
4. Log aggregation (ELK)
5. CI/CD pipeline

### Long-term
1. Native compilation (GraalVM)
2. Event sourcing (CQRS)
3. GraphQL API
4. Machine learning integration
5. Multi-region deployment

---

**Architecture Version**: 2.0  
**Last Updated**: February 2026  
**Status**: Production Ready
