FROM eclipse-temurin:8-alpine
VOLUME /tmp
COPY target/newproject-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]