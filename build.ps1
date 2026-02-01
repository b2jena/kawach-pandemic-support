# Kawach Microservices Build Script
# PowerShell script for building and testing the project

param(
    [Parameter(Mandatory=$false)]
    [ValidateSet('clean', 'build', 'test', 'verify', 'coverage', 'quality', 'site', 'docker', 'help')]
    [string]$Action = 'help',
    
    [Parameter(Mandatory=$false)]
    [switch]$SkipTests = $false,
    
    [Parameter(Mandatory=$false)]
    [switch]$Parallel = $false
)

# Colors for output
$ErrorColor = "Red"
$SuccessColor = "Green"
$InfoColor = "Cyan"
$WarningColor = "Yellow"

function Write-Header {
    param([string]$Message)
    Write-Host "`n========================================" -ForegroundColor $InfoColor
    Write-Host " $Message" -ForegroundColor $InfoColor
    Write-Host "========================================`n" -ForegroundColor $InfoColor
}

function Write-Success {
    param([string]$Message)
    Write-Host "✓ $Message" -ForegroundColor $SuccessColor
}

function Write-Error-Message {
    param([string]$Message)
    Write-Host "✗ $Message" -ForegroundColor $ErrorColor
}

function Write-Info {
    param([string]$Message)
    Write-Host "ℹ $Message" -ForegroundColor $InfoColor
}

function Write-Warning-Message {
    param([string]$Message)
    Write-Host "⚠ $Message" -ForegroundColor $WarningColor
}

function Get-MavenCommand {
    # Check if Maven Wrapper exists
    if (Test-Path "mvnw.cmd") {
        Write-Info "Using Maven Wrapper (mvnw.cmd)"
        return ".\mvnw.cmd"
    }
    
    # Check if Maven is installed
    try {
        mvn --version | Out-Null
        Write-Info "Using system Maven"
        return "mvn"
    } catch {
        Write-Error-Message "Neither Maven Wrapper nor Maven found!"
        Write-Info "Maven Wrapper will be downloaded automatically on first use."
        return ".\mvnw.cmd"
    }
}

function Check-Prerequisites {
    Write-Header "Checking Prerequisites"
    
    # Check Java
    try {
        $javaVersion = java -version 2>&1 | Select-String "version" | ForEach-Object { $_.ToString() }
        if ($javaVersion -match "21") {
            Write-Success "Java 21 found: $javaVersion"
        } else {
            Write-Warning-Message "Java 21 not found. Current version: $javaVersion"
            Write-Info "Please install Java 21 from: https://adoptium.net/temurin/releases/?version=21"
            return $false
        }
    } catch {
        Write-Error-Message "Java not found. Please install Java 21."
        return $false
    }
    
    # Check Maven or Maven Wrapper
    $mvnCmd = Get-MavenCommand
    if ($mvnCmd -eq ".\mvnw.cmd") {
        if (Test-Path "mvnw.cmd") {
            Write-Success "Maven Wrapper found"
        } else {
            Write-Warning-Message "Maven Wrapper not found, but will be created"
        }
    } else {
        try {
            $mavenVersion = & $mvnCmd --version 2>&1 | Select-String "Apache Maven" | ForEach-Object { $_.ToString() }
            Write-Success "Maven found: $mavenVersion"
        } catch {
            Write-Warning-Message "Maven check failed, will use Maven Wrapper"
        }
    }
    
    # Check JAVA_HOME
    if ($env:JAVA_HOME) {
        Write-Success "JAVA_HOME is set: $env:JAVA_HOME"
    } else {
        Write-Warning-Message "JAVA_HOME is not set. Setting it temporarily..."
        # Try to find Java 21 installation
        $javaPath = (Get-Command java -ErrorAction SilentlyContinue).Source
        if ($javaPath) {
            $javaHome = Split-Path (Split-Path $javaPath -Parent) -Parent
            $env:JAVA_HOME = $javaHome
            Write-Info "Temporarily set JAVA_HOME to: $javaHome"
        }
    }
    
    return $true
}

function Clean-Build {
    Write-Header "Cleaning Build Artifacts"
    $mvnCmd = Get-MavenCommand
    & $mvnCmd clean
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Clean completed successfully"
    } else {
        Write-Error-Message "Clean failed"
        exit 1
    }
}

function Build-Project {
    Write-Header "Building Project"
    
    $mvnCmd = Get-MavenCommand
    $mvnArgs = "clean", "install"
    
    if ($SkipTests) {
        $mvnArgs += "-DskipTests"
        Write-Warning-Message "Skipping tests (not recommended for production)"
    }
    
    if ($Parallel) {
        $mvnArgs += "-T", "4"
        Write-Info "Building with 4 parallel threads"
    }
    
    & $mvnCmd $mvnArgs
    
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Build completed successfully"
    } else {
        Write-Error-Message "Build failed"
        exit 1
    }
}

function Run-Tests {
    Write-Header "Running Tests"
    $mvnCmd = Get-MavenCommand
    & $mvnCmd test
    
    if ($LASTEXITCODE -eq 0) {
        Write-Success "All tests passed"
    } else {
        Write-Error-Message "Some tests failed"
        Write-Info "Check reports in: target/surefire-reports/"
        exit 1
    }
}

function Run-Verify {
    Write-Header "Running Verification (Tests + Integration Tests)"
    $mvnCmd = Get-MavenCommand
    & $mvnCmd verify
    
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Verification completed successfully"
    } else {
        Write-Error-Message "Verification failed"
        exit 1
    }
}

function Generate-Coverage {
    Write-Header "Generating Coverage Report"
    $mvnCmd = Get-MavenCommand
    & $mvnCmd clean test jacoco:report
    
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Coverage report generated"
        Write-Info "View reports at:"
        Get-ChildItem -Path . -Recurse -Filter "index.html" | Where-Object { $_.FullName -like "*jacoco*" } | ForEach-Object {
            Write-Host "  - $($_.FullName)" -ForegroundColor $InfoColor
        }
    } else {
        Write-Error-Message "Coverage generation failed"
        exit 1
    }
}

function Run-Quality-Checks {
    Write-Header "Running Quality Checks"
    
    $mvnCmd = Get-MavenCommand
    
    Write-Info "Running SpotBugs..."
    & $mvnCmd spotbugs:check
    $spotbugsResult = $LASTEXITCODE
    
    Write-Info "Running Checkstyle..."
    & $mvnCmd checkstyle:check
    $checkstyleResult = $LASTEXITCODE
    
    Write-Info "Running PMD..."
    & $mvnCmd pmd:check
    $pmdResult = $LASTEXITCODE
    
    if ($spotbugsResult -eq 0 -and $checkstyleResult -eq 0 -and $pmdResult -eq 0) {
        Write-Success "All quality checks passed"
    } else {
        Write-Warning-Message "Some quality checks failed (this is informational)"
        Write-Info "Review reports in target/ directories"
    }
}

function Generate-Site {
    Write-Header "Generating Project Site"
    $mvnCmd = Get-MavenCommand
    & $mvnCmd clean verify site
    
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Site generated successfully"
        Write-Info "View site at:"
        Get-ChildItem -Path . -Recurse -Filter "index.html" | Where-Object { $_.FullName -like "*site*" -and $_.FullName -notlike "*jacoco*" } | Select-Object -First 1 | ForEach-Object {
            Write-Host "  - $($_.FullName)" -ForegroundColor $InfoColor
        }
    } else {
        Write-Error-Message "Site generation failed"
        exit 1
    }
}

function Build-Docker {
    Write-Header "Building Docker Images"
    
    # Check if Docker is available
    try {
        docker --version | Out-Null
        Write-Success "Docker found"
    } catch {
        Write-Error-Message "Docker not found. Please install Docker Desktop."
        exit 1
    }
    
    Write-Info "Building Docker images..."
    docker-compose build
    
    if ($LASTEXITCODE -eq 0) {
        Write-Success "Docker images built successfully"
        Write-Info "Start services with: docker-compose up -d"
    } else {
        Write-Error-Message "Docker build failed"
        exit 1
    }
}

function Show-Help {
    Write-Host @"

Kawach Microservices Build Script
==================================

Usage: .\build.ps1 -Action <action> [-SkipTests] [-Parallel]

Actions:
  clean      Clean build artifacts
  build      Build the project (default: with tests)
  test       Run unit tests only
  verify     Run tests and integration tests
  coverage   Generate code coverage report
  quality    Run quality checks (SpotBugs, Checkstyle, PMD)
  site       Generate project site with all reports
  docker     Build Docker images
  help       Show this help message

Options:
  -SkipTests    Skip running tests (not recommended)
  -Parallel     Build with parallel threads (faster)

Examples:
  .\build.ps1 -Action build
  .\build.ps1 -Action build -SkipTests
  .\build.ps1 -Action build -Parallel
  .\build.ps1 -Action coverage
  .\build.ps1 -Action quality
  .\build.ps1 -Action docker

Maven Wrapper:
  This script automatically uses Maven Wrapper (mvnw.cmd) if available.
  Maven Wrapper will download Maven automatically on first use.
  No need to install Maven separately!

Common Workflows:
  
  1. Full build with tests:
     .\build.ps1 -Action build
  
  2. Quick build (skip tests):
     .\build.ps1 -Action build -SkipTests
  
  3. Generate coverage report:
     .\build.ps1 -Action coverage
  
  4. Run quality checks:
     .\build.ps1 -Action quality
  
  5. Generate complete site:
     .\build.ps1 -Action site
  
  6. Build Docker images:
     .\build.ps1 -Action docker

Direct Maven Wrapper Usage:
  You can also use Maven Wrapper directly:
  
  .\mvnw.cmd clean install
  .\mvnw.cmd test
  .\mvnw.cmd verify

For more information, see:
  - BUILD_REQUIREMENTS.md
  - MIGRATION_GUIDE.md
  - TESTING_GUIDE.md

"@ -ForegroundColor $InfoColor
}

# Main execution
Write-Host @"

╔═══════════════════════════════════════════════════════════╗
║                                                           ║
║   Kawach Microservices Platform                          ║
║   Build & Test Automation Script                         ║
║                                                           ║
╚═══════════════════════════════════════════════════════════╝

"@ -ForegroundColor $InfoColor

# Check prerequisites first (except for help)
if ($Action -ne 'help') {
    if (-not (Check-Prerequisites)) {
        Write-Error-Message "Prerequisites check failed. Please install required software."
        Write-Info "See BUILD_REQUIREMENTS.md for detailed instructions."
        exit 1
    }
}

# Execute requested action
switch ($Action) {
    'clean' {
        Clean-Build
    }
    'build' {
        Build-Project
    }
    'test' {
        Run-Tests
    }
    'verify' {
        Run-Verify
    }
    'coverage' {
        Generate-Coverage
    }
    'quality' {
        Run-Quality-Checks
    }
    'site' {
        Generate-Site
    }
    'docker' {
        Build-Docker
    }
    'help' {
        Show-Help
    }
    default {
        Show-Help
    }
}

Write-Host "`n"
