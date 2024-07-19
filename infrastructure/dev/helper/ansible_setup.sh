#!/bin/bash
apt-add-repository ppa:ansible/ansible -y
apt update -y
apt install -y ansible
ansible --version

#* for container
apt-get update -y && apt-get install software-properties-common -y && apt-get install ansible -y