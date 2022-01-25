#FROM arm64v8/openjdk:11-jre
FROM openjdk:11

WORKDIR /opt/
COPY ./build/libs/library-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java" ,"-Xms512m", "-Xmx512m", "-jar", "/opt/app.jar"]
