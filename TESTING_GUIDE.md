# Comprehensive Testing Guide

## Overview

This guide covers all aspects of testing in the Kawach microservices platform, including unit tests, integration tests, coverage analysis, and quality assurance.

## Test Structure

### Test Organization

```
<module>/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│           └── com/stackroute/<module>/
│               ├── *Tests.java          # Unit tests
│               └── *IntegrationTest.java # Integration tests
```

### Naming Conventions

- **Unit Tests**: `*Test.java` or `*Tests.java`
- **Integration Tests**: `*IT.java` or `*IntegrationTest.java`
- **Test Methods**: `should*()` or descriptive names

## Test Types

### 1. Unit Tests

Unit tests verify individual components in isolation.

**Example:**
```java
@SpringBootTest
class EurekaServerApplicationTests {
    
    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
    }
    
    @Test
    void eurekaServerApplicationBeanExists() {
        assertThat(applicationContext.getBean(EurekaServerApplication.class))
            .isNotNull();
    }
}
```

**Run Unit Tests:**
```bash
mvn test
```

### 2. Integration Tests

Integration tests verify complete workflows and service interactions.

**Example:**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiGatewayIntegrationTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void actuatorHealthShowsUpStatus() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/actuator/health", 
            String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

**Run Integration Tests:**
```bash
mvn verify
```

### 3. End-to-End Tests

E2E tests verify complete user workflows across multiple services.

**Run E2E Tests:**
```bash
# Start all services first
docker-compose up -d

# Run E2E tests
mvn failsafe:integration-test
```

## Test Coverage

### Coverage Goals

| Metric | Target | Enforced |
|--------|--------|----------|
| Line Coverage | 90% | ✅ Yes |
| Branch Coverage | 85% | ✅ Yes |
| Complexity Coverage | 80% | ✅ Yes |

### Generate Coverage Report

```bash
# Run tests with coverage
mvn clean test jacoco:report

# View HTML report
open target/site/jacoco/index.html  # macOS
start target/site/jacoco/index.html # Windows
```

### Coverage Report Structure

```
target/site/jacoco/
├── index.html              # Main coverage report
├── jacoco.xml             # XML format (for CI/CD)
├── jacoco.csv             # CSV format
└── <package>/
    └── <class>.html       # Per-class coverage
```

### Understanding Coverage Metrics

#### Line Coverage (Green/Red/Yellow)
- **Green**: Line executed
- **Red**: Line not executed
- **Yellow**: Partially covered (branches)

#### Branch Coverage
- Measures if/else, switch, ternary operators
- Both true and false paths must be tested

#### Complexity Coverage
- Based on cyclomatic complexity
- Measures independent paths through code

### Improving Coverage

1. **Identify Uncovered Code**
   ```bash
   mvn jacoco:report
   # Open report and look for red/yellow lines
   ```

2. **Write Missing Tests**
   - Focus on business logic
   - Test edge cases
   - Test error handling

3. **Verify Improvement**
   ```bash
   mvn clean test jacoco:report
   ```

## Test Configuration

### Test Properties

Create `src/test/resources/application-test.properties`:

```properties
# Disable Eureka for tests
eureka.client.enabled=false

# Use random port
server.port=0

# Fast startup
spring.jmx.enabled=false

# Test database
spring.datasource.url=jdbc:h2:mem:testdb
```

### Test Profiles

Use `@TestPropertySource` or `@ActiveProfiles`:

```java
@SpringBootTest
@TestPropertySource(properties = {
    "eureka.client.enabled=false",
    "spring.cloud.gateway.enabled=true"
})
class ApiGatewayTests {
    // Tests
}
```

## Testing Best Practices

### 1. Test Independence

Each test should be independent and not rely on execution order.

```java
@BeforeEach
void setUp() {
    // Initialize test data
}

@AfterEach
void tearDown() {
    // Clean up test data
}
```

### 2. Use Meaningful Names

```java
// Good
@Test
void shouldReturnHealthStatusUpWhenServiceIsRunning() { }

// Bad
@Test
void test1() { }
```

### 3. Arrange-Act-Assert Pattern

```java
@Test
void shouldCalculateTotalPrice() {
    // Arrange
    Order order = new Order();
    order.addItem(new Item("Product", 10.0));
    
    // Act
    double total = order.calculateTotal();
    
    // Assert
    assertThat(total).isEqualTo(10.0);
}
```

### 4. Test Edge Cases

```java
@Test
void shouldHandleEmptyList() { }

@Test
void shouldHandleNullInput() { }

@Test
void shouldHandleMaximumValue() { }
```

### 5. Use AssertJ for Fluent Assertions

```java
// Good - Fluent and readable
assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
assertThat(response.getBody()).contains("UP");

// Less readable
assertEquals(HttpStatus.OK, response.getStatusCode());
assertTrue(response.getBody().contains("UP"));
```

## Mocking and Stubbing

### Using Mockito

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    void shouldFindUserById() {
        // Arrange
        User user = new User("1", "John");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        
        // Act
        User result = userService.findById("1");
        
        // Assert
        assertThat(result).isEqualTo(user);
        verify(userRepository).findById("1");
    }
}
```

### Using @MockBean (Spring)

```java
@SpringBootTest
class UserControllerTest {
    
    @MockBean
    private UserService userService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void shouldReturnUser() throws Exception {
        when(userService.findById("1"))
            .thenReturn(new User("1", "John"));
        
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John"));
    }
}
```

## Testing Microservices

### Testing with TestRestTemplate

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiGatewayTest {
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldRouteToService() {
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/api/users",
            String.class
        );
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

### Testing with WebTestClient (Reactive)

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class GatewayReactiveTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @Test
    void shouldRouteRequest() {
        webTestClient.get()
            .uri("/api/users")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .value(body -> assertThat(body).contains("users"));
    }
}
```

## Performance Testing

### Load Testing with JMeter

1. Install Apache JMeter
2. Create test plan
3. Configure thread groups
4. Add HTTP requests
5. Run and analyze results

### Stress Testing

```bash
# Using Apache Bench
ab -n 1000 -c 10 http://localhost:8086/actuator/health

# Using wrk
wrk -t12 -c400 -d30s http://localhost:8086/actuator/health
```

## Security Testing

### SpotBugs Security Scan

```bash
# Run security scan
mvn spotbugs:check

# View report
open target/spotbugsXml.xml
```

### OWASP Dependency Check

```bash
# Add to pom.xml
<plugin>
    <groupId>org.owasp</groupId>
    <artifactId>dependency-check-maven</artifactId>
    <version>9.0.9</version>
</plugin>

# Run check
mvn dependency-check:check
```

## Continuous Integration

### GitHub Actions Example

```yaml
name: Test and Coverage

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
      - uses: actions/checkout@v4
      
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
      
      - name: Run tests
        run: mvn clean verify
      
      - name: Generate coverage report
        run: mvn jacoco:report
      
      - name: Upload coverage to Codecov
        uses: codecov/codecov-action@v3
        with:
          files: ./target/site/jacoco/jacoco.xml
      
      - name: Check coverage thresholds
        run: mvn jacoco:check
```

## Test Reports

### Surefire Reports (Unit Tests)

Location: `target/surefire-reports/`

```bash
# View test results
cat target/surefire-reports/TEST-*.xml
```

### Failsafe Reports (Integration Tests)

Location: `target/failsafe-reports/`

```bash
# View integration test results
cat target/failsafe-reports/TEST-*.xml
```

### JaCoCo Reports

Location: `target/site/jacoco/`

- `index.html` - Main report
- `jacoco.xml` - XML format
- `jacoco.csv` - CSV format

## Troubleshooting Tests

### Common Issues

#### 1. Port Already in Use

**Problem**: Test fails with "Address already in use"

**Solution**: Use random port
```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
```

#### 2. Eureka Connection Timeout

**Problem**: Tests timeout connecting to Eureka

**Solution**: Disable Eureka in tests
```java
@TestPropertySource(properties = "eureka.client.enabled=false")
```

#### 3. Slow Tests

**Problem**: Tests take too long

**Solution**: 
- Use `@MockBean` instead of real services
- Disable auto-configuration: `@SpringBootTest(classes = {YourClass.class})`
- Use test slices: `@WebMvcTest`, `@DataJpaTest`

#### 4. Flaky Tests

**Problem**: Tests pass/fail randomly

**Solution**:
- Avoid Thread.sleep()
- Use Awaitility for async operations
- Ensure test independence
- Use `@DirtiesContext` if needed

### Debug Tests

```bash
# Run single test
mvn test -Dtest=EurekaServerApplicationTests

# Run with debug
mvn test -Dmaven.surefire.debug

# Skip specific test
mvn test -Dtest=!EurekaServerApplicationTests
```

## Test Metrics

### View Test Statistics

```bash
# Run tests with detailed output
mvn test -Dsurefire.printSummary=true

# Generate site with test reports
mvn site
open target/site/surefire-report.html
```

### Coverage Trends

Track coverage over time:

1. Generate coverage report after each build
2. Store `jacoco.xml` in version control or CI artifacts
3. Use tools like SonarQube or Codecov for visualization

## Advanced Testing

### Contract Testing

Use Spring Cloud Contract for API contract testing:

```java
@SpringBootTest
@AutoConfigureStubRunner(
    ids = "com.stackroute:user-service:+:stubs:8080",
    stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
class ContractTest {
    // Tests
}
```

### Chaos Engineering

Test resilience with chaos engineering:

```java
@Test
void shouldHandleServiceFailure() {
    // Simulate service failure
    wireMockServer.stubFor(get("/api/users")
        .willReturn(aResponse().withStatus(500)));
    
    // Verify circuit breaker opens
    // Verify fallback is called
}
```

## Test Checklist

Before committing code:

- [ ] All tests pass: `mvn test`
- [ ] Integration tests pass: `mvn verify`
- [ ] Coverage meets thresholds: `mvn jacoco:check`
- [ ] No security issues: `mvn spotbugs:check`
- [ ] Code style compliant: `mvn checkstyle:check`
- [ ] No code smells: `mvn pmd:check`

## Resources

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [AssertJ Documentation](https://assertj.github.io/doc/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)

---

**Remember**: Good tests are the foundation of maintainable, reliable software!
