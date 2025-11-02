pipeline {
    agent any
    
    environment {
        S3_BUCKET = 'your-react-app-bucket'
        DOCKER_IMAGE = 'spring-backend'
    }
    
    stages {
        stage('Build React') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        
        stage('Deploy to S3') {
            steps {
                dir('frontend/build') {
                    sh 'aws s3 sync . s3://${S3_BUCKET}/ --delete'
                }
            }
        }
        
        stage('Build Spring Boot') {
            steps {
                dir('backend') {
                    sh './mvnw clean package -DskipTests'
                }
            }
        }
        
        stage('Build Docker Image') {
            steps {
                dir('backend') {
                    sh 'docker build -t ${DOCKER_IMAGE}:latest .'
                }
            }
        }
        
        stage('Deploy Container') {
            steps {
                sh '''
                    docker stop ${DOCKER_IMAGE} || true
                    docker rm ${DOCKER_IMAGE} || true
                    docker run -d --name ${DOCKER_IMAGE} -p 8081:8080 ${DOCKER_IMAGE}:latest
                '''
            }
        }
    }
}