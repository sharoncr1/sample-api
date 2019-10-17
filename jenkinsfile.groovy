node {
        def mvnHome
        stage('Preparation') { // for display purposes
                // Get some code from a GitHub repository
                sh 'pwd'
                sh 'sudo rm -rf target -r'
                git 'https://bitbucket.org/sharoncr/sample-api.git'
                echo 'fininsed cloning'
                // Get the Maven tool.
                // ** NOTE: This 'M3' Maven tool must be configured
                // **       in the global configuration.
                //   mvnHome = tool 'M3'
        }
        stage('Tests'){
                withEnv(["MVN_HOME=/usr/share/maven"]) {
                        sh 'echo "Tests Enabled Status : $Tests"'
                        sh 'git stash'
                        sh 'git checkout $Branch'
                        sh 'echo "We are on "'
                        if( Tests == 'Yes' ){
                                if (isUnix()) {
                                        // sh 'git fetch -a'
                                        // sh 'git pull origin $Branch'
                                        sh '"$MVN_HOME/bin/mvn" clean test'
                                        // sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean install'
                                }
                        }
                }
        }
        stage('Build') {
                sh 'echo "We are on "'
                sh 'git branch'
                sh 'echo "starting to build"'
                sh 'mvn -Dmaven.test.skip=true package'

        }

        stage('Creating Docker Image') {
                sh 'sudo mvn install dockerfile:build'
        }
        stage('Deploy') {
                sh 'sudo docker run --rm --network host springio/thirdproject &'
        }

        stage('Results') {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'target/*.jar'
        }
}