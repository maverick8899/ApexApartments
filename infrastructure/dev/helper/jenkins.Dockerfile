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

USER jenkins
WORKDIR /