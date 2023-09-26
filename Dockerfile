FROM openjdk:17-jdk-alpine
MAINTAINER ferdi-homelab.casa 



COPY build/libs/plant-grower-springboot-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar" ]





