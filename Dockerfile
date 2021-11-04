FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/animesforum-0.0.1-SNAPSHOT.jar animes-forum.jar
ENTRYPOINT ["java","-jar","/animes-forum.jar"]
