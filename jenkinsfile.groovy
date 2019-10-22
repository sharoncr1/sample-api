import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

node {
        def endpoint = "http://localhost:8080/health"
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

        stage('Verify Deployment'){
                def get = new URL(endpoint).openConnection();
                println(get.getContent());
                println(get.toString());
                println(get);
                if(get.getResponseCode().equals("200")) {
                        println(get.getInputStream().getText());
                }
        }

        stage('Results') {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'target/*.jar'
        }
}