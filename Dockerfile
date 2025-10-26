FROM eclipse-temurin:25-jdk-alpine

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw package -DskipTests

EXPOSE 8081

CMD ["java", "-jar", "target/customer-service-0.0.1-SNAPSHOT.jar"]
