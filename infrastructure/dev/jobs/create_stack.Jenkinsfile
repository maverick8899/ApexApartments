pipeline {
    agent any
    stages {
        stage("Deploy Stack") {
            steps {
                sh '''
                ansible -i /usr/share/inventory.yml master1 -m ansible.builtin.shell -a "docker stack deploy --detach=false -c /data/docker-swarm.devcluster.yml apartment"
                '''
            }
        }
    }
}
