
FROM mysql/mysql-server:8.0

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=familydb
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=pass

COPY schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306
