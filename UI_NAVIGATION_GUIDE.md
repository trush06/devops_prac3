# Jenkins Pipeline - Visual Guide & UI Navigation

## Jenkins Dashboard

When you go to `localhost:8888`, you'll see:

```
┌────────────────────────────────────────────────────────────┐
│                    Jenkins Dashboard                        │
├────────────────────────────────────────────────────────────┤
│                                                              │
│  Welcome to Jenkins                                          │
│                                                              │
│  New Item    │  People    │  Build History    │  Manage      │
│                                                              │
│  Quick Links:                                                │
│  • My Views                                                  │
│  • Credentials                                               │
│  • Configuration as Code                                     │
│                                                              │
│  Recent builds:                                              │
│  • Java-Addition-Job (Success ✓)                            │
│  • Python-Calculator-Job (Success ✓)                        │
│  • CI-CD-Pipeline (Success ✓)                               │
│                                                              │
└────────────────────────────────────────────────────────────┘
```

---

## Creating a Freestyle Job - UI Steps

### Step 1: Click "New Item"
```
Jenkins Home
  ↓
Click "New Item" (top left)
  ↓
Enter Job Name: "Java-Addition-Job"
  ↓
Select "Freestyle job"
  ↓
Click OK
```

### Step 2: Configure Job
```
┌─────────────────────────────────────────┐
│ Java-Addition-Job Configuration         │
├─────────────────────────────────────────┤
│                                         │
│ General                                 │
│ ├─ Description (optional)               │
│ ├─ Build Triggers                       │
│ │  └─ ☐ Build periodically              │
│ │  └─ ☐ GitHub hook trigger for GITScm  │
│                                         │
│ Build                                   │
│ ├─ Add build step ▼                     │
│ │  └─ Execute Windows batch command     │
│ │     cd C:\...\java-projects           │
│ │     javac AddNumbers.java             │
│ │     java AddNumbers                   │
│                                         │
│ Post-build Actions                      │
│ ├─ Add post-build action ▼              │
│                                         │
│ ┌─────────────────┐  ┌─────────────┐   │
│ │ Save            │  │ Cancel      │   │
│ └─────────────────┘  └─────────────┘   │
│                                         │
└─────────────────────────────────────────┘
```

---

## Creating a Pipeline Job - UI Steps

### Step 1: Create Pipeline Job
```
Jenkins Home
  ↓
Click "New Item"
  ↓
Enter Job Name: "CI-CD-Pipeline"
  ↓
Select "Pipeline" (Important: Not Freestyle!)
  ↓
Click OK
```

### Step 2: Enter Pipeline Script
```
┌──────────────────────────────────────────────────┐
│ CI-CD-Pipeline Configuration                     │
├──────────────────────────────────────────────────┤
│                                                  │
│ General                                          │
│ ├─ Description: "CI/CD using Java and Python"   │
│                                                  │
│ Pipeline                                         │
│ ├─ Definition: Pipeline script (selected)        │
│                                                  │
│ ◯ Pipeline script          (Select This!)        │
│ ◯ Pipeline script from SCM (For GitHub)          │
│                                                  │
│ Script: [Large Text Area]                        │
│ ┌────────────────────────────────────────┐       │
│ │ pipeline {                             │       │
│ │     agent any                          │       │
│ │     stages {                           │       │
│ │         stage('Java Compilation') {    │       │
│ │             steps {                    │       │
│ │                 bat 'cd ... && javac'  │       │
│ │             }                          │       │
│ │         }                              │       │
│ │     }                                  │       │
│ │ }                                      │       │
│ └────────────────────────────────────────┘       │
│                                                  │
│ ┌──────────────────┐  ┌────────────────┐       │
│ │ Save             │  │ Cancel         │       │
│ └──────────────────┘  └────────────────┘       │
│                                                  │
└──────────────────────────────────────────────────┘
```

---

## Running a Job/Pipeline

### Step 1: Click "Build Now"
```
Job: Java-Addition-Job
├─ Description
├─ ► Build Now          (Click this!)
├─ ► Configure
├─ ► Delete Job
│
Build History:
├─ #3  (Date/Time)  Success ✓
├─ #2  (Date/Time)  Success ✓
└─ #1  (Date/Time)  Success ✓
```

### Step 2: View Build Progress
```
Build #1 in progress...

Console Output:
Started by user admin
Running as SYSTEM
Building in workspace C:\...\workspace\Java-Addition-Job

+ javac AddNumbers.java
+ java AddNumbers
============================
Java Addition Program
============================
Number 1: 25
Number 2: 35
============================
Sum: 60
============================

Finished: SUCCESS
```

---

## Pipeline Visualization

### Pipeline Build Page

```
┌──────────────────────────────────────────────────────┐
│ CI-CD-Pipeline  #1                                   │
├──────────────────────────────────────────────────────┤
│                                                      │
│  Status: SUCCESS ✓                                   │
│  Duration: 2.5 seconds                               │
│  Started: Apr 16, 2026 10:30 AM                       │
│                                                      │
│  Job Details:                                        │
│  ├─ Console Output (top right)                       │
│  ├─ Archive Artifacts (if configured)                │
│  ├─ Edit (pencil icon)                               │
│  └─ Delete                                           │
│                                                      │
│  Pipeline View:                                      │
│  ┌──────────────────────────────────────────────┐   │
│  │    ┌─────────────────┐                      │   │
│  │    │ Initialization  │ (2 sec) ✓            │   │
│  │    └────────┬────────┘                      │   │
│  │             ↓                               │   │
│  │    ┌─────────────────┐                      │   │
│  │    │Java Compilation │ (1 sec) ✓            │   │
│  │    └────────┬────────┘                      │   │
│  │             ↓                               │   │
│  │    ┌─────────────────┐                      │   │
│  │    │  Java Addition  │ (0.5 sec) ✓          │   │
│  │    └────────┬────────┘                      │   │
│  │             ↓                               │   │
│  │    ┌─────────────────┐                      │   │
│  │    │Java with Input  │ (0.5 sec) ✓          │   │
│  │    └────────┬────────┘                      │   │
│  │             ↓                               │   │
│  │    ┌─────────────────┐                      │   │
│  │    │ Python Executor │ (1 sec) ✓            │   │
│  │    └────────┬────────┘                      │   │
│  │             ↓                               │   │
│  │    ┌─────────────────┐                      │   │
│  │    │ Final Report    │ (0.5 sec) ✓          │   │
│  │    └─────────────────┘                      │   │
│  └──────────────────────────────────────────────┘   │
│                                                      │
│  All stages completed successfully! ✓               │
│                                                      │
└──────────────────────────────────────────────────────┘
```

### Clicking on a Stage

```
Stage View for: "Execute Java Addition"

Logs:
+ java AddNumbers

============================
Java Addition Program
============================
Number 1: 25
Number 2: 35
============================
Sum: 60
============================

Duration: 0.5 seconds
Status: SUCCESS ✓
```

---

## Viewing Console Output for Entire Pipeline

```
Console Output for: CI-CD-Pipeline #1

Started by user admin
Running as SYSTEM
Building in workspace C:\...\CI-CD-Pipeline

[Pipeline] Start of Pipeline
[Pipeline] Stage - Initialization
[Pipeline] { (Initialization)
[Pipeline] echo
========================================
CI/CD Pipeline - Java & Python
========================================
Build Number: 1
[Pipeline] } (end Initialization)

[Pipeline] Stage - Java Compilation
[Pipeline] { (Java Compilation)
[Pipeline] bat
C:\Users\...>cd C:\\Jenkins-WorkSpace\\java-projects && javac AddNumbers.java
C:\Users\...>javac AddNumbersInput.java
[Pipeline] } (end Java Compilation)

[Pipeline] Stage - Execute Java Addition
[Pipeline] { (Execute Java Addition)
[Pipeline] bat
C:\Users\...>java AddNumbers
============================
Java Addition Program
============================
Number 1: 25
Number 2: 35
============================
Sum: 60
============================
[Pipeline] } (end Execute Java Addition)

[Pipeline] Stage - Execute Java with Input
[Pipeline] { (Execute Java with Input)
[Pipeline] bat
C:\Users\...>java AddNumbersInput 100 50
============================
Java Addition with Input
============================
Number 1: 100
Number 2: 50
-----------------------------
Sum: 150
============================
[Pipeline] } (end Execute Java with Input)

[Pipeline] Stage - Execute Python
[Pipeline] { (Execute Python)
[Pipeline] bat
C:\Users\...>python calculator.py
==================================================
Python Calculator Program
==================================================
Number 1: 50
Number 2: 30
Addition: 50 + 30 = 80
Subtraction: 50 - 30 = 20
Multiplication: 50 * 30 = 1500
Division: 50 / 30 = 1.67
==================================================
Calculation completed successfully!
==================================================
[Pipeline] } (end Execute Python)

[Pipeline] Stage - Final Report
[Pipeline] { (Final Report)
[Pipeline] echo
==========================================
All Stages Completed Successfully!
==========================================
[Pipeline] } (end Final Report)

[Pipeline] Post Actions
[Pipeline] { (Post Actions)
[Pipeline] echo
✓ BUILD SUCCESSFUL - All programs executed!
[Pipeline] } (end Post Actions)

[Pipeline] End of Pipeline

Finished: SUCCESS
```

---

## Common Jenkins UI Elements

### Build Status Icons

| Icon | Meaning |
|------|---------|
| 🟢 | Successful build |
| 🔴 | Failed build |
| 🟡 | Unstable build |
| ⚪ | Not built / Disabled |
| 🔵 | Currently building |

### Left Menu in Job

```
Job: Java-Addition-Job
├─ Status
├─ Changes
├─ Build Now
├─ Configure
├─ Delete Job
├─ View Console Output (latest)
├─ Rename
└─ More Actions ▼
```

### Build Triggers Options

```
Build Triggers:
☐ Trigger builds remotely
☐ Build after other projects are built
☐ Build periodically
  H H * * *  (Cron syntax: "every day at midnight")
☐ GitHub hook trigger for GITScm polling
☐ Poll SCM
  H/15 * * * *  (Every 15 minutes)
```

---

## Pipeline Syntax Tips

### Key Jenkins Variables

```groovy
${BUILD_NUMBER}     // Build number (1, 2, 3...)
${BUILD_ID}         // Build ID (unique identifier)
${WORKSPACE}        // Path to job workspace
${BUILD_TIMESTAMP}  // Timestamp of build start
${JOB_NAME}         // Name of this job
${STAGE_NAME}       // Name of current stage
```

### Echo for Output

```groovy
echo 'Simple message'
echo "Build number: ${BUILD_NUMBER}"
echo "=================================================="
```

### Multiple Commands

```groovy
// Option 1: Single line with &&
bat 'cd folder && javac file.java && java ClassName'

// Option 2: Multiple lines (better for readability)
bat '''
    cd C:\\folder
    javac file.java
    java ClassName
'''
```

---

## Expected Screen Progression

### 1️⃣ Jenkins Home
- See "New Item" button
- See "Java-Addition-Job", "Python-Calculator-Job", "CI-CD-Pipeline"

### 2️⃣ Click "CI-CD-Pipeline"
- See pipeline job configuration
- See "Build Now" button

### 3️⃣ Click "Build Now"
- Page shows "Build #1 in progress..."

### 4️⃣ Wait for stages to complete
- See pipeline stages turning GREEN one by one
- See "Build #1 SUCCESS ✓"

### 5️⃣ Click on a stage
- See the output of that stage
- See your program's output (Sum: 60, etc.)

### 6️⃣ Click "Console Output"
- See complete execution log
- See all three programs' outputs
- Verify everything worked

---

## Success Indicators ✓

When your Jenkins lab is working:

1. ✓ Stages are GREEN boxes
2. ✓ Pipeline says "SUCCESS"
3. ✓ Console shows your program outputs
4. ✓ Java Addition shows "Sum: 60"
5. ✓ Java Input shows "Sum: 150"
6. ✓ Python shows calculator results
7. ✓ No RED error boxes

---

## If Something Turns Red ❌

1. Click the RED stage
2. Look at the error message
3. Common errors:
   - "File not found" → Check path in command
   - "javac is not recognized" → Add Java to Path
   - "python is not recognized" → Add Python to Path
   - "Permission denied" → Run Jenkins as Admin

---

## Quick Navigation Reference

| What to Do | Where to Click |
|-----------|---------------|
| See all jobs | Jenkins Home |
| Build a job | Job page → "Build Now" |
| View output | Build number → "Console Output" |
| Edit job | Job page → "Configure" |
| Delete job | Job page → "Delete Job" |
| See stage output | Pipeline page → Click stage box |
| Manage Jenkins | Home → "Manage Jenkins" |
| Check plugins | Home → "Manage Jenkins" → "Plugins" |

---

## Jenkins Keyboard Shortcuts

| Shortcut | Action |
|----------|--------|
| `?` | Show help on any page |
| `j` | Jump to console output (on build page) |
| `#` | Jump to build number (on job page) |

(Press `?` on any Jenkins page to see available shortcuts)

---

## Pro Tips 💡

1. **Bookmark localhost:8888** for quick access

2. **Open Console Output in new tab:** Right-click "Console Output" → Open in tab

3. **Refresh page with F5** to see latest build status

4. **Check "System log"** in Manage Jenkins for overall errors

5. **Use small test values** before running full tests
   - Test with `java AddNumbersInput 5 3` before real data

6. **Add extra echo commands** for debugging:
   ```groovy
   bat 'echo Current directory:'
   bat 'cd'
   bat 'echo Files:'
   bat 'dir'
   ```

---

Good luck with your Jenkins lab! 🚀
