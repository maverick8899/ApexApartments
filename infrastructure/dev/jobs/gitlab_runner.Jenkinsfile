pipeline {
    agent any 
    environment { 
        REMOTE_USER = "ubuntu"
        ANSIBLE_PATH = "/data/ansible"
        INVENTORY_PATH = "/data/ansible/inventory.yml"
    }
    stages { 
        stage("Setup GitLab Runner") {
            steps {
                input 'Do you approve the gitlab-runner setup?' 
                ansiblePlaybook(
                    disableHostKeyChecking: true,
                    installation: 'ansible',
                    inventory: "${INVENTORY_PATH}",
                    playbook: "${ANSIBLE_PATH}/gitlab_runner.yml",
                    vaultTmpPath: '',
                    extraVars: [
                        gitlab_project_token: "${GITLAB_PROJECT_TOKEN}",
                        gitlab_url: "${GITLAB_URL}"
                    ]
                )         

            }
        }  
    }
//*END PIPILINE */
}
