# 1단계: 빌드 단계 (Gradle 기본 이미지 사용 후 OpenJDK 21 설치)
FROM gradle:8.5.0-jammy AS builder  

WORKDIR /app

# JDK 21 설치 및 캐시 레이어 최적화
RUN apt-get update && apt-get install -y openjdk-21-jdk && \
    rm -rf /var/lib/apt/lists/*

ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# Gradle Wrapper 및 설정 파일 복사 (실행 권한 부여)
COPY --chmod=755 gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./

# 의존성 다운로드 및 캐시 최적화 (실제 빌드 없이 의존성만 다운로드)
RUN ./gradlew dependencies --no-daemon || true

# 전체 프로젝트 복사
COPY . .

# 실행 권한 재설정
RUN chmod +x gradlew

# Gradle 빌드 수행 (테스트 제외)
RUN ./gradlew clean build -x test --no-daemon

# 2단계: 실행 단계 (최소한의 런타임 이미지 사용)
FROM eclipse-temurin:21.0.2_13-jdk-alpine AS runtime

WORKDIR /app

# 빌드된 JAR 파일 복사 (정확한 파일명을 지정)
COPY --from=builder /app/build/libs/*.jar /app/app.jar

# 애플리케이션 포트 노출
EXPOSE 8080

# 애플리케이션 실행
CMD ["sh", "-c", "java -jar /app/app.jar"]
