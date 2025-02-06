FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/atm-report-service-0.0.1-SNAPSHOT.jar app.jar

COPY src/data /app/data

ENV ATM_DATA_FOLDER="/app/data"

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
