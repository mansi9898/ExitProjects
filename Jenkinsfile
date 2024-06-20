pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
            post {
                always {
                    // Record JUnit test results from the specified path
                    junit 'test-output/junitreports/**/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Your deploy steps here
            }
        }
        stage('Clean Up') {
            steps {
                echo 'Cleaning up the workspace...'
                // Clean the workspace using Maven
                bat 'mvn clean'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}