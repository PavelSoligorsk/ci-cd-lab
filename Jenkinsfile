pipeline {
    agent any

    tools {
        maven 'M3'
        jdk 'JDK17'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timestamps()
    }

    triggers {
        pollSCM('H/2 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'D:/repos/java-maven-ci-demo.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
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
                bat 'mvn -B package -DskipTests'
                archiveArtifacts 'target/*.jar'
            }
        }
    }

    post {
        always {
            echo "Pipeline completed for ${env.JOB_NAME} #${env.BUILD_NUMBER}"
        }
        success {
            echo "✅ Build successful!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}