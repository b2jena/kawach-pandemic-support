# Build Requirements for Kawach Microservices

## Prerequisites

### Required Software Versions

1. **Java Development Kit (JDK) 21**
   - **Current System**: Java 8 (OpenJDK 1.8.0_472)
   - **Required**: Java 21 LTS or higher
   - **Download**: https://adoptium.net/temurin/releases/?version=21

2. **Apache Maven 3.8.0+** (Optional - Maven Wrapper included!)
   - **Maven Wrapper**: Included in project (mvnw.cmd)
   - **No installation needed**: Maven Wrapper downloads Maven automatically
   - **Manual Install**: https://maven.apache.org/download.cgi (if you prefer)

3. **Git** (for Config Server)
   - Any recent version

## Installation Steps

### Step 1: Install Java 21

#### Option A: Using Eclipse Temurin (Recommended)
1. Download from: https://adoptium.net/temurin/releases/?version=21
2. Choose: OpenJDK 21 (LTS) for Windows x64
3. Run the installer
4. During installation, select "Set JAVA_HOME variable"
5. Select "Add to PATH"

#### Option B: Using Oracle JDK
1. Download from: https://www.oracle.com/java/technologies/downloads/#java21
2. Install and configure JAVA_HOME

#### Verify Installation
```powershell
java -version
# Should show: openjdk version "21.x.x"

echo %JAVA_HOME%
# Should show: C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot (or similar)
```

### Step 2: Maven Setup (Optional - Maven Wrapper Included!)

**Good News**: This project includes Maven Wrapper, so you don't need to install Maven!

The Maven Wrapper (`mvnw.cmd`) will automatically download and use the correct Maven version on first use.

#### Option A: Use Maven Wrapper (Recommended - No Installation Needed)
```powershell
# Just use mvnw.cmd instead of mvn
.\mvnw.cmd clean install
.\mvnw.cmd test
.\mvnw.cmd verify
```

#### Option B: Install Maven Manually (Optional)
If you prefer to install Maven system-wide:

1. Download Maven 3.9.x from: https://maven.apache.org/download.cgi
2. Extract to: `C:\Program Files\Apache\maven`
3. Add to PATH: `C:\Program Files\Apache\maven\bin`
4. Set M2_HOME: `C:\Program Files\Apache\maven`

#### Verify Installation
```powershell
# If using Maven Wrapper
.\mvnw.cmd --version

# If using system Maven
mvn --version

# Should show: Apache Maven 3.9.x
# Should show: Java version: 21.x.x
```

### Step 3: Configure Environment Variables

#### Set JAVA_HOME (Required)
```powershell
# PowerShell (Run as Administrator)
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-21.0.x-hotspot', 'Machine')
```

#### Set M2_HOME (Optional - Only if using system Maven)
```powershell
# PowerShell (Run as Administrator) - Only needed if you installed Maven manually
[System.Environment]::SetEnvironmentVariable('M2_HOME', 'C:\Program Files\Apache\maven', 'Machine')
```

#### Update PATH
```powershell
# Add to PATH (Run as Administrator)
$path = [System.Environment]::GetEnvironmentVariable('Path', 'Machine')
$newPath = $path + ';%JAVA_HOME%\bin'

# Only add Maven to PATH if you installed it manually
# $newPath = $newPath + ';%M2_HOME%\bin'

[System.Environment]::SetEnvironmentVariable('Path', $newPath, 'Machine')
```

**Note**: Maven Wrapper doesn't require M2_HOME or Maven in PATH!

## Build Commands

### Using Maven Wrapper (Recommended)

```powershell
# Clean Build
.\mvnw.cmd clean install

# Build with Tests
.\mvnw.cmd clean verify

# Build Skipping Tests (Not Recommended)
.\mvnw.cmd clean install -DskipTests

# Generate Coverage Report
.\mvnw.cmd clean test jacoco:report

# Run Security Scan
.\mvnw.cmd spotbugs:check

# Run Code Quality Checks
.\mvnw.cmd checkstyle:check pmd:check

# Generate Complete Site with Reports
.\mvnw.cmd clean verify site
```

### Using System Maven (If Installed)

```powershell
# Clean Build
mvn clean install

# Build with Tests
mvn clean verify

# Build Skipping Tests (Not Recommended)
mvn clean install -DskipTests

# Generate Coverage Report
mvn clean test jacoco:report

# Run Security Scan
mvn spotbugs:check

# Run Code Quality Checks
mvn checkstyle:check pmd:check

# Generate Complete Site with Reports
mvn clean verify site
```

### Using PowerShell Build Script (Easiest)

```powershell
# The build script automatically uses Maven Wrapper if available
.\build.ps1 -Action build
.\build.ps1 -Action test
.\build.ps1 -Action coverage
.\build.ps1 -Action quality
```

## Build Output Locations

### Test Reports
- **Surefire Reports**: `<module>/target/surefire-reports/`
- **Failsafe Reports**: `<module>/target/failsafe-reports/`

### Coverage Reports
- **JaCoCo HTML**: `<module>/target/site/jacoco/index.html`
- **JaCoCo XML**: `<module>/target/site/jacoco/jacoco.xml`

### Quality Reports
- **SpotBugs**: `<module>/target/spotbugsXml.xml`
- **Checkstyle**: `<module>/target/checkstyle-result.xml`
- **PMD**: `<module>/target/pmd.xml`

### Site Reports
- **Complete Site**: `<module>/target/site/index.html`

## Troubleshooting

### Issue: "JAVA_HOME is not defined correctly"
**Solution**: 
1. Verify JAVA_HOME points to JDK 21 installation
2. Restart terminal/IDE after setting environment variables
3. Run: `echo %JAVA_HOME%` to verify

### Issue: "Unsupported class file major version 65"
**Solution**: You're using Java 21 bytecode with older Java version
- Ensure Java 21 is installed and JAVA_HOME is set correctly

### Issue: Maven not found
**Solution**:
1. Verify Maven is installed: `mvn --version`
2. Check PATH includes Maven bin directory
3. Restart terminal after PATH changes

### Issue: Tests failing
**Solution**:
1. Ensure all services can start on their configured ports
2. Check no other services are using ports: 8076, 8086, 8088, 8099
3. Review test logs in `target/surefire-reports/`

### Issue: Coverage threshold not met
**Solution**:
1. Review coverage report: `target/site/jacoco/index.html`
2. Add tests for uncovered code
3. Temporarily lower thresholds in parent pom.xml (not recommended for production)

## Docker Build (Optional)

### Build Docker Images
```powershell
# Build all services
docker-compose build

# Build specific service
docker build -t kawach/eureka-server ./eureka-server
```

### Run with Docker Compose
```powershell
docker-compose up -d
```

## IDE Configuration

### IntelliJ IDEA
1. File → Project Structure → Project SDK → Select JDK 21
2. File → Settings → Build, Execution, Deployment → Build Tools → Maven
3. Set Maven home directory
4. Set JDK for Maven: Use Project JDK

### Eclipse
1. Window → Preferences → Java → Installed JREs → Add JDK 21
2. Window → Preferences → Maven → Installations → Add Maven installation
3. Right-click project → Maven → Update Project

### VS Code
1. Install Extension Pack for Java
2. Set `java.configuration.runtimes` in settings.json:
```json
{
  "java.configuration.runtimes": [
    {
      "name": "JavaSE-21",
      "path": "C:\\Program Files\\Eclipse Adoptium\\jdk-21.0.x-hotspot",
      "default": true
    }
  ]
}
```

## Continuous Integration

### GitHub Actions Example
```yaml
name: Build and Test

on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Build with Maven
        run: mvn clean verify
      - name: Upload Coverage
        uses: codecov/codecov-action@v3
```

## Performance Tuning

### Maven Memory Settings
Create/edit `%USERPROFILE%\.mavenrc` or set environment variable:
```powershell
$env:MAVEN_OPTS="-Xmx2048m -XX:MaxPermSize=512m"
```

### Parallel Builds
```powershell
mvn clean install -T 4  # Use 4 threads
```

## Next Steps After Setup

1. Verify installation: `mvn --version` shows Java 21
2. Run clean build: `mvn clean install`
3. Run tests: `mvn test`
4. Generate reports: `mvn verify site`
5. Review coverage: Open `target/site/jacoco/index.html`
6. Start services: Follow MIGRATION_GUIDE.md

## Support

For issues or questions:
1. Check this document first
2. Review MIGRATION_GUIDE.md
3. Check Maven logs in `target/` directories
4. Review test reports for specific failures

---

**Last Updated**: February 2026  
**Minimum Requirements**: Java 21, Maven 3.8+
