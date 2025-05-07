pipeline {
    agent any

    triggers {
        githubPush()
    }

    environment {
        WAR_FILE = 'target/HotelTransito.war'
        DEPLOY_PATH = '/usr/local/tomcat/webapps/HotelTransito.war'
        TOMCAT_CONTAINER = 'hoteltransito-tomcat-1' // Reemplaza si tu contenedor Tomcat tiene otro nombre
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Jhuly1215/HotelTransito.git',
                    branch: 'jhuls',
                    credentialsId: '7a4ed59d-b080-4c62-bf88-82a94e118c6a'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
                sh 'mvn test'
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo "🔄 Copiando .war al contenedor Tomcat..."
                    sh "docker cp ${WAR_FILE} ${TOMCAT_CONTAINER}:${DEPLOY_PATH}"
                    echo "♻️ Reiniciando Tomcat..."
                    sh "docker restart ${TOMCAT_CONTAINER}"
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build y despliegue exitoso tras push a GitHub.'
        }
        failure {
            echo '❌ El build o despliegue falló.'
        }
    }
}
