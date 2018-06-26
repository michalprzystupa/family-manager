
FROM anapsix/alpine-java:8

WORKDIR /usr/src/myapp

ARG JAR_FILE

COPY target/${JAR_FILE} app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
