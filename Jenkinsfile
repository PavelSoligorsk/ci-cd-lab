pipeline {
    agent any

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    environment {
        APP_NAME = 'java-maven-ci-demo'
        VERSION = '1.0.0'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'file:///E:/repos/java-maven-ci-demo.git']]
                ])
                echo "Project: ${env.APP_NAME} v${env.VERSION}"
            }
        }

        stage('Build') {
            steps {
                echo "Compiling Java sources..."
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                echo "Running unit tests..."
                bat 'mvn -B test jacoco:report'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    publishHTML([
                        reportDir: 'target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Code Coverage'
                    ])
                }
            }
        }

        stage('Package') {
            steps {
                echo "Packaging application..."
                bat 'mvn -B package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying application locally..."
                bat 'java -jar target/java-maven-ci-demo-1.0.0.jar'
            }
        }
    }

    post {
        always {
            echo "Pipeline execution completed"
        }
        success {
            echo "✅ Build successful!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}