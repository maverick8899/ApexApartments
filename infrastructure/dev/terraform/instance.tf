resource "aws_key_pair" "terraform-key" {
  key_name   = "terraform-key"
  public_key = file(var.PUB_KEY)
}

resource "aws_instance" "registry" {
  ami               = var.AMIS[var.REGION] # Ubuntu AMI
  instance_type     = "t2.micro"
  subnet_id         = aws_subnet.docker-swarm-pub-1.id
  availability_zone = var.ZONE1
  # key_name                    = aws_key_pair.terraform-key.key_name
  key_name                    = "terraform-key"
  vpc_security_group_ids      = [aws_security_group.docker-swarm-sg.id]
  associate_public_ip_address = true #? true for pub-snt, false for pri-snt
  tags = {
    Name = "registry"
  }
  provisioner "local-exec" {
    command = "echo ${aws_instance.registry.public_ip}  > pub_ip.txt "
  }
  root_block_device {
    volume_size = 24
    volume_type = "gp3"
  }
  # #* user data
  # provisioner "file" {
  #   source      = "harbor_setup.sh"
  #   destination = "/tmp/harbor_setup.sh"
  # }
  # provisioner "remote-exec" {
  #   inline = [
  #     "ls -la /tmp",
  #     "cat /tmp/harbor_setup.sh",
  #     "chmod +x /tmp/harbor_setup.sh",
  #     "sudo apt install -y dos2unix",
  #     "dos2unix /tmp/harbor_setup.sh",
  #     "sudo /tmp/harbor_setup.sh"
  #   ]
  # }
  # connection {
  #   type        = "ssh"
  #   user        = var.USER[var.REGION]
  #   private_key = file("terraform-key")
  #   host        = self.public_ip
  # }
  #* user data __END
}

#* suppliment EBS
resource "aws_ebs_volume" "vol_4" {
  availability_zone = var.ZONE1
  size              = 15
  tags = {
    Name = "extr-vol-4"
  }
}
resource "aws_volume_attachment" "atch_vol" {
  device_name = "/dev/xvdh"
  volume_id   = aws_ebs_volume.vol_4.id
  instance_id = aws_instance.registry.id
}
#* suppliment EBS __END

output "registry_public_ip" {
  value = aws_instance.registry.public_ip
}

