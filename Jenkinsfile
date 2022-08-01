pipeline {
    agent any
    stages {
        stage('Compile'){
            steps{
            withMaven(maven: 'MAVEN_HOME'){
                sh 'mvn clean compile'
                }
            }
        }
        stage('Test'){
            steps{
                withMaven(maven:'MAVEN_HOME'){
                sh 'mvn test'
                }
            }
        }
        stage('Deploy'){
            steps{
                withMaven(maven:'MAVEN_HOME'){
                sh 'mvn deploy'
                }
            }
        }

    }

}