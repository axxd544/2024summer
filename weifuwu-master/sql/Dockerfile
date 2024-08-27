# 使用 MySQL 官方镜像
FROM mysql:8.0

# 环境变量设置
ENV MYSQL_ROOT_PASSWORD=123456

# 复制 SQL 脚本到容器中
COPY consumer_db.sql /docker-entrypoint-initdb.d/1-consumer-service.sql
COPY content_db.sql /docker-entrypoint-initdb.d/2-content-service.sql
COPY social_db.sql /docker-entrypoint-initdb.d/3-social-service.sql

EXPOSE 3306

# 在启动时，MySQL 会自动执行这些 SQL 脚本来创建数据库和表
