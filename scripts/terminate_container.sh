#!/bin/sh

# 컨테이너 이름
CONTAINER_NAME="was-container"
# 이미지 이름
IMAGE_NAME="439127579128.dkr.ecr.ap-northeast-2.amazonaws.com/klol/was:latest"

if [ $(docker ps -a -q -f name=${CONTAINER_NAME}) ]; then
    echo "remove container(${CONTAINER_NAME})"
    # 컨테이너 삭제
    docker rm -f ${CONTAINER_NAME}
else
    echo "Does not exist ${CONTAINER_NAME}"
fi

if [[ "$(docker images -q ${IMAGE_NAME})" != "" ]]; then
    echo "remove docker image(${IMAGE_NAME})"
    docker rmi -f ${IMAGE_NAME}
else
    echo "Does not exist ${IMAGE_NAME}"
fi
