resource "aws_key_pair" "terraform-key" {
  key_name   = "terraform-key"
  public_key = file(var.PUB_KEY)
}
#@ EC2 instances for Manager nodes
resource "aws_instance" "manager" {
  ami               = var.AMIS[var.REGION] # Ubuntu AMI
  instance_type     = "t2.medium"
  subnet_id         = aws_subnet.docker-swarm-pub-1.id
  availability_zone = var.ZONE1
  # key_name                    = aws_key_pair.terraform-key.key_name
  key_name                    = "terraform-key"
  vpc_security_group_ids      = [aws_security_group.docker-swarm-sg.id]
  associate_public_ip_address = true #? true for pub-snt, false for pri-snt
  tags = {
    Name = "Docker-Swarm-Manager"
  }
  provisioner "remote-exec" {
    inline = [
      "sudo apt-get update -y",
      "sudo apt-get install -y docker.io",
      "sudo systemctl start docker",
      "sudo systemctl enable docker",
      # "sudo docker swarm init --advertise-addr ${self.public_ip}"
    ]
    connection {
      type        = "ssh"
      user        = var.USER[var.REGION]
      private_key = file("terraform-key")
      host        = self.public_ip
    }
  }
}

#@ EC2 instances for worker nodes
resource "aws_instance" "worker1" {
  ami               = var.AMIS[var.REGION] # Ubuntu AMI
  instance_type     = "t2.medium"
  subnet_id         = aws_subnet.docker-swarm-pub-2.id
  availability_zone = var.ZONE2
  # key_name                    = aws_key_pair.terraform-key.key_name
  key_name                    = "terraform-key"
  vpc_security_group_ids      = [aws_security_group.docker-swarm-sg.id]
  associate_public_ip_address = true

  tags = {
    Name = "Docker-Swarm-Worker-1"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt-get update -y",
      "sudo apt-get install -y docker.io",
      "sudo systemctl start docker",
      "sudo systemctl enable docker"
    ]

    connection {
      type        = "ssh"
      user        = var.USER[var.REGION]
      private_key = file("terraform-key")
      host        = self.public_ip
    }
  }
}

#@ EC2 instances for worker nodes
resource "aws_instance" "worker2" {
  ami               = var.AMIS[var.REGION] # Ubuntu AMI
  instance_type     = "t2.medium"
  subnet_id         = aws_subnet.docker-swarm-pub-3.id
  availability_zone = var.ZONE3
  # key_name                    = aws_key_pair.terraform-key.key_name
  key_name                    = "terraform-key"
  vpc_security_group_ids      = [aws_security_group.docker-swarm-sg.id]
  associate_public_ip_address = true

  tags = {
    Name = "Docker-Swarm-Worker-2"
  }

  provisioner "remote-exec" {
    inline = [
      "sudo apt-get update -y",
      "sudo apt-get install -y docker.io",
      "sudo systemctl start docker",
      "sudo systemctl enable docker"
    ]

    connection {
      type        = "ssh"
      user        = var.USER[var.REGION]
      private_key = file("terraform-key")
      host        = self.public_ip
    }
  }
}
resource "aws_ebs_volume" "vol_4" {
  availability_zone = var.ZONE1
  size              = 4
  tags = {
    Name = "extr-vol-4"
  }
}
resource "aws_volume_attachment" "atch_vol" {
  device_name = "/dev/xvdh"
  volume_id   = aws_ebs_volume.vol_4.id
  instance_id = aws_instance.manager.id
}
output "manager_public_ip" {
  value = aws_instance.manager.public_ip
}
output "worker1_public_ip" {
  value = aws_instance.worker1.public_ip
}
output "worker2_public_ip" {
  value = aws_instance.worker2.public_ip
}
 