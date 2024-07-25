curl -X POST -u "elastic:${ELASTIC_PASSWORD}" -H "Content-Type: application/json" http://localhost:9200/_security/user/kibana_system/_password -d "{ \"password\": \"${KIBANA_PASSWORD}\" }"

docker-swarm
#!/bin/bash
docker run -d -p 8888:8000 -p 9999:9000 --name=portainer --restart=always -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer-ce:alpine-sts  
sudo docker swarm init --advertise-addr `manager_ip`
sudo docker network create --driver overlay traefik
sudo docker network create --driver overlay webapp
sudo docker stack deploy -c docker-compose.yaml
docker stack deploy --detach=false -c docker-swarm.devcluster.yml apartment
-jenkins
local: scp inventory.yml vagrant@jenkins.local:~/
sudo bash -c 'echo "192.168.30.31 gitlab.local" >> /etc/hosts'

-nfs
sudo bash -c 'echo "192.168.30.31 gitlab.local" >> /etc/hosts'
cd /data/nfs_export
sudo git config --global http.sslVerify false
sudo git clone https://root:Root2003%40@gitlab.local/apartment/apartment_deploy
mv apartment_deploy/.??\* .
rmdir apartment_deploy
