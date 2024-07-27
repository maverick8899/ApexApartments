pipeline {
    agent any
    stages{
        stage("Remove Stack"){
            steps{
                sh "ansible -i /usr/share/inventory.yml master1  -m ansible.builtin.shell -a 'docker stack rm apartment'"
            }
        }
    }
}

