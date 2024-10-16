#!/bin/bash

# 스크립트 파일위치
SCRIPT_DIR="$(dirname "$0")"
# 환경변수 불러오기
source "$SCRIPT_DIR/env.sh"

# 컨테이너가 실행 중인지 확인
if [[ "$(docker ps -q -f name=$CONTAINER_NAME 2> /dev/null)" != "" ]]; then
  echo "컨테이너를 중지합니다: $CONTAINER_NAME"
  docker stop $CONTAINER_NAME
else
  echo "실행 중인 컨테이너가 없습니다: $CONTAINER_NAME"
fi