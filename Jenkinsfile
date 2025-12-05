pipeline {
    agent any

    tools {
        maven 'Bundled (Maven 3)'   // Проверьте точное имя в Jenkins
        jdk 'JDK-21'        // Проверьте точное имя в Jenkins
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
        stage('Checkout from GitHub') {
            steps {
                // ИЗМЕНИТЬ: ваш репозиторий на GitHub
                git url: 'https://github.com/PavelSoligorsk/ci-cd-lab.git', branch: 'main'
                echo '✅ Код загружен с GitHub'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn --version'
                bat 'mvn -B clean compile'
                echo '✅ Сборка завершена'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
                echo '✅ Тесты выполнены'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'

                    // ОСТОРОЖНО: плагин publishHTML нужно установить
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
                bat 'mvn -B package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                echo '✅ JAR-файл создан'
            }
        }

        stage('Deploy Simulation') {
            steps {
                echo '🚀 Симуляция деплоя...'
                // Запускаем приложение для демонстрации
                bat 'java -jar target/ci-cd-lab-*.jar'
                echo '🎉 CI/CD цикл завершен успешно!'
            }
        }
    }

    post {
        always {
            echo "======================================="
            echo "Pipeline: ${env.JOB_NAME} #${env.BUILD_NUMBER}"
            echo "Результат: ${currentBuild.result}"
            echo "======================================="
        }
        success {
            echo "✅ СБОРКА УСПЕШНА!"
            // Можно добавить уведомление на почту
        }
        failure {
            echo "❌ СБОРКА ПРОВАЛЕНА!"
        }
        unstable {
            echo "⚠️ СБОРКА НЕСТАБИЛЬНА (тесты упали)"
        }
    }
}