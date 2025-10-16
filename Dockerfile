FROM openjdk:latest
COPY ./target/SET08103--Friday-4PM-Group-8-0.1.0.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SET08103--Friday-4PM-Group-8-0.1.0.1-jar-with-dependencies.jar"]