FROM openjdk:latest
LABEL maintainer="KafkaMovieDB"

COPY API-Producer/target/API-Producer-0.0.1-SNAPSHOT.jar API-Producer.jar
COPY Console-FileWrite-Consumer/target/Console-FileWrite-Consumer-0.0.1-SNAPSHOT.jar Console-FileWrite-Consumer.jar
COPY Database-Consumer-producer/target/Database-Consumer-producer-0.0.1-SNAPSHOT.jar Database-Consumer-producer.jar
COPY MovieDBApplication-Consumer/target/MovieDBApplication-Consumer-0.0.1-SNAPSHOT.jar MovieDBApplication-Consumer.jar

CMD ["java", "-jar", "API-Producer.jar"]
CMD ["java", "-jar", "Console-FileWrite-Consumer.jar"]
CMD ["java", "-jar", "Database-Consumer-producer.jar"]
CMD ["java", "-jar", "MovieDBApplication-Consumer.jar"]
