# Quick Jenkins Pipeline Reference

## What is a Pipeline?

A **Pipeline** is a series of automated steps (stages) that run in sequence. Each stage can:
- Build code
- Run tests
- Execute programs
- Deploy applications
- Generate reports

## Pipeline vs Freestyle Job

| Feature | Freestyle | Pipeline |
|---------|-----------|----------|
| Complexity | Simple | Advanced |
| Visibility | Single build | Multiple stages |
| Version Control | Manual | Code-defined |
| Reusability | Limited | High |
| Use Case | Single task | Complete workflow |

---

## Basic Pipeline Structure

```groovy
pipeline {
    agent any                  // Where to run (any available agent)
    
    stages {                   // List of stages
        stage('Stage 1') {
            steps {            // What to do
                echo 'Doing something'
            }
        }
        
        stage('Stage 2') {
            steps {
                echo 'Doing next thing'
            }
        }
    }
    
    post {                     // After pipeline finishes
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
```

---

## Common Commands in Pipeline

### For Windows (Batch):
```groovy
bat 'command here'
bat 'cd folder && javac File.java'
bat 'java ClassName'
bat 'python script.py'
```

### For Linux/Mac (Shell):
```groovy
sh 'command here'
sh 'cd folder && javac File.java'
sh 'java ClassName'
sh 'python script.py'
```

---

## Creating a Declarative Pipeline (Easy Version)

### 5 Simple Steps:

1. **Go to Jenkins** → New Item
2. **Enter Name** → CI-CD-Pipeline
3. **Choose** → Pipeline
4. **Click** → OK
5. **Paste** → Pipeline script in the text area
6. **Click** → Save
7. **Click** → Build Now

That's it! Your pipeline will run!

---

## Your Lab Pipeline Breakdown

```groovy
pipeline {
    agent any
    
    stages {
        // STAGE 1: Setup and info
        stage('Initialization') {
            steps {
                echo 'Starting pipeline...'
            }
        }
        
        // STAGE 2: Compile Java
        stage('Java Compilation') {
            steps {
                bat 'javac AddNumbers.java'
            }
        }
        
        // STAGE 3: Run Java 1
        stage('Execute Java Addition') {
            steps {
                bat 'java AddNumbers'
                // Output: Sum: 60
            }
        }
        
        // STAGE 4: Run Java 2
        stage('Execute Java with Input') {
            steps {
                bat 'java AddNumbersInput 100 50'
                // Output: Sum: 150
            }
        }
        
        // STAGE 5: Run Python
        stage('Execute Python') {
            steps {
                bat 'python calculator.py'
                // Output: All calculator operations
            }
        }
        
        // STAGE 6: Final message
        stage('Final Report') {
            steps {
                echo 'All stages completed!'
            }
        }
    }
    
    // What happens after pipeline finishes
    post {
        success {
            echo 'SUCCESS - All programs ran!'
        }
        failure {
            echo 'FAILED - Check errors above'
        }
    }
}
```

---

## Key Pipeline Syntax Elements

| Element | Purpose | Example |
|---------|---------|---------|
| `pipeline` | Start pipeline block | `pipeline { ... }` |
| `agent` | Run location | `agent any` |
| `stages` | Container for stages | `stages { ... }` |
| `stage` | Individual step | `stage('Name') { ... }` |
| `steps` | Commands to run | `steps { bat 'command' }` |
| `post` | After execution | `post { success { ... } }` |
| `echo` | Print output | `echo 'Hello'` |
| `bat` | Windows command | `bat 'dir'` |
| `sh` | Linux command | `sh 'ls'` |

---

## Common Mistakes to Avoid ❌

### ❌ WRONG: Using forward slashes in Windows paths
```groovy
bat 'cd C:/Jenkins-WorkSpace/java'  // WRONG!
```

### ✓ CORRECT: Use backslashes
```groovy
bat 'cd C:\\Jenkins-WorkSpace\\java'  // RIGHT!
```

### ❌ WRONG: Missing double backslashes
```groovy
bat 'cd C:\Jenkins'  // WRONG - single backslash is escape character
```

### ✓ CORRECT: Double backslashes in Groovy
```groovy
bat 'cd C:\\Jenkins'  // RIGHT
```

### ❌ WRONG: Forgetting quotes
```groovy
bat echo hello  // WRONG
```

### ✓ CORRECT: Use quotes
```groovy
bat 'echo hello'  // RIGHT
```

### ❌ WRONG: Using && in Windows batch
```groovy
bat 'cd folder && javac File.java'  // Unreliable in Jenkins
```

### ✓ CORRECT: Chain commands properly
```groovy
bat '''
    cd folder
    javac File.java
'''
```

---

## Debugging Your Pipeline

### If stage turns RED:

1. **Check Console Output:**
   - Click build number
   - Click "Console Output"
   - Look for red error text

2. **Common Errors:**
   - File not found → Check path in batch command
   - Command not found → Check if software installed
   - Compilation error → Check Java code syntax
   - Permission denied → Run Jenkins as Admin

3. **Use Echo for debugging:**
```groovy
stage('Debug') {
    steps {
        echo 'Current path:'
        bat 'cd'
        echo 'List files:'
        bat 'dir C:\\Jenkins-WorkSpace\\java-projects'
        echo 'Java version:'
        bat 'java -version'
    }
}
```

---

## After Each Stage Completes

### What You'll See:
- Stage box becomes **GREEN** = Success ✓
- Shows **time taken**
- Can click stage to see its output
- Next stage automatically starts

### If Stage Fails (RED):
- Stage box becomes **RED** ✗
- Pipeline **stops**
- Fix the error
- Click "Build Now" again

---

## Expected Output When Everything Works

```
[Pipeline] Start of Pipeline
Initialized
========================================
CI/CD Pipeline - Java & Python
========================================
Build Number: 1

[Pipeline] Stage - Java Compilation
Compiling Java Programs...

[Pipeline] Stage - Execute Java Addition
============================
Java Addition Program
============================
Number 1: 25
Number 2: 35
============================
Sum: 60
============================

[Pipeline] Stage - Execute Java with Input
============================
Java Addition with Input
============================
Number 1: 100
Number 2: 50
-----------------------------
Sum: 150
============================

[Pipeline] Stage - Execute Python
==================================================
Python Calculator Program
==================================================
Addition: 50 + 30 = 80
Subtraction: 50 - 30 = 20
Multiplication: 50 * 30 = 1500
Division: 50 / 30 = 1.67
==================================================

[Pipeline] Stage - Final Report
==========================================
All Stages Completed Successfully!
==========================================

✓ BUILD SUCCESSFUL - All programs executed!
```

---

## Pipeline Visualization

Your pipeline flow in Jenkins:

```
START
  ↓
[Initialization] ✓
  ↓
[Java Compilation] ✓
  ↓
[Execute Java Addition] ✓
  ↓
[Execute Java with Input] ✓
  ↓
[Execute Python] ✓
  ↓
[Final Report] ✓
  ↓
SUCCESS ✓
```

All GREEN boxes = Pipeline Success!

---

## Tips & Tricks

1. **Test locally first:**
   ```batch
   cd C:\Jenkins-WorkSpace\java-projects
   javac AddNumbers.java
   java AddNumbers
   ```
   If this works locally, it will work in Jenkins.

2. **Use descriptive stage names:**
   ```groovy
   stage('Compile Java Programs')    // Good
   stage('Compile')                  // Vague
   stage('1')                        // Bad
   ```

3. **Add timestamps:**
   ```groovy
   options {
       timestamps()
   }
   ```
   Shows when each log line was generated.

4. **Add timeout:**
   ```groovy
   options {
       timeout(time: 10, unit: 'MINUTES')
   }
   ```
   Stops build if it takes too long.

5. **View stage duration:**
   - Click pipeline build
   - See how long each stage took

---

## Next Steps After Lab

Once you master basic pipelines:

1. **Add Git integration:**
   - Pull code from GitHub
   - Auto-build on code changes

2. **Add Webhooks:**
   - Build when code is pushed

3. **Add Testing:**
   - Run unit tests in pipeline
   - Fail if tests don't pass

4. **Add Notifications:**
   - Email on success/failure
   - Slack messages

5. **Add Artifacts:**
   - Save compiled files
   - Archive build outputs

---

## Remember

- **Pipeline = Automated Workflow**
- **Each Stage = One Task**
- **Green = Success, Red = Failed**
- **Console Output = Your Debug Tool**
- **Test Locally First!**

Keep it simple, test everything locally before adding to Jenkins, and gradually add complexity!

Good luck! 🚀
