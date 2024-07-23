pipeline {
    agent any
    environment { 
        ANSIBLE_PATH = "/data/ansible"
        INVENTORY_PATH = "/data/ansible/inventory.yml"
    }

    stages {
        stage("Check Mount NFS") {
            steps {
                ansiblePlaybook disableHostKeyChecking: true, installation: 'ansible', inventory: "${INVENTORY_PATH}", playbook: "${ANSIBLE_PATH}/check_nfs.yml", vaultTmpPath: '' 
            }
        }
        stage("Check Status Docker Swarm") {
            steps {
                ansiblePlaybook disableHostKeyChecking: true, installation: 'ansible', inventory: "${INVENTORY_PATH}", playbook: "${ANSIBLE_PATH}/check_swarm.yml", vaultTmpPath: '' 
            }
        }
    }
}