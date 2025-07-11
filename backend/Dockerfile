### ---------- Build Stage ----------
FROM maven:3.9.5-eclipse-temurin-21-alpine AS builder
WORKDIR /app

# Bağımlılıkları önbelleğe al (opsiyonel cache mount)
COPY backend/pom.xml backend/.mvn/ ./
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -B dependency:go-offline

# Kaynak kodu kopyala ve derle
COPY backend/src ./src
RUN --mount=type=cache,target=/root/.m2 \
    mvn -q -B package -DskipTests

### ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:+ExitOnOutOfMemoryError"
EXPOSE 8070
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
