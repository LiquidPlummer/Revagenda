# Revagenda
[![Build Status](http://ec2-54-234-94-174.compute-1.amazonaws.com:8080/buildStatus/icon?job=revagenda-demo)](http://ec2-50-17-99-29.compute-1.amazonaws.com:8080/job/revagenda-demo/)
 

 
# Jenkins Pipeline Setup Guide

## Project Structure

Your repository should be organized like this:
```
revagenda/
├── Jenkinsfile
├── Dockerfile
├── .gitignore
├── README.md
├── revagenda-client/          # React Frontend
│   ├── package.json
│   ├── package-lock.json
│   ├── vite.config.js
│   ├── index.html
│   ├── public/
│   └── src/
└── revagenda-server/          # Spring Backend
    ├── pom.xml
    ├── mvnw
    ├── mvnw.cmd
    ├── .mvn/
    └── src/
```

## Jenkinsfile

Create this in your repository root:

```groovy
pipeline {
    agent any
    
    environment {
        S3_BUCKET = 'your-react-app-bucket'
        SECRET_BUCKET = 'your-secrets-bucket'
        AWS_REGION = 'us-east-2'
        DOCKER_IMAGE = 'spring-backend'
        EXTERNAL_PORT = '8090'
        INTERNAL_PORT = '8080'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/your-username/your-repo'
            }
        }
        
        stage('Build React Frontend') {
            steps {
                dir('revagenda-client') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }
        
        stage('Deploy Frontend to S3') {
            steps {
                dir('revagenda-client/dist') {
                    sh 'aws s3 sync . s3://${S3_BUCKET}/ --delete --region ${AWS_REGION}'
                }
            }
        }

        stage('Fetch Secrets') {
            steps {
                sh 'aws s3 cp s3://${SECRET_BUCKET}/path/to/application.properties revagenda-server/src/main/resources/'
            }
        }
        
        stage('Build Spring Backend') {
            steps {
                dir('revagenda-server') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        
        stage('Cleanup Old Docker Resources') {
            steps {
                sh '''
                    docker stop ${DOCKER_IMAGE} || true
                    docker rm ${DOCKER_IMAGE} || true
                    docker system prune -a -f
                '''
            }
        }
        
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t ${DOCKER_IMAGE}:${BUILD_NUMBER} .'
                sh 'docker tag ${DOCKER_IMAGE}:${BUILD_NUMBER} ${DOCKER_IMAGE}:latest'
            }
        }
        
        stage('Deploy Backend Container') {
            steps {
                sh 'docker run -d --name ${DOCKER_IMAGE} -p ${EXTERNAL_PORT}:${INTERNAL_PORT} ${DOCKER_IMAGE}:latest'
            }
        }
    }
    
    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
```

## Dockerfile

Create this in your repository root:

```dockerfile
FROM openjdk:17-slim
WORKDIR /app
COPY backend/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## Create S3 Bucket

On your Jenkins server:
```bash
aws s3 mb s3://your-react-app-bucket
aws s3 website s3://your-react-app-bucket --index-document index.html --error-document index.html
```

Make bucket public for static website hosting:
```bash
aws s3api put-bucket-policy --bucket your-react-app-bucket --policy '{
  "Version": "2012-10-17",
  "Statement": [{
    "Sid": "PublicReadGetObject",
    "Effect": "Allow",
    "Principal": "*",
    "Action": "s3:GetObject",
    "Resource": "arn:aws:s3:::your-react-app-bucket/*"
  }]
}'
```

## Create Jenkins Pipeline

### 1. Create New Pipeline Job
1. Go to Jenkins dashboard
2. Click **New Item**
3. Enter job name (e.g., "react-spring-deploy")
4. Select **Pipeline**
5. Click **OK**

### 2. Configure Pipeline
1. Scroll to **Build Triggers**
2. Check **GitHub hook trigger for GITScm polling**

3. Scroll to **Pipeline** section
4. Select **Pipeline script from SCM**
5. Choose **Git** as SCM
6. Enter your repository URL
7. Set branch to `*/main`
8. Set **Script Path** to `Jenkinsfile`
9. Click **Save**

### 3. Set Up GitHub Webhook
1. Go to your GitHub repository → **Settings** → **Webhooks**
2. Click **Add webhook**
3. Set Payload URL: `http://your-ec2-ip:8080/github-webhook/`
4. Content type: **application/json**
5. Events: **Just the push event**
6. Click **Add webhook**

### 4. Update Environment Variables
Edit the Jenkinsfile in your repo and update:
- `S3_BUCKET`: Your actual S3 bucket name
- `AWS_REGION`: Your AWS region
- Git repository URL in the Checkout stage

### 5. First Build
1. Click **Build Now** to run the pipeline manually
2. Watch the stages execute
3. Check console output for any errors

### 6. Add Build Status Badge to README
1. Go to your pipeline job in Jenkins
2. Click **Embeddable Build Status** in the left sidebar
3. Copy the markdown code
4. Paste into your GitHub README.md

Or manually add:
```markdown
![Build Status](http://your-ec2-ip:8080/buildStatus/icon?job=your-job-name)
```
