# Jenkins CI/CD Lab - Complete Setup Guide

## Part 1: Create Freestyle Jobs

### Job 1: Java Program - Numeric Addition

#### Step 1: Create Job
1. Go to Jenkins Dashboard (localhost:8888)
2. Click **New Item**
3. Enter Job Name: `Java-Addition-Job`
4. Select **Freestyle job**
5. Click **OK**

#### Step 2: Configure Job
1. Go to **Source Code Management** → Select **Git**
2. Repository URL: `https://github.com/your-username/jenkins-java-demo.git` (Use local path for now)
3. Go to **Build** section
4. Click **Add build step** → **Execute Windows batch command**
5. Enter:
```batch
javac AddNumbers.java
java AddNumbers
```

#### Step 3: Save and Build
- Click **Save**
- Click **Build Now**

---

### Job 2: Java Program - Command-line Input

#### Step 1: Create Job
- New Item → Name: `Java-InputProgram-Job` → Freestyle job

#### Step 2: Configure Build Steps
Build command:
```batch
javac AddNumbersInput.java
java AddNumbersInput 10 20
```

---

### Job 3: Python Program

#### Step 1: Create Job
- New Item → Name: `Python-Calculator-Job` → Freestyle job

#### Step 2: Configure Build Steps
Build command:
```batch
python calculator.py
```

---

## Part 2: Create Jenkins Pipeline

### Pipeline Creation Steps

#### Step 1: Create Pipeline Job
1. Click **New Item**
2. Enter Name: `CI-CD-Pipeline`
3. Select **Pipeline**
4. Click **OK**

#### Step 2: Pipeline Configuration
1. Scroll down to **Pipeline** section
2. Select **Pipeline script** radio button
3. Paste the pipeline script (see Part 3 below)
4. Click **Save**
5. Click **Build Now**

---

## Part 3: Pipeline Script Examples

### Example 1: Simple Declarative Pipeline

```groovy
pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                // For local files, you can skip git checkout
            }
        }
        
        stage('Build Java') {
            steps {
                echo 'Building Java program...'
                bat 'cd C:\\path\\to\\java\\files && javac AddNumbers.java'
            }
        }
        
        stage('Test Java') {
            steps {
                echo 'Testing Java program...'
                bat 'cd C:\\path\\to\\java\\files && java AddNumbers'
            }
        }
        
        stage('Build Python') {
            steps {
                echo 'Running Python program...'
                bat 'python calculator.py'
            }
        }
        
        stage('Final Results') {
            steps {
                echo 'Pipeline execution completed successfully!'
            }
        }
    }
    
    post {
        success {
            echo 'SUCCESS: All stages completed!'
        }
        failure {
            echo 'FAILED: Pipeline execution failed!'
        }
    }
}
```

### Example 2: Advanced Pipeline with Parameters

```groovy
pipeline {
    agent any
    
    parameters {
        string(name: 'NUM1', defaultValue: '5', description: 'First Number')
        string(name: 'NUM2', defaultValue: '10', description: 'Second Number')
    }
    
    stages {
        stage('Prepare') {
            steps {
                echo "Parameters: NUM1=${params.NUM1}, NUM2=${params.NUM2}"
            }
        }
        
        stage('Compile') {
            steps {
                bat 'cd C:\\path\\to\\files && javac AddNumbers.java'
            }
        }
        
        stage('Execute') {
            steps {
                bat 'cd C:\\path\\to\\files && java AddNumbers'
            }
        }
        
        stage('Report') {
            steps {
                echo 'Build completed successfully!'
            }
        }
    }
}
```

---

## Part 4: Setting Up Jenkins with Local Files

### Option A: Using Local File System

1. Create a workspace folder:
   - `C:\Jenkins-WorkSpace\java-projects`
   - `C:\Jenkins-WorkSpace\python-projects`

2. Place your source files there

3. In Jenkins job, use batch commands with full paths:
```batch
cd C:\Jenkins-WorkSpace\java-projects
javac AddNumbers.java
java AddNumbers
```

### Option B: Using Git Repository

1. Initialize a Git repository locally
2. Add your files to Git
3. Create a bare repository on your machine
4. Use local file path as repository URL in Jenkins

---

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Java not found | Add JAVA_HOME to System Path: `C:\Program Files\Java\Jdk21\bin` |
| Python not found | Add Python to System Path |
| Permission denied | Run Jenkins as Administrator |
| Port 8888 already in use | Change Jenkins port in configuration |
| Build fails without error | Check Console Output in Jenkins UI |

---

## Quick Commands to Test Locally

Before adding to Jenkins, test your programs:

```batch
# Java
javac AddNumbers.java
java AddNumbers

# Python
python calculator.py
```

---

## Expected Output Examples

- Java Addition: `Sum: 15` (or similar output)
- Python Calculator: `Result: [operation result]`
- Pipeline: All stages show as GREEN ✓
