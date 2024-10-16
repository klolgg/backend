#!/bin/sh

aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 439127579128.dkr.ecr.ap-northeast-2.amazonaws.com
# ${aws_client_id}.dkr.ecr.${region}/${repo_name}:${image_tag}
docker pull 439127579128.dkr.ecr.ap-northeast-2.amazonaws.com/klol/was:latest