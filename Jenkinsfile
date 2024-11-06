pipeline {
    agent any

    environment {
        // Set the Maven tool if installed in Jenkins
        MAVEN_HOME = tool name: 'M3', type: 'ToolLocation'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/nhatnam99132/docker-compose-demo.git']]])
            }
        }

        stage('Build and Package') {
            steps {
                script {
                    // Run Maven clean and package to build the WAR file
                    sh "mvnw clean package -DskipTests"
                }
            }
        }
    }

    post {
        always {
            // Clean up or other final actions if needed
            echo 'Pipeline finished.'
        }
    }
}
