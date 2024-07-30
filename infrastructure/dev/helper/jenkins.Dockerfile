FROM jenkins/jenkins:lts

#? Install dependencies
WORKDIR /tool
USER root

COPY ./ansible_setup.sh .
RUN chmod +x /tool/ansible_setup.sh && /tool/ansible_setup.sh

COPY ./terraform_setup.sh .
RUN chmod +x /tool/terraform_setup.sh && /tool/terraform_setup.sh

COPY ./awscli_setup.sh .
RUN chmod +x /tool/awscli_setup.sh && /tool/awscli_setup.sh

RUN apt-get update && apt-get install -y docker.io

RUN apt-get install -y sshpass

USER jenkins
WORKDIR /