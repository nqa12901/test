# -------- Stage 1: Build ứng dụng bằng Maven --------
FROM maven:3.8-openjdk-8 AS builder

WORKDIR /app

# Chép file pom.xml và tải dependencies (cache layer)
COPY pom.xml .
RUN mvn dependency:go-offline

# Chép toàn bộ source và build jar
COPY src ./src
RUN mvn clean package -DskipTests

# -------- Stage 2: Chạy ứng dụng bằng JRE nhẹ --------
FROM openjdk:8-jre-alpine

# Tạo folder chứa jar (tùy ý)
WORKDIR /app

# Copy file jar từ stage builder sang
COPY --from=builder /app/target/*.jar app.jar

# Mở port dựa trên biến môi trường PORT hoặc mặc định 8080
EXPOSE ${PORT:-8080}

# Chạy ứng dụng với cấu hình linh hoạt
ENTRYPOINT ["java", "-Dserver.port=${PORT:-8080}", "-jar", "app.jar"]
