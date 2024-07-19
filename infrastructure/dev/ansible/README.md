ansible-galaxy init roles/docker
ansible-galaxy init roles/swarm
ansible-playbook -i inventory.yml -u vagrant -k playbook.yml -> -k: Yêu cầu Ansible yêu cầu mật khẩu (pass=vagrant)
error -> Thêm fingerprint vào known_hosts: Bạn cần thêm fingerprint (dấu vân tay) của các máy chủ vào file known_hosts trên máy tính bạn đang chạy Ansible. Điều này cho phép SSH xác thực máy chủ mà không cần sử dụng mật khẩu hoặc yêu cầu người dùng xác nhận. (yes/no) vì sshChallenge
ssh-keyscan 192.168.140.10 192.168.140.11 192.168.140.12 >> ~/.ssh/known_hosts


# install AWS CLI
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
unzip awscliv2.zip
sudo ./aws/install