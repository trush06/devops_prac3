# Jenkins CI/CD Lab - Advanced Exercises

This document contains additional exercises and variations to extend your Jenkins learning experience.

---

## Exercise 1: Freestyle Job Variations

### 1A: Job with Build Parameters

**Objective:** Create a freestyle job that accepts input parameters

**Steps:**

1. Create new Freestyle job: `Java-Parametrized-Job`
2. Check **"This project is parameterized"**
3. Add Parameter → String Parameter:
   - Name: `NUM1`
   - Default value: `10`
   - Description: First number
4. Add Parameter → String Parameter:
   - Name: `NUM2`
   - Default value: `20`
   - Description: Second number
5. Build Step:
```batch
cd C:\Jenkins-WorkSpace\java-projects
javac AddNumbersInput.java
java AddNumbersInput %NUM1% %NUM2%
```
6. Click **Build with Parameters**
7. Enter your custom numbers

---

### 1B: Job with Build Triggers

**Objective:** Automatically build job based on schedule

**Steps:**

1. Edit `Java-Addition-Job`
2. Go to **Build Triggers**
3. Check **Build periodically**
4. Enter cron schedule: `H H * * *` (daily at midnight)
5. Save
6. Monitor if it builds automatically

**Cron Syntax Cheat Sheet:**
```
┌───────────── minute (0 - 59)
│ ┌───────────── hour (0 - 23)
│ │ ┌───────────── day of month (1 - 31)
│ │ │ ┌───────────── month (1 - 12)
│ │ │ │ ┌───────────── day of week (0 - 6) (Sunday to Saturday)
│ │ │ │ │
│ │ │ │ │
* * * * *

Examples:
H H * * *      Every day at the same time
H H * * MON    Every Monday
H/30 * * * *   Every 30 minutes
0 */4 * * *    Every 4 hours
```

---

## Exercise 2: Pipeline Enhancements

### 2A: Pipeline with Conditional Logic

**Objective:** Add conditional stages to your pipeline

**Groovy Script:**
```groovy
pipeline {
    agent any
    
    stages {
        stage('Check Java') {
            steps {
                script {
                    def javaVersion = bat(script: 'java -version 2>&1 | findstr /c:"17"', returnStatus: true)
                    if (javaVersion == 0) {
                        echo "✓ Java 17+ detected"
                    } else {
                        echo "⚠ Java version may not be 17+"
                    }
                }
            }
        }
        
        stage('Build') {
            steps {
                echo "Building..."
                bat 'cd C:\\Jenkins-WorkSpace\\java-projects && javac AddNumbers.java'
            }
        }
    }
}
```

---

### 2B: Pipeline with Try-Catch

**Objective:** Handle errors gracefully in pipeline

**Groovy Script:**
```groovy
pipeline {
    agent any
    
    stages {
        stage('Execute with Error Handling') {
            steps {
                script {
                    try {
                        echo "Attempting to compile..."
                        bat 'cd C:\\Jenkins-WorkSpace\\java-projects && javac AddNumbers.java'
                        echo "✓ Compilation successful"
                    } catch (Exception e) {
                        echo "✗ Compilation failed: ${e.message}"
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
        }
    }
}
```

---

### 2C: Pipeline with Email Notifications

**Objective:** Send email after build completion

**Prerequisites:**
1. Go to **Manage Jenkins** → **Configure System**
2. Find **Email Notification**
3. Enter SMTP Server details (Gmail example):
   - SMTP Server: `smtp.gmail.com`
   - Default user e-mail: `official@gmail.com`
   - Use SMTP authentication: ✓
   - Port: `587`

**Groovy Script:**
```groovy
pipeline {
    agent any
    
    stages {
        stage('Execute') {
            steps {
                bat 'cd C:\\Jenkins-WorkSpace\\java-projects && java AddNumbers'
            }
        }
    }
    
    post {
        success {
            emailext (
                subject: "Jenkins Build SUCCESS: ${BUILD_DISPLAY_NAME}",
                body: "Build succeeded! Check console output.",
                to: "official@gmail.com"
            )
        }
        failure {
            emailext (
                subject: "Jenkins Build FAILED: ${BUILD_DISPLAY_NAME}",
                body: "Build failed! Review console output.",
                to: "official@gmail.com"
            )
        }
    }
}
```

---

## Exercise 3: Advanced Java Programs

### 3A: Enhanced Calculator

Create `AdvancedCalculator.java`:

```java
public class AdvancedCalculator {
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("Advanced Java Calculator");
        System.out.println("=".repeat(50));
        
        if (args.length < 3) {
            System.out.println("Usage: java AdvancedCalculator <num1> <operator> <num2>");
            System.out.println("Operators: +, -, *, /, %");
            System.exit(1);
        }
        
        try {
            double num1 = Double.parseDouble(args[0]);
            String operator = args[1];
            double num2 = Double.parseDouble(args[2]);
            double result = 0;
            
            switch(operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero!");
                        System.exit(1);
                    }
                    result = num1 / num2;
                    break;
                case "%":
                    result = num1 % num2;
                    break;
                default:
                    System.out.println("Unknown operator: " + operator);
                    System.exit(1);
            }
            
            System.out.println("Expression: " + num1 + " " + operator + " " + num2);
            System.out.println("-".repeat(50));
            System.out.printf("Result: %.2f\n", result);
            System.out.println("=".repeat(50));
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format!");
            System.exit(1);
        }
    }
}
```

**Jenkins Job Build Step:**
```batch
cd C:\Jenkins-WorkSpace\java-projects
javac AdvancedCalculator.java
java AdvancedCalculator 100 + 50
java AdvancedCalculator 100 - 30
java AdvancedCalculator 25 * 4
java AdvancedCalculator 100 / 5
```

---

### 3B: String Manipulation Program

Create `StringOperations.java`:

```java
public class StringOperations {
    public static void main(String[] args) {
        System.out.println("=".repeat(50));
        System.out.println("String Operations");
        System.out.println("=".repeat(50));
        
        String text = "Jenkins CI/CD Automation";
        
        System.out.println("\nOriginal String: " + text);
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("Reversed: " + new StringBuilder(text).reverse());
        System.out.println("Contains 'CI': " + text.contains("CI"));
        System.out.println("Starts with 'Jenkins': " + text.startsWith("Jenkins"));
        System.out.println("Character at position 5: " + text.charAt(5));
        System.out.println("\n" + "=".repeat(50));
    }
}
```

---

## Exercise 4: Advanced Python Scripts

### 4A: Python with Command Line Arguments

Create `advanced_calculator.py`:

```python
#!/usr/bin/env python3
import sys
import math

def main():
    print("=" * 50)
    print("Advanced Python Calculator")
    print("=" * 50)
    
    if len(sys.argv) < 4:
        print("Usage: python advanced_calculator.py <num1> <operator> <num2>")
        print("Operators: +, -, *, /, %, ^, sqrt, log")
        sys.exit(1)
    
    try:
        num1 = float(sys.argv[1])
        operator = sys.argv[2]
        
        if operator in ['+', '-', '*', '/', '%']:
            num2 = float(sys.argv[3])
            
            if operator == '+':
                result = num1 + num2
            elif operator == '-':
                result = num1 - num2
            elif operator == '*':
                result = num1 * num2
            elif operator == '/':
                if num2 == 0:
                    print("Error: Division by zero!")
                    sys.exit(1)
                result = num1 / num2
            else:  # %
                result = num1 % num2
            
            print(f"Expression: {num1} {operator} {num2}")
        else:
            print(f"Error: Unknown operator '{operator}'")
            sys.exit(1)
        
        print("-" * 50)
        print(f"Result: {result:.2f}")
        print("=" * 50)
        
    except ValueError:
        print("Error: Invalid number format!")
        sys.exit(1)
    except Exception as e:
        print(f"Error: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()
```

**Jenkins Build Step:**
```batch
python C:\Jenkins-WorkSpace\python-projects\advanced_calculator.py 50 + 30
```

---

### 4B: Python File Operations

Create `file_operations.py`:

```python
#!/usr/bin/env python3
import os
from datetime import datetime

def main():
    print("=" * 50)
    print("Python File Operations & Logging")
    print("=" * 50)
    
    log_file = "build_log.txt"
    
    # Create log entry
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    log_message = f"[{timestamp}] Jenkins Build Executed Successfully\n"
    
    # Write to file
    with open(log_file, 'a') as f:
        f.write(log_message)
    
    # Read back
    print(f"\nLog file created: {log_file}")
    print("\nRecent entries:")
    print("-" * 50)
    
    with open(log_file, 'r') as f:
        lines = f.readlines()
        for line in lines[-5:]:  # Show last 5 lines
            print(line.strip())
    
    print("\n" + "=" * 50)
    print("Log file size:", os.path.getsize(log_file), "bytes")
    print("=" * 50)

if __name__ == "__main__":
    main()
```

---

## Exercise 5: Multi-Branch Pipeline

**Objective:** Create pipelines that respond to Git branches

**Steps:**

1. Push this repo with multiple branches:
```bash
git checkout -b develop
# Make changes
git commit -am "Development changes"
git push origin develop

git checkout -b feature/new-tests
# Make changes
git commit -am "New tests"
git push origin feature/new-tests
```

2. Create Pipeline from GitHub:
   - New Item → Pipeline
   - Pipeline script from SCM → Git
   - Repository URL: https://github.com/trush06/devops_prac3.git
   - Script path: `Jenkinsfile` (create in repo)

---

## Exercise 6: Load Testing Simulation

Create `load_test.py`:

```python
#!/usr/bin/env python3
import time
import statistics

def simulate_load():
    print("=" * 50)
    print("Simulated Load Testing")
    print("=" * 50)
    
    response_times = []
    
    print("\nSimulating 10 API requests...")
    for i in range(1, 11):
        start = time.time()
        # Simulate work
        time.sleep(0.1)
        end = time.time()
        
        response_time = (end - start) * 1000
        response_times.append(response_time)
        print(f"Request {i}: {response_time:.2f}ms ✓")
    
    print("-" * 50)
    print(f"Average Response Time: {statistics.mean(response_times):.2f}ms")
    print(f"Min Response Time: {min(response_times):.2f}ms")
    print(f"Max Response Time: {max(response_times):.2f}ms")
    print(f"Std Deviation: {statistics.stdev(response_times):.2f}ms")
    print("\n" + "=" * 50)

if __name__ == "__main__":
    simulate_load()
```

---

## Exercise 7: Create a Jenkinsfile

Create `Jenkinsfile` in repo root:

```groovy
pipeline {
    agent any
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }
    
    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\Jdk21'
        WORKSPACE_PATH = 'C:\\Jenkins-WorkSpace'
    }
    
    stages {
        stage('Setup') {
            steps {
                echo "Java: ${JAVA_HOME}"
                echo "Workspace: ${WORKSPACE_PATH}"
            }
        }
        
        stage('Build') {
            steps {
                echo "Building all programs..."
                bat '''
                    cd %WORKSPACE_PATH%\\java-projects
                    javac AddNumbers.java
                    javac AddNumbersInput.java
                '''
            }
        }
        
        stage('Test') {
            steps {
                echo "Running tests..."
                bat '''
                    cd %WORKSPACE_PATH%\\java-projects
                    java AddNumbers
                    java AddNumbersInput 100 50
                '''
                bat '''
                    cd %WORKSPACE_PATH%\\python-projects
                    python calculator.py
                '''
            }
        }
        
        stage('Report') {
            steps {
                echo "All tests passed!"
            }
        }
    }
    
    post {
        always {
            echo "Pipeline finished"
        }
        success {
            echo "✓ Build successful"
        }
        failure {
            echo "✗ Build failed"
        }
    }
}
```

Then create Pipeline job:
- New Item → Pipeline
- Pipeline script from SCM (Git)
- Repository: https://github.com/trush06/devops_prac3.git
- Script path: `Jenkinsfile`

---

## Exercise 8: Integration with GitHub Webhooks

**Objective:** Auto-trigger builds on code push

**Steps:**

1. Go to GitHub repo Settings → Webhooks
2. Add webhook:
   - Payload URL: `http://your-jenkins-url:8888/github-webhook/`
   - Content type: `application/json`
   - Events: Push events
   - Active: ✓

3. Create Multibranch Pipeline:
   - New Item → Multibranch Pipeline
   - Branch Sources → GitHub
   - Repository HTTPS URL
   - Save

4. Now every push to GitHub will trigger a build!

---

## Exercise 9: Performance Comparison

Create test to compare Java vs Python execution:

**Java vs Python Benchmark:**

```
Language | Compilation | Execution | Total Time
---------|------------|-----------|----------
Java     | 200ms      | 50ms      | 250ms
Python   | N/A        | 80ms      | 80ms
```

Create reports for each build!

---

## Exercise 10: Slack Notifications

**Setup:**

1. Create Slack app in your workspace
2. Install plugins: Manage Jenkins → Plugin Manager → Search "Slack"
3. Configure: Manage Jenkins → Configure System → Slack
4. Add webhook URL from Slack

**Pipeline Code:**

```groovy
post {
    success {
        slackSend (
            channel: '#jenkins-builds',
            color: 'good',
            message: "Build SUCCESS: ${env.BUILD_URL}"
        )
    }
    failure {
        slackSend (
            channel: '#jenkins-builds',
            color: 'danger',
            message: "Build FAILED: ${env.BUILD_URL}"
        )
    }
}
```

---

## 🎯 Challenge: Build Your Own Program

**Task:** Create your own Java or Python program and integrate it with Jenkins

**Requirements:**
- ✅ Takes input (hardcoded or command-line)
- ✅ Performs some calculation or operation
- ✅ Displays formatted output
- ✅ Returns proper exit codes
- ✅ Runs in Jenkins successfully

---

## 📊 Grading Rubric

| Criteria | Points |
|----------|--------|
| Freestyle Jobs Working | 25 |
| Basic Pipeline Executing | 25 |
| All Programs Output Correct | 25 |
| Enhanced Exercises (choose 3+) | 15 |
| Documentation & Comments | 10 |
| **Total** | **100** |

---

## 🚀 Next Steps for Production

Once comfortable with basics:

1. **Version Control:** Use Git branches for features
2. **Docker:** Containerize applications
3. **Testing:** Add unit tests and integration tests
4. **Artifact Storage:** Use artifact repositories
5. **Cloud Deployment:** Deploy to AWS/Azure/GCP
6. **Monitoring:** Add performance monitoring
7. **Security:** Secure secrets and credentials

---

Good luck! 🎊
