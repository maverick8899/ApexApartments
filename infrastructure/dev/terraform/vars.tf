variable "REGION" {
  default = "ap-southeast-1"
}
variable "ROUTE53_ID" {
  default = "Z00293713CTSFD58RHY3S"
}

variable "ZONE1" {
  default = "ap-southeast-1a"
}

variable "ZONE2" {
  default = "ap-southeast-1b"
}

variable "ZONE3" {
  default = "ap-southeast-1c"
}

variable "AMIS" {
  type = map(any)
  default = {
    ap-southeast-1 = "ami-003c463c8207b4dfa" #/ Ubuntu Server 24.04 LTS (HVM), SSD Volume Type
    ap-southeast-2 = "ami-04c913012f8977029" #/ Amazon Linux 2023 AMI
  }
}

variable "USER" {
  default = {
    ap-southeast-1 = "ubuntu"
    ap-southeast-2 = "ec2-user"
  }
}

variable "PUB_KEY" {
  default = "terraform-key.pub"
}

variable "PRIV_KEY" {
  default = "terraform-key"
}

variable "MYIP" {
  default = "0.0.0.0/0"
} 