FROM openjdk:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


#FROM openjdk:21
#ADD target/backend.jar backend.jar
#ENTRYPOINT ["java", "-jar"