FROM java:8
ADD /target/BookManager-1.0-SNAPSHOT.jar BookManager-1.0-SNAPSHOT.jar
ENTRYPOINT java -jar BookManager-1.0-SNAPSHOT.jar BookManager