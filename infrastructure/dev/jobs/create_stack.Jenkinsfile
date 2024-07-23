pipeline{
    stages{
        stage(Deploy Stack){
            sh 'docker stack deploy --detach=false -c docker-swarm.devcluster.yml apartment'
        }
    }
}