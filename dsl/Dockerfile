FROM java:8

WORKDIR /dsl

ADD web/target/scala-2.11/web-assembly-0.1-SNAPSHOT.jar /dsl/web-assembly-0.1-SNAPSHOT.jar

EXPOSE 9999

CMD []
ENTRYPOINT ["java", "-jar","web-assembly-0.1-SNAPSHOT.jar"]
