FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/ACCOUNT-MICROSERVICE-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 7878
ENTRYPOINT ["java","-jar","app.jar"]
