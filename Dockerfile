# 1단계: 빌드 단계
FROM gradle:8.3-jdk17 AS builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper 복사 (필수)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 실행 권한 추가
RUN chmod +x gradlew

# Gradle 의존성 미리 다운로드 (캐시 최적화)
RUN ./gradlew dependencies --no-daemon --project-cache-dir /app/.gradle

# 전체 프로젝트 복사 후 빌드
COPY . .
RUN ./gradlew build -x test --no-daemon

# 2단계: 실행 단계
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 실행 포트 설정
EXPOSE 8080

# 실행 명령
CMD ["java", "-jar", "app.jar"]
