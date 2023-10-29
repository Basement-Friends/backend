FROM openjdk:21
ADD target/basement-friends.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


#FROM openjdk:21
#ADD target/backend.jar backend.jar
#ENTRYPOINT ["java", "-jar"