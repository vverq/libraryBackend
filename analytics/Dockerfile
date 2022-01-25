FROM arm64v8/openjdk:11-jre

WORKDIR /opt/
COPY ./build/libs/analytics-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java" ,"-Xms512m", "-Xmx512m", "-jar", "/opt/app.jar"]
