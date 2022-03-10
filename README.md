# CGI ECTP Java Track 

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)

## About <a name = "about"></a>

CGI Early Career Training Program Microservices project for Java Training Track. The project uses Java and Spring boot to run a few microservices to interface with a database that contains owners, pets, images and JWT authetication tokens. 

## Getting Started <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

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

#### Setting up Database:
Start by opening the program pgAdmin. Login in using the password (should be "postgres" if you followed instructions earlier.)
- Right click on "Databases" > "Create" > "Database..."
- Name the databse "pet"
- Set the "Owner" to "username" (This profile should've been created earlier.)
- Click "Save"

Now, using the SQL file provided in the project files, open the SQL file and copy the contents of the file to your clipboard. 
Back in pgAdmin, drop down the Databases and right click on "pet". Select "Query Tool" and paste the SQL. Click the run button in the Top right corner of the window and the tables will be created for the pet database. 

#### Launching the Microservices (using localhost)
Open each Microservice project in IntelliJ. 

## Usage <a name = "usage"></a>

Add notes about how to use the system.
