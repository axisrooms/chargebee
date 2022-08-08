FROM openjdk:8-jre-slim
COPY build/libs/Chargebee-0.1.0.jar /
WORKDIR /
CMD ["java", "-jar", "Chargebee-0.1.0.jar"]
