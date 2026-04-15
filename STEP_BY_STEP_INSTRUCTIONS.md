# Step-by-Step: Create Your First Jenkins Pipeline

## Prerequisites
- Jenkins running on localhost:8888
- JDK 17/21 installed
- Python installed
- Java and Python files ready

---

## STEP 1: Prepare Your Files

### Create workspace folders:
```
C:\Jenkins-WorkSpace\
├── java-projects\
│   ├── AddNumbers.java
│   └── AddNumbersInput.java
└── python-projects\
    └── calculator.py
```

### Option A: Copy files (Windows)
```batch
mkdir C:\Jenkins-WorkSpace\java-projects
mkdir C:\Jenkins-WorkSpace\python-projects

# Copy Java files
copy AddNumbers.java C:\Jenkins-WorkSpace\java-projects\
copy AddNumbersInput.java C:\Jenkins-WorkSpace\java-projects\

# Copy Python files
copy calculator.py C:\Jenkins-WorkSpace\python-projects\
```

### Option B: Or use provided files in this folder

---

## STEP 2: Verify Environment Variables

Open Command Prompt and verify:

```batch
# Check Java
javac -version
java -version

# Check Python
python --version
```

If not found, add to System Path:
- JAVA_HOME: `C:\Program Files\Java\Jdk21` (or JDK17)
- Python: `C:\Users\{username}\AppData\Local\Programs\Python\Python312` (or your version)

---

## STEP 3: Test Programs Locally

Before using in Jenkins, test each program:

### Test Java Addition:
```batch
cd C:\Jenkins-WorkSpace\java-projects
javac AddNumbers.java
java AddNumbers
```

Expected Output:
```
============================
Java Addition Program
============================
Number 1: 25
Number 2: 35
============================
Sum: 60
============================
```

### Test Java with Input:
```batch
java AddNumbersInput 100 50
```

Expected Output:
```
============================
Java Addition with Input
============================
Number 1: 100
Number 2: 50
-----------------------------
Sum: 150
============================
```

### Test Python:
```batch
cd C:\Jenkins-WorkSpace\python-projects
python calculator.py
```

Expected Output:
```
==================================================
Python Calculator Program
==================================================
Number 1: 50
Number 2: 30
--------------------------------------------------
Addition: 50 + 30 = 80
Subtraction: 50 - 30 = 20
Multiplication: 50 * 30 = 1500
Division: 50 / 30 = 1.67
==================================================
Calculation completed successfully!
==================================================
```

---

## STEP 4: Create First Freestyle Job (Java Addition)

### In Jenkins Dashboard:

1. Click **New Item**
2. Enter Job Name: **`Java-Addition-Job`**
3. Select **Freestyle job**
4. Click **OK**

### Configure Job:

5. Scroll down to **Build** section
6. Click **Add build step**
7. Select **Execute Windows batch command**
8. Enter:
```batch
cd C:\Jenkins-WorkSpace\java-projects
javac AddNumbers.java
java AddNumbers
```

9. Click **Save**

### Run Job:

10. Click **Build Now**
11. Wait for build to complete (green checkmark = success)
12. Click on build number → **Console Output** to see results

---

## STEP 5: Create Second Freestyle Job (Java with Input)

Repeat STEP 4 but:
- Job Name: **`Java-InputProgram-Job`**
- Build command:
```batch
cd C:\Jenkins-WorkSpace\java-projects
javac AddNumbersInput.java
java AddNumbersInput 100 50
```

---

## STEP 6: Create Third Freestyle Job (Python)

Repeat STEP 4 but:
- Job Name: **`Python-Calculator-Job`**
- Build command:
```batch
cd C:\Jenkins-WorkSpace\python-projects
python calculator.py
```

---

## STEP 7: Create Your First Pipeline 🎯

This is the main objective!

### Create Pipeline Job:

1. Click **New Item**
2. Enter Name: **`CI-CD-Pipeline`**
3. Select **Pipeline** (not Freestyle!)
4. Click **OK**

### Configure Pipeline:

5. Scroll down to **Pipeline** section
6. Select **Pipeline script** (first radio button)
7. Copy-paste the pipeline script below into the text area
8. Click **Save**

### Pipeline Script:

```groovy
pipeline {
    agent any
    
    options {
        timestamps()
        timeout(time: 15, unit: 'MINUTES')
    }
    
    stages {
        stage('Initialization') {
            steps {
                echo '=========================================='
                echo 'CI/CD Pipeline - Java & Python'
                echo '=========================================='
                echo "Build Number: ${BUILD_NUMBER}"
            }
        }
        
        stage('Java Compilation') {
            steps {
                echo '--- Stage 1: Compiling Java Programs ---'
                bat 'cd C:\\Jenkins-WorkSpace\\java-projects && javac AddNumbers.java'
                bat 'cd C:\\Jenkins-WorkSpace\\java-projects && javac AddNumbersInput.java'
            }
        }
        
        stage('Execute Java Addition') {
            steps {
                echo '--- Stage 2: Running Java Addition ---'
                bat 'cd C:\\Jenkins-WorkSpace\\java-projects && java AddNumbers'
            }
        }
        
        stage('Execute Java with Input') {
            steps {
                echo '--- Stage 3: Running Java with Input ---'
                bat 'cd C:\\Jenkins-WorkSpace\\java-projects && java AddNumbersInput 100 50'
            }
        }
        
        stage('Execute Python') {
            steps {
                echo '--- Stage 4: Running Python Calculator ---'
                bat 'cd C:\\Jenkins-WorkSpace\\python-projects && python calculator.py'
            }
        }
        
        stage('Final Report') {
            steps {
                echo '=========================================='
                echo 'All Stages Completed Successfully!'
                echo '=========================================='
            }
        }
    }
    
    post {
        success {
            echo '✓ BUILD SUCCESSFUL - All programs executed!'
        }
        failure {
            echo '✗ BUILD FAILED - Check console output'
        }
    }
}
```

### Run Pipeline:

9. Click **Build Now**
10. Watch the pipeline stages execute
11. Each stage will turn GREEN if successful
12. Click on the stage name to see detailed output

---

## STEP 8: Understanding Pipeline Stages

Your pipeline has 5 stages:

| Stage | What it does | Status |
|-------|-------------|--------|
| Initialization | Shows pipeline info | ✓ |
| Java Compilation | Compiles Java files | ✓ |
| Execute Java Addition | Runs AddNumbers.java | ✓ |
| Execute Java with Input | Runs AddNumbersInput.java | ✓ |
| Execute Python | Runs calculator.py | ✓ |
| Final Report | Shows completion message | ✓ |

Each GREEN checkmark = Stage completed successfully!

---

## STEP 9: View Pipeline Results

### Pipeline View:
1. Go to your pipeline job
2. Click **Build Number** (e.g., #1, #2)
3. See stage overview on left side
4. Each stage shows execution time

### Console Output:
1. Click **Console Output**
2. See complete execution log
3. Look for your program outputs
4. Check for errors (red text)

---

## TROUBLESHOOTING

### Issue: "javac is not recognized"
**Solution:** 
1. Add Java to Path: 
   - Settings → Environment Variables → Path
   - Add: `C:\Program Files\Java\Jdk21\bin`
2. Restart Command Prompt
3. Verify: `javac -version`

### Issue: "python is not recognized"
**Solution:**
1. Add Python to Path
2. Or use full path: `C:\Users\{username}\AppData\Local\Programs\Python\Python312\python.exe calculator.py`

### Issue: Pipeline stage turns RED
**Solution:**
1. Click stage name
2. Read error message
3. Check Console Output
4. Look for common errors:
   - File not found → Check file paths
   - Compilation error → Check Java code
   - Module not found → Check imports

### Issue: Build hangs
**Solution:**
1. The timeout option will stop after 15 minutes
2. Manually click **Stop Build** button
3. Check what's causing delay

---

## SUCCESS CRITERIA ✓

Your lab is complete when:

1. ✓ Jenkins Dashboard loads on localhost:8888
2. ✓ All 3 Freestyle jobs execute successfully
3. ✓ Pipeline has all 5 stages turning GREEN
4. ✓ Program outputs visible in console
5. ✓ Java Addition result: 60
6. ✓ Java Input result: 150
7. ✓ Python Calculator shows all 4 operations

---

## Next Steps (Advanced)

To make your pipeline even better:

1. **Add Git Integration:**
   - Store code in GitHub
   - Pipeline automatically pulls from Git

2. **Add Email Notifications:**
   - Email results after build

3. **Add Testing:**
   - Verify outputs match expected values

4. **Add Artifacts:**
   - Save compilation outputs

---

## Quick Reference Commands

```batch
# Windows Batch Commands

# Change directory
cd C:\Jenkins-WorkSpace\java-projects

# Compile Java
javac FileName.java

# Run Java
java ClassName

# Run Python
python filename.py

# Check version
java -version
python --version

# List files
dir
```

---

## Important Notes

- **DO NOT** use forward slashes in Windows paths in batch files - use backslashes
- Jenkins **must** be running before you can build jobs
- **Console Output** is your best friend for debugging
- Always test locally first, then add to Jenkins
- Different stages fail independently - one stage failure stops pipeline

---

Good luck! 🚀
