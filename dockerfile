FROM openjdk:17-oracle
COPY target/*.jar ms-gateway.jar
ENTRYPOINT ["java", "-jar", "/ms-gateway.jar"]
EXPOSE 8072