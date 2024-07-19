#!/bin/bash
mkdir /tools

#@ docker
sudo apt install -y apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list >/dev/null
sudo apt update -y
sudo apt install docker-ce -y
sudo systemctl start docker
sudo systemctl enable docker
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker -v
docker-compose -v

#@ harbor
apt update -y
apt install certbot -y
mkdir -p /tools/harbor && cd /tools/harbor
curl -s https://api.github.com/repos/goharbor/harbor/releases/latest | grep browser_download_url | cut -d '"' -f 4 | grep '.tgz$' | wget -i -
tar xvzf harbor-offline-installer*.tgz
cd harbor/
cp harbor.yml.tmpl harbor.yml

export DOMAIN="registry.devsops.online"
export EMAIL="trankimbang0809@gmail.com"
# sudo certbot certonly --standalone -d $DOMAIN --preferred-challenges http --agree-tos -m $EMAIL --keep-until-expiring
#@ HTTPS certbot
yes | sudo certbot certonly --standalone -d $DOMAIN --preferred-challenges http --agree-tos -m $EMAIL --keep-until-expiring


sed -i 's/hostname: reg.mydomain.com/hostname: registry.devsops.online/' harbor.yml
sed -i 's/certificate: \/your\/certificate\/path/certificate: \/etc\/letsencrypt\/live\/registry.devsops.online\/fullchain.pem/' harbor.yml
sed -i 's/private_key: \/your\/private\/key\/path/private_key: \/etc\/letsencrypt\/live\/registry.devsops.online\/privkey.pem/' harbor.yml

./prepare
./install.sh
