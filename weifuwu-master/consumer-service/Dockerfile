# 使用 OpenJDK 20 作为基础镜像
FROM openjdk:21

WORKDIR /app

# 复制打包好的JAR文件
COPY target/*.jar /app/consumer-service.jar

# 暴露Spring Boot应用的端口
EXPOSE 8081

# 运行Spring Boot应用
ENTRYPOINT ["java", "-jar", "consumer-service.jar"]
