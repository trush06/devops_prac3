# Jenkins CI/CD Lab - DevOps Practical 3

![Jenkins](https://img.shields.io/badge/Jenkins-Latest-blue)
![Java](https://img.shields.io/badge/Java-JDK%2017%2F21-orange)
![Python](https://img.shields.io/badge/Python-3.x-green)
![License](https://img.shields.io/badge/License-MIT-green)

A comprehensive Jenkins CI/CD automation lab demonstrating job creation, pipeline building, and continuous integration using Java and Python programs.

---

## 📋 Lab Overview

This lab teaches you how to:
- ✅ Install and configure Jenkins on Windows
- ✅ Create **Freestyle Jobs** for Java and Python programs
- ✅ Build **Jenkins Pipelines** with multiple stages
- ✅ Understand CI/CD automation concepts
- ✅ Execute automated builds and tests

**Lab Number:** DevOps Practical 3  
**Difficulty:** Beginner to Intermediate  
**Duration:** 1-2 hours

---

## 📁 Repository Structure

```
devops_prac3/
├── README.md                          # This file
├── STEP_BY_STEP_INSTRUCTIONS.md       # 👈 START HERE - Complete walkthrough
├── PIPELINE_QUICK_REFERENCE.md        # Pipeline syntax and concepts
├── JENKINS_SETUP_GUIDE.md             # Comprehensive reference
├── UI_NAVIGATION_GUIDE.md             # Jenkins UI visual guide
├── AddNumbers.java                    # Java: Simple addition
├── AddNumbersInput.java               # Java: Command-line input
├── calculator.py                      # Python: Calculator operations
└── simple-pipeline.groovy             # Groovy: Pipeline script template
```

---

## 🎯 Quick Start Guide

### Step 1: Prerequisites
- **Operating System:** Windows 10 or later
- **Java:** JDK 17 or JDK 21  
- **Jenkins:** Latest version (running on port 8888)
- **Python:** 3.8 or later
- **Git:** Installed and configured

### Step 2: Clone This Repository

```bash
git clone https://github.com/trush06/devops_prac3.git
cd devops_prac3
```

### Step 3: Set Up Workspace

Create local Jenkins workspace folders:

```batch
# Windows Command Prompt
mkdir C:\Jenkins-WorkSpace\java-projects
mkdir C:\Jenkins-WorkSpace\python-projects

# Copy Java files
copy AddNumbers.java C:\Jenkins-WorkSpace\java-projects\
copy AddNumbersInput.java C:\Jenkins-WorkSpace\java-projects\

# Copy Python files
copy calculator.py C:\Jenkins-WorkSpace\python-projects\
```

### Step 4: Test Programs Locally

```batch
# Test Java
cd C:\Jenkins-WorkSpace\java-projects
javac AddNumbers.java
java AddNumbers

# Test Python
cd C:\Jenkins-WorkSpace\python-projects
python calculator.py
```

### Step 5: Follow the Guide

👉 **Open [STEP_BY_STEP_INSTRUCTIONS.md](STEP_BY_STEP_INSTRUCTIONS.md)** for detailed walkthrough

---

## 📚 Lab Jobs

### Job 1: Java Addition (Freestyle)
- **Name:** `Java-Addition-Job`
- **Type:** Freestyle Job
- **Program:** `AddNumbers.java`
- **Expected Output:** `Sum: 60`

### Job 2: Java Input (Freestyle)
- **Name:** `Java-InputProgram-Job`
- **Type:** Freestyle Job
- **Program:** `AddNumbersInput.java`
- **Input:** `100 50`
- **Expected Output:** `Sum: 150`

### Job 3: Python Calculator (Freestyle)
- **Name:** `Python-Calculator-Job`
- **Type:** Freestyle Job
- **Program:** `calculator.py`
- **Operations:** Addition, Subtraction, Multiplication, Division

### Pipeline Job: CI/CD Pipeline
- **Name:** `CI-CD-Pipeline`
- **Type:** Pipeline
- **Stages:** 5+ stages including compilation, execution, and reporting
- **Status:** All GREEN ✓

---

## 🔧 Program Details

### AddNumbers.java
Simple Java program with hardcoded values:
```java
Number 1: 25
Number 2: 35
Sum: 60
```

### AddNumbersInput.java
Java program that accepts command-line arguments:
```bash
java AddNumbersInput 100 50
```
Output: `Sum: 150`

### calculator.py
Python calculator performing multiple operations:
```
Addition: 80
Subtraction: 20
Multiplication: 1500
Division: 1.67
```

### simple-pipeline.groovy
Reusable pipeline script with 5 stages:
1. Initialization
2. Java Compilation
3. Execute Java Addition
4. Execute Java with Input
5. Execute Python
6. Final Report

---

## 📖 Documentation Files

| File | Purpose | Best For |
|------|---------|----------|
| **STEP_BY_STEP_INSTRUCTIONS.md** | Complete beginner-friendly walkthrough | First-time users |
| **PIPELINE_QUICK_REFERENCE.md** | Syntax, concepts, and best practices | Learning pipeline syntax |
| **JENKINS_SETUP_GUIDE.md** | Comprehensive reference guide | Troubleshooting |
| **UI_NAVIGATION_GUIDE.md** | Visual Jenkins UI guide | Understanding Jenkins UI |

---

## 🚀 Jenkins CI/CD Pipeline

Your pipeline will execute in this order:

```
START
  ↓
[Initialization] ✓
  ↓
[Java Compilation] ✓
  ↓
[Execute Java Addition] ✓ (Output: Sum: 60)
  ↓
[Execute Java with Input] ✓ (Output: Sum: 150)
  ↓
[Execute Python] ✓ (Output: Calculator results)
  ↓
[Final Report] ✓
  ↓
SUCCESS ✓
```

---

## 🐛 Troubleshooting

### Issue: "javac is not recognized"
**Solution:** Add Java to Windows PATH
```
Settings → Environment Variables → System Path
Add: C:\Program Files\Java\Jdk21\bin
```

### Issue: "python is not recognized"
**Solution:** Add Python to Windows PATH
```
Settings → Environment Variables → System Path
Add: C:\Users\{username}\AppData\Local\Programs\Python\Python312
```

### Issue: Pipeline stage turns RED
**Solution:**
1. Click the RED stage
2. Check error message
3. Review Console Output
4. Verify file paths are correct

### Issue: Jenkins won't start on port 8888
**Solution:**
1. Check if port is in use: `netstat -ano | findstr :8888`
2. Change Jenkins port in installation settings
3. Restart Jenkins service

---

## ✅ Expected Output

When everything works correctly, you should see:

```
========================================
Java Addition Program
========================================
Number 1: 25
Number 2: 35
Sum: 60
========================================

========================================
Java Addition with Input
========================================
Number 1: 100
Number 2: 50
Sum: 150
========================================

==================================================
Python Calculator Program
==================================================
Addition: 50 + 30 = 80
Subtraction: 50 - 30 = 20
Multiplication: 50 * 30 = 1500
Division: 50 / 30 = 1.67
==================================================
Calculation completed successfully!
==================================================

✓ BUILD SUCCESSFUL - All programs executed!
```

---

## 📊 Success Criteria

Your lab is complete when:

- ✅ Jenkins Dashboard loads on localhost:8888
- ✅ All 3 Freestyle jobs execute successfully (GREEN status)
- ✅ Pipeline has 5+ stages turning GREEN
- ✅ Java programs produce correct outputs
- ✅ Python calculator displays all operations
- ✅ Console Output shows no errors

---

## 🎓 Learning Outcomes

After completing this lab, you will understand:

1. **Jenkins Fundamentals**
   - Dashboard navigation
   - Job configuration
   - Build execution

2. **Freestyle Jobs**
   - Simple automation
   - Build steps
   - Console output

3. **Jenkins Pipelines**
   - Pipeline syntax (Groovy DSL)
   - Multiple stages
   - Sequential execution
   - Post-build actions

4. **CI/CD Concepts**
   - Continuous integration
   - Automated builds
   - Testing automation
   - Deployment pipelines

5. **Windows Batch Commands**
   - Directory navigation
   - Program compilation
   - Program execution

---

## 🔗 Useful Resources

- **Jenkins Official:** https://www.jenkins.io/
- **Pipeline Documentation:** https://www.jenkins.io/doc/book/pipeline/
- **Groovy Documentation:** https://groovy-lang.org/
- **Java Documentation:** https://docs.oracle.com/javase/tutorial/
- **Python Documentation:** https://docs.python.org/3/

---

## 👨‍💼 Author & Contact

**Developer:** Trush Vichhawawala  
**Email:** official@gmail.com  
**Lab:** DevOps Practical 3  
**Last Updated:** April 16, 2026

---

## 📝 Lab Handout Info

**Aim:**  
To configure Jenkins on a local machine and create Jenkins jobs and pipelines using Java and Python programs to understand basic CI/CD automation.

**Software Tools:**
- Operating System: Windows
- Java Development Kit (JDK 17 or JDK 21)
- Jenkins (Latest Version)
- Web Browser (Chrome/Edge)
- Command Prompt/PowerShell

**Theory:**  
Jenkins is an open-source automation server used for Continuous Integration and Continuous Delivery (CI/CD). It automates building, testing, and deploying software.

---

## 📄 License

This project is licensed under the MIT License - see LICENSE file for details.

---

## 🎯 Next Steps

1. ✅ Read [STEP_BY_STEP_INSTRUCTIONS.md](STEP_BY_STEP_INSTRUCTIONS.md)
2. ✅ Set up your Jenkins workspace
3. ✅ Test all programs locally
4. ✅ Create Freestyle jobs in Jenkins
5. ✅ Create your first Pipeline
6. ✅ Execute the complete CI/CD workflow
7. ✅ Verify all outputs match expected results
8. ✅ Celebrate your success! 🎉

---

## 🆘 Need Help?

If you encounter issues:

1. **Check Console Output in Jenkins** - Most errors are shown here
2. **Verify file paths** - Ensure all paths use correct backslashes
3. **Test locally first** - Always test programs before adding to Jenkins
4. **Review documentation** - Each `.md` file covers different aspects
5. **Check system PATH variables** - Ensure Java and Python are properly installed

---

## 🚀 Happy Learning!

This lab is designed to teach you practical CI/CD concepts through hands-on experience with Jenkins. Take your time, test everything locally first, and don't hesitate to review the documentation files when needed.

**Good luck! 🎊**
