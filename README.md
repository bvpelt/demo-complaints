# Demo Complaints

This project is test for producing:
* docker images
* axon
* spring-cloud

## Docker
The version of the docker engine can be seen with
```
$ docker version
Client:
 Version:         1.12.6
 API version:     1.24
 Package version: docker-common-1.12.6-6.gitae7d637.fc25.x86_64
 Go version:      go1.7.4
 Git commit:      ae7d637/1.12.6
 Built:           Mon Jan 30 16:15:28 2017
 OS/Arch:         linux/amd64

Server:
 Version:         1.12.6
 API version:     1.24
 Package version: docker-common-1.12.6-6.gitae7d637.fc25.x86_64
 Go version:      go1.7.4
 Git commit:      ae7d637/1.12.6
 Built:           Mon Jan 30 16:15:28 2017
 OS/Arch:         linux/amd64
$
$ docker-compose version
 docker-compose version 1.9.0, build 2585387
 docker-py version: 1.10.6
 CPython version: 2.7.13
 OpenSSL version: OpenSSL 1.0.2k-fips  26 Jan 2017

```
Only certain versions of docker-compose work well with a specific docker version. See
[docker-compose versioning](https://docs.docker.com/compose/compose-file/compose-versioning/).
Above you see my versions.

## Maven
Building the system
``` 
$ mvn clean package docker:build
```
## Starting the system
``` 
$ docker-compose up &
```
