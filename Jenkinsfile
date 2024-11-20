
pipeline{

    agent any

    environment{
        BRANCH_NAME = 'main'
        DOCKER_IMAGE_NAME = "leavemanagement"
        MYSQL_CONTAINER = "mysqldb"
        MYSQL_HOST = "localhost"
        SONAR_TOKEN = credentials('sonarCreds')
        SONAR_HOME = tool "SonarScanner"
    }

    tools {
            maven 'Maven 3.x'  // Referencing the Maven tool configured in Jenkins
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

        // stage 3 : Sonarqube analysis
        stage('Sonarqube analysis'){
            steps{
              script{
                withSonarQubeEnv("SonarServer"){
                    // Debugging the SONAR_HOME environment variable and SonarQube Scanner location
                    echo "SONAR_HOME is: $SONAR_HOME"
                    sh "ls -l $SONAR_HOME/bin/sonar-scanner"  // Ensure sonar-scanner exists in the bin folder
                    sh "$SONAR_HOME/bin/sonar-scanner -Dsonar.projectName=leavemanagement -Dsonar.projectKey=leavemanagement -Dsonar.host.url=http://192.168.0.102:9000 -Dsonar.login=${env.SONAR_TOKEN} -Dsonar.exclusions=**/*.sock,**/*.ibd,**/mysql-data/**/*.sock,**/mysql-data/**/*.ibd"
                }
              }
            }
        }

        // stage 3.1 : Sonarqube quality gate
        stage('Sonarqube Quality Gates'){
            steps{
                timeout(time: 1,unit: "MINUTES"){
                    waitForQualityGate abortPipeline: false
                }
            }
        }

        // stage 4 : Build the docker image using Dockerfile
        stage('Build Docker Image'){
            steps{
                script{
                    // Build the Docker image named 'leavemanagement'
                    sh 'docker build -t $DOCKER_IMAGE_NAME .'
                }
            }
        }

        // Stage 5: Run Docker Compose to start MySQL and Spring Boot containers
        stage('Docker Compose Up') {
            steps {
                script {
                    // Start the Docker containers using docker-compose.yml
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }

    }

}