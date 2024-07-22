FROM openjdk:20-jdk
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]