# 1단계: 빌드 단계 (Gradle 기본 이미지 사용 후 OpenJDK 21 설치)
FROM gradle:8.5.0-jammy AS builder  

# 작업 디렉토리 설정
WORKDIR /app

# JDK 21 설치
RUN apt-get update && apt-get install -y openjdk-21-jdk && \
    rm -rf /var/lib/apt/lists/*

# JAVA_HOME 설정
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Gradle Wrapper 복사
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 실행 권한 추가
RUN chmod +x gradlew

# Gradle 버전 확인 (디버깅용)
RUN ./gradlew --version

# Gradle 의존성 다운로드 (기본 경로 사용)
RUN ./gradlew dependencies --no-daemon

# 전체 프로젝트 복사 후 빌드
COPY . .
RUN ./gradlew build -x test --no-daemon

# 2단계: 실행 단계
FROM eclipse-temurin:21-jdk-alpine  # 실행 단계에서도 Java 21 사용

WORKDIR /app

# 빌드된 JAR 파일 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 실행 포트 설정
EXPOSE 8080

# 실행 명령
CMD ["java", "-jar", "app.jar"]
