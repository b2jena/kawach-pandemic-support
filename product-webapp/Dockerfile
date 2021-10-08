FROM node:latest as node

WORKDIR /usr/src/app

RUN npm install -g @angular/cli

COPY . /usr/src/app/

CMD ng serve --host 0.0.0.0 --port 4200
# FROM openjdk:11-jre-slim
# ADD target/product-webapp-0.0.1-SNAPSHOT.jar product-webapp-0.0.1-SNAPSHOT.jar
# EXPOSE 8099
# ENTRYPOINT ["java","-jar","product-webapp-0.0.1-SNAPSHOT.jar"]

