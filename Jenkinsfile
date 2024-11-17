
pipeline{

    agent any

    environment{
        BRANCH_NAME = 'main'
        DOCKER_IMAGE_NAME = "leavemanagement"
        MYSQL_CONTAINER = "mysqldb"
        MYSQL_HOST = "localhost"
    }

    stages{

        // stage 1 : checkout code from git
        stage('checkout'){
            steps{
                git branch: "${env.BRANCH_NAME}", url: "https://github.com/Baljeet1699/LeaveManagement.git"
            }
        }

        // stage 2 : Build the springboot application using maven
        stage('Build with Maven'){
            steps{
                script{

                    // Run maven to clean, install packages and package the application
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        // stage 3 : Build the docker image using Dockerfile
        stage('Build Docker Image'){
            steps{
                script{
                    // Build the Docker image named 'leavemanagement'
                    sh 'docker build -t $DOCKER_IMAGE_NAME .'
                }
            }
        }

        // Stage 4: Run Docker Compose to start MySQL and Spring Boot containers
        stage('Docker Compose Up') {
            steps {
                script {
                    // Start the Docker containers using docker-compose.yml
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }

         // Stage 5: Test the application
        stage('Test Application') {
            steps {
                script {
                    // Test if the Spring Boot application is accessible
                    sh 'curl -f http://localhost:8082/actuator/health || exit 1'
                }
            }
        }

        // Stage 6: Clean up Docker containers and images after the test
        stage('Cleanup') {
            steps {
                script {
                    // Stop and remove the containers started by docker-compose
                    sh 'docker-compose down'
                }
            }
        }
    }

    // Post-actions after the pipeline finishes
    post {
        always {
            // Clean up Docker image to free up space
            sh 'docker rmi $DOCKER_IMAGE_NAME || true'
        }
    }

}