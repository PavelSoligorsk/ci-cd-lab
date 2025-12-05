pipeline {
    agent any

    tools {
        maven 'Bundled (Maven 3)'
        jdk 'JDK-21'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/PavelSoligorsk/ci-cd-lab.git'
                branch: 'main'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile -B'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test jacoco:report -B'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    publishHTML([
                        reportDir: 'target/site/jacoco',
                        reportFiles: 'index.html',
                        reportName: 'JaCoCo Coverage Report',
                        alwaysLinkToLastBuild: true,
                        allowMissing: false,
                        keepAll: true
                    ])
                }
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests -B'
                archiveArtifacts 'target/*.jar'
            }
        }
    }

    post {
        success {
            echo '🎉 ЛАБОРАТОРНАЯ ВЫПОЛНЕНА! CI/CD работает успешно!'
        }
    }
}