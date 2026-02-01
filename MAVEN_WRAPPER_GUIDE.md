# Maven Wrapper Guide

## What is Maven Wrapper?

Maven Wrapper is a tool that allows you to run Maven builds without having Maven installed on your system. It automatically downloads the correct Maven version on first use.

## Benefits

✅ **No Maven Installation Required** - Just need Java  
✅ **Consistent Maven Version** - Everyone uses the same Maven version  
✅ **Easy Setup** - Works out of the box  
✅ **CI/CD Friendly** - Perfect for automated builds  

## Files Included

```
kawach-pandemic-support/
├── mvnw.cmd                          # Maven Wrapper for Windows
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties  # Maven version configuration
```

## Usage

### Windows (PowerShell or CMD)

```powershell
# Instead of: mvn clean install
# Use:
.\mvnw.cmd clean install

# Instead of: mvn test
# Use:
.\mvnw.cmd test

# Instead of: mvn verify
# Use:
.\mvnw.cmd verify
```

### First Run

On the first run, Maven Wrapper will:
1. Download Maven 3.9.6 (specified in maven-wrapper.properties)
2. Cache it in your user directory (~/.m2/wrapper)
3. Use it for all subsequent builds

**Example First Run:**
```powershell
PS> .\mvnw.cmd clean install
Downloading Maven 3.9.6...
Downloaded successfully
Building project...
```

### Subsequent Runs

After the first run, Maven Wrapper uses the cached Maven:
```powershell
PS> .\mvnw.cmd clean install
Building project...
```

## Common Commands

### Build Commands
```powershell
# Clean and build
.\mvnw.cmd clean install

# Build with tests
.\mvnw.cmd clean verify

# Skip tests (not recommended)
.\mvnw.cmd clean install -DskipTests

# Parallel build (faster)
.\mvnw.cmd clean install -T 4
```

### Test Commands
```powershell
# Run all tests
.\mvnw.cmd test

# Run specific test
.\mvnw.cmd test -Dtest=EurekaServerApplicationTests

# Run integration tests
.\mvnw.cmd verify
```

### Coverage & Quality
```powershell
# Generate coverage report
.\mvnw.cmd clean test jacoco:report

# Run SpotBugs
.\mvnw.cmd spotbugs:check

# Run Checkstyle
.\mvnw.cmd checkstyle:check

# Run PMD
.\mvnw.cmd pmd:check

# Generate site with all reports
.\mvnw.cmd clean verify site
```

### Module-Specific Commands
```powershell
# Build specific module
.\mvnw.cmd clean install -pl eureka-server

# Build module and dependencies
.\mvnw.cmd clean install -pl eureka-server -am

# Build all except one module
.\mvnw.cmd clean install -pl !product-webapp
```

## Configuration

### Maven Version

The Maven version is configured in `.mvn/wrapper/maven-wrapper.properties`:

```properties
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip
```

To change the Maven version, update the version number in the URL.

### Maven Options

Create `.mvn/jvm.config` to set JVM options for Maven:

```
-Xmx2048m
-XX:MaxPermSize=512m
```

Create `.mvn/maven.config` to set Maven options:

```
-T 4
--batch-mode
```

## Integration with Build Script

The `build.ps1` script automatically detects and uses Maven Wrapper:

```powershell
# Automatically uses mvnw.cmd if available
.\build.ps1 -Action build

# Falls back to system Maven if mvnw.cmd not found
.\build.ps1 -Action test
```

## Troubleshooting

### Issue: "mvnw.cmd is not recognized"

**Solution**: Use `.\mvnw.cmd` (with `.\` prefix) instead of just `mvnw.cmd`

```powershell
# Wrong
mvnw.cmd clean install

# Correct
.\mvnw.cmd clean install
```

### Issue: "JAVA_HOME is not defined"

**Solution**: Set JAVA_HOME environment variable

```powershell
# Temporary (current session)
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.x-hotspot"

# Permanent (requires admin)
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Eclipse Adoptium\jdk-21.0.x-hotspot', 'Machine')
```

### Issue: Download fails

**Solution**: Check internet connection and proxy settings

```powershell
# If behind proxy, set proxy in Maven settings
# Create/edit: %USERPROFILE%\.m2\settings.xml
```

### Issue: "Wrapper JAR not found"

**Solution**: The wrapper will download it automatically on first run. If it fails:

1. Check internet connection
2. Manually download from: https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.2.0/maven-wrapper-3.2.0.jar
3. Place in: `.mvn/wrapper/maven-wrapper.jar`

## Comparison: Maven vs Maven Wrapper

| Feature | System Maven | Maven Wrapper |
|---------|-------------|---------------|
| Installation | Required | Not required |
| Version Control | Manual | Automatic |
| Consistency | Varies by system | Same for everyone |
| CI/CD Setup | Need to install | Works out of box |
| Updates | Manual | Update properties file |

## Best Practices

### 1. Use Maven Wrapper in CI/CD

```yaml
# GitHub Actions example
- name: Build with Maven Wrapper
  run: ./mvnw.cmd clean verify
```

### 2. Commit Wrapper Files

Always commit these files to version control:
- `mvnw.cmd`
- `.mvn/wrapper/maven-wrapper.properties`

### 3. Document Usage

Include Maven Wrapper commands in your README:
```markdown
## Build
```powershell
.\mvnw.cmd clean install
```
```

### 4. Keep Maven Version Updated

Regularly update the Maven version in `maven-wrapper.properties` to get latest features and fixes.

## Advanced Usage

### Custom Repository

Use a custom Maven repository:

```powershell
.\mvnw.cmd clean install -Dmaven.repo.local=C:\custom\repo
```

### Offline Mode

Build without downloading dependencies:

```powershell
.\mvnw.cmd clean install -o
```

### Debug Mode

Run with debug output:

```powershell
.\mvnw.cmd clean install -X
```

### Quiet Mode

Suppress output:

```powershell
.\mvnw.cmd clean install -q
```

## IDE Integration

### IntelliJ IDEA

1. File → Settings → Build, Execution, Deployment → Build Tools → Maven
2. Set "Maven home directory" to: `Use Maven wrapper`
3. IntelliJ will automatically use mvnw.cmd

### Eclipse

1. Window → Preferences → Maven → Installations
2. Add → External
3. Browse to project root (where mvnw.cmd is located)
4. Eclipse will detect and use Maven Wrapper

### VS Code

Maven extension automatically detects and uses Maven Wrapper if present.

## Migration from System Maven

If you're currently using system Maven:

1. **No changes needed** - Maven Wrapper is compatible
2. **Replace commands**: Change `mvn` to `.\mvnw.cmd`
3. **Update scripts**: Update build scripts to use Maven Wrapper
4. **Update CI/CD**: Update pipeline configurations

## FAQ

**Q: Do I still need to install Maven?**  
A: No! Maven Wrapper downloads Maven automatically.

**Q: Where is Maven downloaded?**  
A: To `%USERPROFILE%\.m2\wrapper\dists\`

**Q: Can I use both system Maven and Maven Wrapper?**  
A: Yes! They don't conflict. Use `mvn` for system Maven and `.\mvnw.cmd` for wrapper.

**Q: How do I update Maven version?**  
A: Edit `.mvn/wrapper/maven-wrapper.properties` and change the version in distributionUrl.

**Q: Is Maven Wrapper secure?**  
A: Yes! It downloads from official Maven Central repository over HTTPS.

**Q: Does Maven Wrapper work offline?**  
A: After first download, yes! Maven is cached locally.

## Resources

- [Maven Wrapper GitHub](https://github.com/apache/maven-wrapper)
- [Maven Documentation](https://maven.apache.org/wrapper/)
- [Maven Central Repository](https://repo.maven.apache.org/maven2/)

## Summary

Maven Wrapper makes building Java projects easier by:
- Eliminating Maven installation requirement
- Ensuring consistent Maven version across team
- Simplifying CI/CD setup
- Providing better reproducibility

**Just use `.\mvnw.cmd` instead of `mvn` and you're good to go!**

---

**Included in Kawach Project**: ✅  
**Ready to Use**: ✅  
**No Setup Required**: ✅
