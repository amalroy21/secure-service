## INTRODUCTION

`secure-service` is responsible for provide a secure channel for communication.

## DESCRIPTION
'secure-service' provides REST API's for secure communication of confidential information over the Internet and also provides an end point to securely store data on cloud. 


## PREREQUISITES
* `Java 1.8` and `Maven` are needed to build/run this project

## GETTING STARTED
1. open a terminal/console window
3. `cd path/to/secure-service`
4. `mvn clean package` to build the services
5.  mvn spring-boot:run

HomeController.java  method:getSubmitPage is where the encrypted browser data is decrypted.