// Simple Jenkins Pipeline - Windows Compatible
// Usage: Create Pipeline job and paste this script

pipeline {
    agent any
    
    options {
        timestamps()
        timeout(time: 10, unit: 'MINUTES')
    }
    
    stages {
        stage('Initialization') {
            steps {
                echo '=========================================='
                echo 'CI/CD Pipeline - Java & Python Execution'
                echo '=========================================='
                echo "Build Number: ${BUILD_NUMBER}"
                echo "Build ID: ${BUILD_ID}"
                echo "Workspace: ${WORKSPACE}"
            }
        }
        
        stage('Java Setup') {
            steps {
                echo '--- Java Setup Stage ---'
                bat 'java -version'
                bat 'javac -version'
            }
        }
        
        stage('Java Compilation') {
            steps {
                echo '--- Compiling Java Programs ---'
                // Change the path to where your Java files are located
                dir('C:\\Jenkins-WorkSpace\\java-projects') {
                    bat '''
                        echo Compiling AddNumbers.java...
                        javac AddNumbers.java
                        if %ERRORLEVEL% NEQ 0 (
                            echo Compilation failed!
                            exit /b 1
                        )
                        echo Compilation successful!
                    '''
                }
            }
        }
        
        stage('Java Execution') {
            steps {
                echo '--- Executing Java Programs ---'
                dir('C:\\Jenkins-WorkSpace\\java-projects') {
                    bat '''
                        echo Running AddNumbers...
                        java AddNumbers
                    '''
                }
            }
        }
        
        stage('Python Setup') {
            steps {
                echo '--- Python Setup Stage ---'
                bat 'python --version'
            }
        }
        
        stage('Python Execution') {
            steps {
                echo '--- Running Python Program ---'
                bat '''
                    echo Running Python Calculator...
                    python C:\\Jenkins-WorkSpace\\python-projects\\calculator.py
                '''
            }
        }
        
        stage('Final Report') {
            steps {
                echo '=========================================='
                echo 'Pipeline Execution Completed Successfully'
                echo '=========================================='
                echo "Build Status: SUCCESS"
                echo "All stages executed without errors"
                echo '=========================================='
            }
        }
    }
    
    post {
        always {
            echo "Pipeline execution finished at: ${BUILD_TIMESTAMP}"
        }
        
        success {
            echo "✓ Build Successful!"
        }
        
        failure {
            echo "✗ Build Failed - Review console output above"
        }
        
        unstable {
            echo "⚠ Build is unstable"
        }
    }
}
