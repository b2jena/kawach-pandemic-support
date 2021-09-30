FROM openjdk:11

EXPOSE 9901

ADD ./target/resource-request-service-1.0.0.jar resource-request-service-1.0.0.jar

ENTRYPOINT ["java","-jar", "/resource-request-service-1.0.0.jar"]