# 1️⃣ 실행용 JRE 23 기반 이미지 사용 (빌드 과정 생략)
FROM eclipse-temurin:21-jre
WORKDIR /app

# 2️⃣ 미리 빌드된 WAR 파일을 복사 (로컬에서 준비한 파일 사용)
COPY /build/libs/back-end-bf-0.0.1-SNAPSHOT.war app.war

# 3️⃣ 컨테이너 실행 시 애플리케이션 시작
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.war"]