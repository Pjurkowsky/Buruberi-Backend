FROM maven:3.6.3-openjdk-17 AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package
EXPOSE 8080
CMD ["java","-jar","/app/target/buruberiApp-0.0.1-SNAPSHOT.jar"]