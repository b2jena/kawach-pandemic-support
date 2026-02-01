# Kawach - Pandemic Support Platform

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.0-blue.svg)](https://spring.io/projects/spring-cloud)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-success.svg)]()

> A mission-critical microservices platform connecting patients, doctors, and volunteers during pandemic emergencies.

## 🚀 Latest Updates (February 2026)

**Major Technology Upgrade Completed!**

- ✅ **Java 21** (Latest LTS) - Modern features and performance
- ✅ **Spring Boot 3.2.2** (Latest LTS) - Enhanced security and efficiency
- ✅ **Spring Cloud 2023.0.0** - Latest cloud-native capabilities
- ✅ **Industrial-Grade Quality** - 90%+ test coverage target
- ✅ **Maven Wrapper** - No Maven installation required
- ✅ **Comprehensive Documentation** - 7 detailed guides included

📖 See [MIGRATION_GUIDE.md](MIGRATION_GUIDE.md) for complete upgrade details.

---

## 📋 Table of Contents

- [Overview](#overview)
- [The Problem We Solve](#the-problem-we-solve)
- [Our Solution](#our-solution)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
- [Microservices](#microservices)
- [Testing](#testing)
- [Quality & Monitoring](#quality--monitoring)
- [Deployment](#deployment)
- [Development Methodology](#development-methodology)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

**Kawach** is a crowdsourcing platform designed to provide verified help and assistance to people in distress during pandemics. Built with enterprise-grade microservices architecture, it connects patients seeking medical resources with doctors and volunteers who can provide verified, authentic assistance.

### Key Stakeholders

- **👥 Patients** - Primary users seeking medical resources and consultations
- **👨‍⚕️ Doctors** - Medical professionals providing remote consultations
- **🤝 Volunteers** - Community members providing verified resource information

### Use Cases

1. **Government & Health Organizations** - Deploy at panchayat, district, state, or national level
2. **Corporate Sector** - Use within organizations for employee pandemic support
3. **Individual Networks** - Deploy for friends, family, and professional contacts

---

## 🔴 The Problem We Solve

During the deadly second wave of the pandemic in India, we witnessed:

### Critical Challenges

- **🏥 Resource Scarcity**
  - ICU beds, ventilators, and oxygen in short supply
  - Patients running from hospital to hospital
  - Critical patients with SpO2 below 50 unable to find care

- **💊 Medicine Shortage**
  - Common COVID medicines unavailable
  - Medical equipment scarce
  - Supply chain completely overwhelmed

- **📱 Unverified Information**
  - Social media flooded with S-O-S requests
  - Unverified or fake responses causing frustration
  - Scammers taking advantage of desperate people

- **⏰ Time-Critical Situations**
  - Every minute counts in emergencies
  - No centralized verified information source
  - Difficult to distinguish genuine help from scams

---

## ✅ Our Solution

### "In This Pandemic, Let's Be Stronger Together"

Kawach provides a **verified, centralized platform** for pandemic relief with:

#### 🚀 Quick Patient Access
- **Email + OTP Authentication** - No lengthy registration process
- Immediate access to resources and consultations
- Streamlined emergency response

#### 👨‍⚕️ Doctor Consultations
- Real-time chat-based medical consultations
- Online doctor availability tracking
- Consultation history and follow-ups

#### 📦 Resource Management
- **Verified Information** about:
  - Hospital bed availability (ICU, ventilator, general)
  - Medicine and medical equipment
  - Oxygen supply sources
  - Ambulance services
- Volunteer-verified and regularly updated

#### 🏆 Volunteer Engagement
- Gamified volunteer system with leaderboards
- Score-based recognition for contributions
- Community-driven verification process

#### 📊 Real-Time Updates
- Live pandemic statistics from external APIs
- Regional case tracking
- Trend analysis and alerts

---

## 🏗️ Architecture

### Microservices Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Client Applications                       │
│         (Web, Mobile - Patients, Doctors, Volunteers)       │
└────────────────────────┬────────────────────────────────────┘
                         │
                ┌────────▼────────┐
                │  API Gateway    │ :8086
                │  (Spring Cloud) │
                └────────┬────────┘
                         │
                ┌────────▼────────┐
                │ Eureka Server   │ :8076
                │ (Discovery)     │
                └────────┬────────┘
                         │
        ┌────────────────┼────────────────┐
        │                │                │
   ┌────▼────┐     ┌────▼────┐     ┌────▼────┐
   │ Config  │     │  User   │     │Resource │
   │ Server  │     │  Mgmt   │     │ Request │
   │  :8888  │     │ Service │     │ Service │
   └─────────┘     └────┬────┘     └────┬────┘
                        │               │
        ┌───────────────┼───────────────┼────────────┐
        │               │               │            │
   ┌────▼────┐    ┌────▼────┐    ┌────▼────┐  ┌────▼────┐
   │  Auth   │    │ Doctor  │    │  Chat   │  │Volunteer│
   │ Service │    │Consult  │    │ Service │  │ Service │
   └────┬────┘    └─────────┘    └─────────┘  └─────────┘
        │
   ┌────▼──────────────────────────────┐
   │         Data Layer                │
   │  MongoDB | MySQL | Redis | H2     │
   └───────────────────────────────────┘
```

### Key Architectural Patterns

- **API Gateway Pattern** - Single entry point for all client requests
- **Service Discovery** - Dynamic service registration with Eureka
- **Config Management** - Centralized configuration with Config Server
- **Circuit Breaker** - Resilience and fault tolerance
- **Event-Driven** - Asynchronous communication via RabbitMQ

📖 See [ARCHITECTURE.md](ARCHITECTURE.md) for detailed architecture documentation.

---

## 💻 Technology Stack

### Backend Framework
- **Java 21** (LTS) - Latest long-term support version
- **Spring Boot 3.2.2** (LTS) - Enterprise application framework
- **Spring Cloud 2023.0.0** - Cloud-native microservices

### Microservices Components
- **Spring Cloud Gateway** - Reactive API gateway
- **Netflix Eureka** - Service discovery and registration
- **Spring Cloud Config** - Centralized configuration management

### Databases
- **MongoDB** - User management, consultations, resources (NoSQL)
- **MySQL** - Authentication and credentials (RDBMS)
- **Redis** - Online doctor sessions and caching
- **H2** - In-memory database for OTP service

### Message Broker
- **RabbitMQ** - Asynchronous inter-service communication

### Frontend
- **Angular** - Single Page Application framework
- **TypeScript** - Type-safe JavaScript
- **HTML5/CSS3** - Modern web standards

### DevOps & Quality
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Maven 3.9.6** - Build automation (via Maven Wrapper)
- **JaCoCo** - Code coverage (90%+ target)
- **SpotBugs + FindSecBugs** - Security scanning
- **Checkstyle** - Code style enforcement (Google Java Style)
- **PMD** - Static code analysis

### Observability
- **Spring Boot Actuator** - Health checks and metrics
- **Micrometer** - Application metrics
- **Prometheus** - Metrics collection and monitoring

---

## 🚀 Getting Started

### Prerequisites

**Required:**
- ☕ **Java 21** (LTS) - [Download Eclipse Temurin](https://adoptium.net/temurin/releases/?version=21)

**Optional:**
- 📦 **Maven 3.8+** - Not required! Maven Wrapper is included
- 🐳 **Docker** - For containerized deployment

📖 See [BUILD_REQUIREMENTS.md](BUILD_REQUIREMENTS.md) for detailed installation instructions.

### Quick Start

#### 1️⃣ Clone the Repository
```bash
git clone <repository-url>
cd kawach-pandemic-support
```

#### 2️⃣ Build the Project
```powershell
# Using Maven Wrapper (Recommended - No Maven installation needed!)
.\mvnw.cmd clean install

# Or using PowerShell build script
.\build.ps1 -Action build

# Skip tests for faster build (not recommended)
.\mvnw.cmd clean install -DskipTests
```

#### 3️⃣ Start Services

**Option A: Individual Services (Development)**
```powershell
# Terminal 1: Eureka Server
cd eureka-server
.\mvnw.cmd spring-boot:run

# Terminal 2: Config Server
cd config-server
.\mvnw.cmd spring-boot:run

# Terminal 3: API Gateway
cd api-gateway
.\mvnw.cmd spring-boot:run

# Terminal 4: Product Webapp
cd product-webapp
.\mvnw.cmd spring-boot:run
```

**Option B: Docker Compose (Production-like)**
```powershell
docker-compose up -d
```

#### 4️⃣ Verify Services

- 🔍 **Eureka Dashboard**: http://localhost:8076
- ⚙️ **Config Server**: http://localhost:8888/actuator/health
- 🌐 **API Gateway**: http://localhost:8086/actuator/health
- 🖥️ **Product Webapp**: http://localhost:8099

📖 See [QUICK_START_CHECKLIST.md](QUICK_START_CHECKLIST.md) for step-by-step guide.

---

## 🔧 Microservices

### 1. Eureka Server (Port 8076)
**Service Discovery & Registry**

- Tracks all microservice instances
- Provides service location information
- Health monitoring and heartbeat detection
- Dashboard for service visualization

**Endpoints:**
- Dashboard: `http://localhost:8076`
- Apps: `http://localhost:8076/eureka/apps`
- Health: `http://localhost:8076/actuator/health`

### 2. Config Server (Port 8888)
**Centralized Configuration Management**

- Git-based configuration repository
- Environment-specific configurations
- Dynamic configuration updates
- Encryption/decryption support

**Endpoints:**
- Config: `http://localhost:8888/{application}/{profile}`
- Health: `http://localhost:8888/actuator/health`

### 3. API Gateway (Port 8086)
**Single Entry Point & Routing**

- Intelligent request routing
- Load balancing
- Circuit breaker integration
- Non-blocking reactive architecture

**Endpoints:**
- Health: `http://localhost:8086/actuator/health`
- Routes: `http://localhost:8086/actuator/gateway/routes`
- Metrics: `http://localhost:8086/actuator/metrics`

### 4. Product Webapp (Port 8099)
**Frontend Application**

- Angular-based user interface
- Patient, doctor, and volunteer portals
- Real-time updates and notifications
- Responsive design

**Endpoints:**
- Application: `http://localhost:8099`
- Health: `http://localhost:8099/actuator/health`

### 5. User Management Service
**User Registration & Profiles**

- Doctor and volunteer registration
- User profile management
- Data storage in MongoDB
- Integration with authentication service

### 6. User Authentication Service
**Security & Access Control**

- JWT token-based authentication
- Credential storage in MySQL
- Session management
- Role-based access control

### 7. Patient OTP Service
**Quick Patient Access**

- Email-based OTP generation
- Fast authentication for emergencies
- Temporary session management
- H2 in-memory storage

### 8. Resource Request Service
**Medical Resource Management**

- Bed availability tracking
- Medicine and equipment requests
- Volunteer matching
- Resource verification

### 9. Doctor Consultation Service
**Medical Consultations**

- Doctor-patient matching
- Consultation scheduling
- Medical history tracking
- Consultation records

### 10. Chat Service
**Real-Time Communication**

- WebSocket-based chat
- Doctor-patient messaging
- Online status tracking (Redis)
- Chat history (MongoDB)

### 11. Volunteer Revert Service
**Volunteer Management**

- Response tracking
- Scoring and leaderboards
- Resource verification
- Activity monitoring

### 12. Information Service
**Pandemic Statistics**

- External API integration
- Real-time COVID-19 data
- Regional statistics
- Trend analysis

---

## 🧪 Testing

### Test Coverage Goals

- **Line Coverage**: 90%
- **Branch Coverage**: 85%
- **Complexity Coverage**: 80%

### Running Tests

```powershell
# Run all tests
.\mvnw.cmd test

# Run with coverage report
.\mvnw.cmd clean test jacoco:report

# Run integration tests
.\mvnw.cmd verify

# View coverage report
# Open: <module>/target/site/jacoco/index.html
```

### Test Types

- **Unit Tests** - Component isolation testing
- **Integration Tests** - Complete workflow testing
- **E2E Tests** - Full system testing

📖 See [TESTING_GUIDE.md](TESTING_GUIDE.md) for comprehensive testing documentation.

---

## 📊 Quality & Monitoring

### Code Quality Tools

- **JaCoCo** - Enforces coverage thresholds
- **SpotBugs** - Bug detection and security scanning
- **FindSecBugs** - Security vulnerability detection
- **Checkstyle** - Google Java Style Guide compliance
- **PMD** - Best practices enforcement

### Running Quality Checks

```powershell
# Using build script
.\build.ps1 -Action quality

# Or manually
.\mvnw.cmd spotbugs:check checkstyle:check pmd:check

# Generate complete site with all reports
.\mvnw.cmd clean verify site
```

### Monitoring & Observability

All services expose:
- `/actuator/health` - Health status
- `/actuator/metrics` - Application metrics
- `/actuator/prometheus` - Prometheus-format metrics

---

## 🐳 Deployment

### Docker Deployment

```powershell
# Build all images
docker-compose build

# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop all services
docker-compose down
```

### Production Recommendations

1. **Security**
   - Enable HTTPS/TLS
   - Configure Spring Security
   - Use secrets management (Vault, AWS Secrets Manager)
   - Enable rate limiting

2. **Scalability**
   - Deploy on Kubernetes
   - Configure horizontal pod autoscaling
   - Use service mesh (Istio)

3. **Monitoring**
   - Set up Prometheus + Grafana
   - Configure log aggregation (ELK Stack)
   - Enable distributed tracing (Zipkin)

4. **High Availability**
   - Multi-region deployment
   - Database replication
   - Load balancer configuration

---

## 🎓 Development Methodology

### Agile SCRUM Framework

**5 Sprints × 1 Week Each**

#### Sprint Planning
- User story prioritization
- Sprint backlog creation
- Task estimation and assignment

#### Daily Standups
- What was done yesterday
- Today's plan
- Blockers and impediments

#### Sprint Review
- Working product demonstration
- Stakeholder feedback
- Mentor guidance

#### Sprint Retrospective
- What went well
- What didn't work
- Continuous improvement actions

### CI/CD Pipeline

```
Code Push → GitLab CI → Build → Test → Quality Checks → Deploy to AWS
```

- Automated builds on every push
- Code quality gates
- Automated testing
- Continuous deployment to master branch

---

## 📚 Documentation

### Complete Documentation Suite

| Document | Description |
|----------|-------------|
| [MIGRATION_GUIDE.md](MIGRATION_GUIDE.md) | Complete migration to Java 21 & Spring Boot 3.2.2 |
| [MIGRATION_SUMMARY.md](MIGRATION_SUMMARY.md) | Executive summary of the migration |
| [BUILD_REQUIREMENTS.md](BUILD_REQUIREMENTS.md) | Prerequisites and installation guide |
| [QUICK_START_CHECKLIST.md](QUICK_START_CHECKLIST.md) | Quick reference for getting started |
| [TESTING_GUIDE.md](TESTING_GUIDE.md) | Comprehensive testing documentation |
| [ARCHITECTURE.md](ARCHITECTURE.md) | Detailed system architecture |
| [MAVEN_WRAPPER_GUIDE.md](MAVEN_WRAPPER_GUIDE.md) | Maven Wrapper usage guide |

---

## 🤝 Contributing

We welcome contributions! Please follow these guidelines:

### Development Workflow

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Make your changes
4. Run tests and quality checks (`.\mvnw.cmd clean verify`)
5. Commit with meaningful messages
6. Push to your fork
7. Create a Pull Request

### Code Standards

- Follow Google Java Style Guide
- Maintain 90%+ test coverage
- Pass all quality checks (SpotBugs, Checkstyle, PMD)
- Write meaningful commit messages
- Update documentation as needed

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Team

Developed by a team of 8 engineers during the CGI India program, with guidance from experienced mentors.

---

## 🙏 Acknowledgments

- **CGI India** - For the opportunity and mentorship
- **Healthcare Workers** - Fighting on the frontlines
- **Volunteers** - Making a difference in communities
- **Open Source Community** - For excellent tools and frameworks

---

## 📞 Support

### Getting Help

1. Check the [documentation](#documentation)
2. Review [BUILD_REQUIREMENTS.md](BUILD_REQUIREMENTS.md)
3. See [QUICK_START_CHECKLIST.md](QUICK_START_CHECKLIST.md)
4. Open an issue on GitHub

### Troubleshooting

Common issues and solutions are documented in:
- [BUILD_REQUIREMENTS.md](BUILD_REQUIREMENTS.md#troubleshooting)
- [TESTING_GUIDE.md](TESTING_GUIDE.md#troubleshooting-tests)
- [MAVEN_WRAPPER_GUIDE.md](MAVEN_WRAPPER_GUIDE.md#troubleshooting)

---

## 🌟 Project Status

**Status**: ✅ Production Ready  
**Build**: ✅ Passing  
**Coverage**: 🎯 90%+ Target  
**Security**: 🔒 Scanned  
**Quality**: 🏆 Industrial Grade  

---

<div align="center">

**Built with ❤️ to help people during pandemics**

*"In This Pandemic, Let's Be Stronger Together"*

</div>
