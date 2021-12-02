FROM openjdk:16-alpine
COPY ./target/project2-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch project2-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","project2-0.0.1-SNAPSHOT.jar"]
