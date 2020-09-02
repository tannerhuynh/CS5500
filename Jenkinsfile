def map = [
"Homework-1"  : "hw1",
"Homework-2"  : "hw2",
"Homework-3"  : "hw3",
"Homework-4"  : "hw4"
]

def error_msg = "Not Run"
def pom_exists = ""

pipeline {
  environment {
    jobBaseName = "${env.JOB_NAME}".split("-").first().split("/").last()
  }

  options {
    timeout(time: 10, unit: 'MINUTES') 
  } //option

  agent none
  stages {
    stage('Initialize') {
      agent {
        docker {
          image 'maven:3-alpine'
          args '-v m2_repos:/root/.m2'
        } //docker
      } // agent
      steps {
        script {
          map.each { entry ->
            stage (entry.key) {
             try {    
              echo "Building Homework"
              pom_exists = sh(script: "ls ${entry.key}/${entry.value}", returnStatus: true)
              sh"mvn -f ${entry.key}/${entry.value}/pom.xml compile"
              withSonarQubeEnv('SonarQube') {
                sh  "mvn -f ${entry.key}/${entry.value}/pom.xml clean install"
                sh  "mvn -f ${entry.key}/${entry.value}/pom.xml sonar:sonar -Dsonar.projectKey=${jobBaseName} -Dsonar.projectName=${jobBaseName}"
              }
			  sh "rm -f ${entry.key}/${entry.value}/target/sonar/report-task.txt"
              sh 'sleep 30'
              timeout(time: 10, unit: 'SECONDS') {
                retry(5) {
                  script {
                    def qg = waitForQualityGate()
                    if (qg.status != 'OK') {
                      error_msg =  "Pipeline aborted due to quality gate failure: ${qg.status}"
                      error "Pipeline aborted due to quality gate failure: ${qg.status}"
                    }
                  }
                }
                def temp = addEmbeddableBadgeConfiguration(id: "${entry.value}"+ "Badge", subject: "${entry.value}"+ "Build")
                temp.setStatus('HW Passing')
                temp.setColor('green')
              }
                      } catch (Exception e) {
                         def temp = addEmbeddableBadgeConfiguration(id: "${entry.value}"+ "Badge", subject: "${entry.value}"+ "Build")
                         if (pom_exists == 1) {
                           temp.setStatus("HW Not Run")
                           temp.setColor('yellow') 
                         } else if (error_msg.contains("Pipeline aborted due")) {
                           temp.setStatus(error_msg)
                           temp.setColor('red')
                         } else {
                           temp.setStatus("Build Errors")
                           temp.setColor('red')
                         }
                      }
                    }
                    }
                  }
                }
              }
            }
          }