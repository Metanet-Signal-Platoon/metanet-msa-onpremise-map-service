# 1단계: 빌드 단계 (Gradle 기본 이미지 사용 후 OpenJDK 21 설치)
FROM gradle:8.5.0-jammy AS builder  

WORKDIR /app

# JDK 21 설치 및 캐시 레이어 최적화
RUN apt-get update && apt-get install -y openjdk-21-jdk && \
    rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Gradle Wrapper 및 설정 파일 복사 (실행 권한 부여)
# Docker 20.10 이상인 경우 COPY --chmod 사용
COPY --chmod=755 gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 의존성 캐시 레이어 생성 (빌드에 자주 쓰이는 의존성 미리 다운로드)
RUN ./gradlew dependencies --no-daemon

# 전체 프로젝트 복사
# (.dockerignore로 불필요한 파일 제외)
COPY . .

# (만약 위 COPY로 인해 gradlew의 실행 권한이 손실된다면) 재설정
RUN chmod +x gradlew

# 실제 빌드 (테스트 제외)
RUN ./gradlew build -x test --no-daemon

# 2단계: 실행 단계 (최소한의 런타임 이미지 사용)
FROM eclipse-temurin:21.0.2_13-jdk-alpine AS runtime

WORKDIR /app

# 빌드된 JAR 파일 복사 (하나의 실행 파일이 생성된다고 가정)
COPY --from=builder /app/build/libs/*.jar app.jar

# 애플리케이션 포트 노출
EXPOSE 8080

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]
