pipeline {
    agent any

//     environment {
//         KUBE_CONFIG_PATH = '/path/to/your/kubeconfig'  // 配置Kubeconfig路径
//     }

    stages {
        stage('Checkout') {
            steps {
                // 从Git仓库拉取代码
                git url: 'https://github.com/axxd544/2024summer.git', branch: '21241011-test'
            }
        }

        stage('Build') {
            steps {
                // 使用Maven构建后端项目
                dir('music-server') {
                    sh 'mvn clean package'
                }
                // 使用npm构建前端项目
                dir('music-client') {
                    sh 'npm run build'
                }
                dir('music-manage') {
                    sh 'npm run build'
                }
//                 dir('database') {
//                     sh 'docker build -t your-database-image:latest .'
//                 }
            }
        }

        stage('Docker Build and Push') {
            steps {
                script {
                    // 使用Docker构建镜像
//                     sh "docker build -t your-music-client-image:latest -f music-client/Dockerfile ."
//                     sh "docker build -t your-music-manage-image:latest -f music-manage/Dockerfile ."
//                     sh "docker build -t your-music-server-image:latest -f music-server/Dockerfile ."
//                     sh "docker build -t your-database-image:latest -f database/Dockerfile ."
                    sh "docker-compose up --build"

                    // 如果需要将镜像推送到远程仓库，可以在这里添加推送步骤
                    // sh "docker push your-music-client-image:latest"
                    // sh "docker push your-music-manage-image:latest"
                    // sh "docker push your-music-server-image:latest"
                    // sh "docker push your-database-image:latest"
                }
            }
        }

        stage('Kubernetes Deploy') {
            steps {
                script {
                    // 部署到本地Kubernetes集群
                    sh "kubectl apply -f k8s/music-client-deployment.yaml"
                    sh "kubectl apply -f k8s/music-manage-deployment.yaml"
                    sh "kubectl apply -f k8s/music-server-deployment.yaml"
                    sh "kubectl apply -f k8s/database-deployment.yaml"
                    sh "kubectl apply -f k8s/music-client-service.yaml"
                    sh "kubectl apply -f k8s/music-manage-service.yaml"
                    sh "kubectl apply -f k8s/music-server-service.yaml"
                    sh "kubectl apply -f k8s/database-service.yaml"
                    sh "kubectl apply -f k8s/configmap.yaml"
                    sh "kubectl apply -f k8s/secret.yaml"
                }
            }
        }

        stage('Microservice Testing') {
            steps {
                script {
                    // 执行后端API测试，确保功能正确性
                    dir('music-server') {
                        sh "mvn test"
                    }
                    // 添加前端和数据库的测试步骤（如果需要）
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Checking issues...'
        }
    }
}
