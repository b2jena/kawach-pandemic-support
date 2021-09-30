FROM openjdk:11

EXPOSE 9099

ADD ./target/user-authentication-service-0.0.1-SNAPSHOT.jar user-authentication-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "/user-authentication-service-0.0.1-SNAPSHOT.jar"]