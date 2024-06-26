terraform {
  backend "s3" {
    bucket  = "terraform-state-maverick"
    key     = "terraform/backend"
    region  = "ap-southeast-1"
    profile = "maverick"
  }
}
