FROM openjdk:21
ADD target/basement-friends.jar basement-friends.jar
ENTRYPOINT ["java","-jar","/basement-friends.jar"]


#FROM openjdk:21
#ADD target/backend.jar backend.jar
#ENTRYPOINT ["java", "-jar"