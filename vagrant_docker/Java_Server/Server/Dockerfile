FROM openjdk:7
COPY . /app/ 
WORKDIR /app
RUN javac Server.java
CMD ["java", "Server"]
EXPOSE 8080