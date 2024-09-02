#!/bin/bash

# 스크립트 파일위치
SCRIPT_DIR="$(dirname "$0")"
# mariadb volume
DB_VOLUME_DIR="$(dirname "$(realpath "$0")")/db"
# 환경변수 불러오기
source "$SCRIPT_DIR/env.sh"

# 도커 이미지 빌드
echo "이미지 빌드: $IMAGE_NAME"
docker build -t $IMAGE_NAME .

# 이전에 실행 중이던 컨테이너가 있다면 중지 및 제거
if [[ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]]; then
    echo "기존 컨테이너를 중지하고 제거합니다: $CONTAINER_NAME"
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
fi

# Docker 컨테이너 실행 (데몬 모드) todo: volume을 docker/db/ 경로에 설정하고 .gitignore에 volume 추가
echo "새로운 컨테이너를 실행합니다: $CONTAINER_NAME"
docker run -d --name $CONTAINER_NAME -p 3306:3306 -v "$DB_VOLUME_DIR":/var/lib/mysql $IMAGE_NAME