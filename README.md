# Axon example

CQRS and Eventsourcing are seperate things.

Axon highly uses spring boot by annotations, autoconfiguration. Defaults can be changed.

Available on [github](https://github.com/abuijze/bootiful-axon) 

## Source [youtube](https://www.youtube.com/watch?v=Jp-rW-XOYzA)

* Use [start.spring.io](http://start.spring.io) to generate the start project description
* See [Testing](Testing.md) for a description how to do some testing

## Reading
[The CQRS journey by Microsoft](https://msdn.microsoft.com/en-us/library/jj554200.aspx)

## Prerequisits
* [RabbitMQ](http://www.rabbitmq.com/) must be installed (and started for some testing).
See [Installing RabbitMQ](RabbitMQ.md)

## Dockerize
See [working example](https://www.dontpanicblog.co.uk/2016/07/08/building-tagging-and-pushing-docker-images-with-maven/)
for building the docker image. The example refers to [github](https://github.com/hotblac/spanners/blob/master/pom.xml) for code.

See [docker and java](https://hub.docker.com/_/java/) documentation

In order to build the docker image:
* the docker daemon must be started
* a rabbitmq instance must be running, the maven test will fail otherwise

Building is executed by
``` 
$ mvn clean package docker:build
```
After the maven build / test logging you see
``` 
[INFO] 
[INFO] --- maven-jar-plugin:2.6:jar (default-jar) @ demo-complaints ---
[INFO] Building jar: /home/bvpelt/Develop/demo-complaints/target/demo-complaints-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.1.RELEASE:repackage (default) @ demo-complaints ---
[INFO] 
[INFO] --- docker-maven-plugin:0.4.13:build (default-cli) @ demo-complaints ---
[INFO] Copying /home/bvpelt/Develop/demo-complaints/target/demo-complaints-0.0.1-SNAPSHOT.jar -> /home/bvpelt/Develop/demo-complaints/target/docker/demo-complaints-0.0.1-SNAPSHOT.jar
[INFO] Building image dockerpinguin/demo-complaints
Step 1 : FROM java:openjdk-8-jdk-alpine
 ---> e40ba8c51bb2
Step 2 : ADD /demo-complaints-0.0.1-SNAPSHOT.jar //
 ---> d270bf0ee688
Removing intermediate container e5417602e4b4
Step 3 : EXPOSE 8080
 ---> Running in fd745b90df29
 ---> b40155be8a10
Removing intermediate container fd745b90df29
Step 4 : ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /demo-complaints-0.0.1-SNAPSHOT.jar
 ---> Running in a5cf01ed0933
 ---> 42dfb130e059
Removing intermediate container a5cf01ed0933
Successfully built 42dfb130e059
[INFO] Built dockerpinguin/demo-complaints
[INFO] Tagging dockerpinguin/demo-complaints with 0.0.1-SNAPSHOT
[INFO] Tagging dockerpinguin/demo-complaints with latest
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 33.320 s
[INFO] Finished at: 2017-02-08T16:12:13+01:00
[INFO] Final Memory: 55M/397M
[INFO] ------------------------------------------------------------------------
```