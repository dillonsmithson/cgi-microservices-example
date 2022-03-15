# CGI ECTP Java Track 

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Prerequisties](#prereq)
- [Setting up Database](#database_setup)
- [Launching with localhost](#localhost_launch)
- [Launching with Docker](#docker_launch)

## About <a name = "about"></a>

CGI Early Career Training Program Microservices project for Java Training Track. The project uses Java and Spring boot to run a few microservices to interface with a database that contains owners, pets, images and JWT authetication tokens. 

## Getting Started <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites<a name = "prereq"></a>

There are a few things you'll need to get started.
- Java 11 - (I used [Zulu 11](https://www.azul.com/downloads/?version=java-11-lts&package=jdk))
- Database - [PostgreSQL](https://www.postgresql.org/download/)
- API Testing - [Postman](https://www.postman.com/downloads/)
- Containers through [Docker](https://www.docker.com/products/docker-desktop)
- IDE - [IntelliJ CE](https://www.jetbrains.com/idea/download/#section=windows) is preferred. 

#### Checking Java Version
Ensure that the JDK running on your machine is Java 11
```
java --version
```
You should be seeing 11.x.x... as the version java is running. 

#### Installing PostgreSQL
- When installing, ensure that you uncheck the "Stack Builder" option. 
- When doing username and passwords, stick with "postgres" as your password. This way it's easy to remember.
- After setting up PostgreSQL, make a new user by right clicking on "Login/Group Roles" > "Create" > "Login/Group Role" 
    - Under the "General" Tab, give it the name "username" 
    - Under the "Definition" Tab, give it the password "password"
    - Under the "Privileges" Tab, give all permissions
    - Finally, click on save. 

#### Installing Docker Desktop
Use the instructions for your system from Docker to install Docker Desktop onto your system
- [Mac Download Instructions](https://docs.docker.com/desktop/mac/install/)
- [Windows Download Instructions](https://docs.docker.com/desktop/windows/install/)

### Installing

Here's how to get the microservices running via localhost and docker containers. 

#### Setting up Database: <a name = "database_setup"></a>
Start by opening the program pgAdmin. Login in using the password (should be "postgres" if you followed instructions earlier.)
- Right click on "Databases" > "Create" > "Database..."
- Name the databse "pet"
- Set the "Owner" to "username" (This profile should've been created earlier.)
- Click "Save"

Now, using the SQL file provided in the project files, open the SQL file and copy the contents of the file to your clipboard. 
Back in pgAdmin, drop down the Databases and right click on "pet". Select "Query Tool" and paste the SQL. Click the run button in the Top right corner of the window and the tables will be created for the pet database. 

### Launching the Microservices (using localhost) <a name = "localhost_launch"></a>
Open each Microservice project in IntelliJ including the new "ectpcommon" library.

<b>Setting up common library</b><br/>
In "ectpcommon" under the Gradle tab, drill down "Tasks">"publishing">"publishToMavenLocal"
This should set up the common library.

<b>Setting server ports</b><br/>
You'll need to make sure that in the ```application.properties``` of the auth and owner microservices are set up to include the ```server.port = xxxxx``` property. Without this, each service will default to running on port 8080, which will break the application.

For Owner CRUD copy and paste this property into the ```application.properties``` file
```
server.port = 18080
```

For Auth copy and paste this property into the ```application.properties``` file
```
server.port = 18081
```

<b>Launch each microservice</b><br/>
Once the library is running, go to each microservice and under the Gradle Tab, drill down "Tasks">"application">"bootrun"
This should start running each microservice.

```
orchestrate runs on localhost:8080
owner crud runs on localhost 18080
auth runs on localhost:18081
```

### Launching the Microservices using Docker Compose <a name = "docker_launch"></a>
Open each Microservice project in IntelliJ including the new "ectpcommon" library.

<b>Setting up common library</b><br/>
In "ectpcommon" under the Gradle tab, drill down "Tasks">"publishing">"publishToMavenLocal"
This should set up the common library.

The master branch should have the most up-to-date docker images, so you won't have to build docker images. 

<b><i>Also make sure that you do not have the ```server.port``` property in any of your application.properties! This will break the application</b></i>

<b>Launching</b><br/>
<i>Make sure that you have pgAdmin and Docker Desktop both running on your machine before running the following command.</i>

In the "Orch" microservice, open a terminal and type ```docker-compose up``` and the project should start deploying to docker. The microservices should be running after this.

```
orchestrate runs on localhost:28080
owner crud runs on localhost 18080
auth runs on localhost:18081
```
