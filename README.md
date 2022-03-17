# CGI ECTP Java Track Microservice Project

Current working project for CGI GLK Java Track. 

## How to run the project (As of March 15th, 2022)
- Start by opening each individual microservice and the common library in IntelliJ
- For the common library, under the Gradle panel, do a "publishToMavenLocal" so you have the most up to date version of the library in your local (I have my version set to 1.0 instead of 0.1 like most people, so it shouldn't interfere with the version you made if you were following along with Jeff). 
- Make sure you have pgAdmin open as well as Docker Desktop running. 
- In the orch service, open a terminal and type ```docker-compose up``` and the project should start deploying to docker
- This should have the microservices up running!
# CGI ECTP Java Track Microservice Project

Current working project for CGI GLK Java Track. 

## How to run the project (As of March 15th, 2022)
- Start by opening each individual microservice and the common library in IntelliJ
- For the common library, under the Gradle panel, do a "publishToMavenLocal" so you have the most up to date version of the library in your local (I have my version set to 1.0 instead of 0.1 like most people, so it shouldn't interfere with the version you made if you were following along with Jeff). 
- Make sure you have pgAdmin open as well as Docker Desktop running. 
- In the orch service, open a terminal and type ```docker-compose up``` and the project should start deploying to docker
- This should have the microservices up running!

```
orch is running on 28080
owner is running on 18080 
auth is running on 18082
pet is running on 18083
```