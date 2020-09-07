FROM openjdk:7
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /app/
COPY src /app/src
WORKDIR /app
RUN mvn package
RUN javac src/Server.java
CMD ["java", "Server"]
