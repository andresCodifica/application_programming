FROM adoptopenjdk/openjdk11:ubi
COPY application_programming-0.0.1-SNAPSHOT.jar root.jar
ENTRYPOINT ["java","-Dserver.port=9001", "-jar", "root.jar"]
