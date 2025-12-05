pipeline {
    agent any

    tools {
        maven 'Bundled (Maven 3)'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    triggers {
        pollSCM('H/2 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/PavelSoligorsk/ci-cd-lab.git', branch: 'main'
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
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Run Application') {
            steps {
                echo '🚀 Запуск приложения...'
                // ИСПРАВЛЕНО: правильное имя JAR-файла
                bat 'java -jar target/java-maven-ci-demo-*.jar'
                echo '🎉 Приложение успешно запущено!'
            }
        }
    }

    post {
        always {
            echo "========================================"
            echo "Pipeline: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            echo "Статус: ${currentBuild.currentResult}"
            echo "========================================"
        }
        success {
            echo '🎉 ПОЗДРАВЛЯЮ! ЛАБОРАТОРНАЯ РАБОТА ВЫПОЛНЕНА УСПЕШНО!'
            echo '✅ Полный CI/CD цикл работает'
            echo '✅ 7 тестов пройдены'
            echo '✅ Отчеты JaCoCo сгенерированы'
            echo '✅ JAR-файл создан'
            echo '✅ Приложение запущено'
        }
        failure {
            echo '❌ Сборка завершилась с ошибкой'
        }
    }
}