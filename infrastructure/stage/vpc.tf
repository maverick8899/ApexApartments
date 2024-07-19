resource "aws_vpc" "docker-swarm" {
  cidr_block           = "10.0.0.0/16"
  instance_tenancy     = "default"
  enable_dns_support   = "true"
  enable_dns_hostnames = "true"
  tags = {
    Name = "docker-swarm"
  }
}
resource "aws_subnet" "docker-swarm-pub-1" {
  vpc_id                  = aws_vpc.docker-swarm.id
  cidr_block              = "10.0.1.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = var.ZONE1
  tags = {
    Name = "docker-swarm-pub-1"
  }
}
resource "aws_subnet" "docker-swarm-pub-2" {
  vpc_id                  = aws_vpc.docker-swarm.id
  cidr_block              = "10.0.2.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = var.ZONE2
  tags = {
    Name = "docker-swarm-pub-2"
  }
}
resource "aws_subnet" "docker-swarm-pub-3" {
  vpc_id                  = aws_vpc.docker-swarm.id
  cidr_block              = "10.0.3.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = var.ZONE3
  tags = {
    Name = "docker-swarm-pub-3"
  }
}
resource "aws_subnet" "docker-swarm-priv-1" {
  vpc_id                  = aws_vpc.docker-swarm.id
  cidr_block              = "10.0.4.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = var.ZONE1
  tags = {
    Name = "docker-swarm-priv-1"
  }
}
resource "aws_subnet" "docker-swarm-priv-2" {
  vpc_id                  = aws_vpc.docker-swarm.id
  cidr_block              = "10.0.5.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = var.ZONE2
  tags = {
    Name = "docker-swarm-priv-2"
  }
}
resource "aws_subnet" "docker-swarm-priv-3" {
  vpc_id                  = aws_vpc.docker-swarm.id
  cidr_block              = "10.0.6.0/24"
  map_public_ip_on_launch = "true"
  availability_zone       = var.ZONE3
  tags = {
    Name = "docker-swarm-priv-3"
  }
}
resource "aws_internet_gateway" "docker-swarm-IGW" {
  vpc_id = aws_vpc.docker-swarm.id
  tags = {
    Name = "docker-swarm-IGW"
  }
}
resource "aws_route_table" "docker-swarm-pub-RT" {
  vpc_id = aws_vpc.docker-swarm.id
  route {
    cidr_block = "0.0.0.0/0" #? Specifies the IP address ranges that are allowed to go through to arrive igw
    gateway_id = aws_internet_gateway.docker-swarm-IGW.id
  }
  tags = {
    Name = "docker-swarm-pub-RT"
  }
}
resource "aws_route_table_association" "docker-swarm-pub-1-a" {
  subnet_id      = aws_subnet.docker-swarm-pub-1.id
  route_table_id = aws_route_table.docker-swarm-pub-RT.id
}
resource "aws_route_table_association" "docker-swarm-pub-2-a" {
  subnet_id      = aws_subnet.docker-swarm-pub-2.id
  route_table_id = aws_route_table.docker-swarm-pub-RT.id
}
resource "aws_route_table_association" "docker-swarm-pub-3-a" {
  subnet_id      = aws_subnet.docker-swarm-pub-3.id
  route_table_id = aws_route_table.docker-swarm-pub-RT.id
}
