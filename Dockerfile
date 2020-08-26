FROM openjdk:13

COPY build/libs/*.jar cast-api.jar
ENTRYPOINT ["java", "-jar", "cast-api.jar"]