#!/bin/sh

docker stop was-container
docker rm -f was-container
docker rmi 439127579128.dkr.ecr.ap-northeast-2.amazonaws.com/klol/was:latest

Stop one or more running containers
Create a tag TARGET_IMAGE that refers to SOURCE_IMAGE