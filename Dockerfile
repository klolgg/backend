# github action에서 빌드되는 앱 이미지
# Amazon ECR에 push된다.
FROM openjdk:17-jdk-slim
WORKDIR /app

# JAR 파일을 복사할 위치
COPY app.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-Dspring.profiles.active=prd", "-jar", "app.jar"]
