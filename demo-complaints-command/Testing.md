# Testing

## Building
```
mvn package
```
## Start service
```
mvn spring-boot:run
```
This starts the server side

To enter a complaint with curl
``` 
curl -H "Content-type: application/json" -d '{"company": "linux", "description": "Bash does not always work nicely"}' localhost:8080/complaints
```
This posts an event on the event message bus.
