# Docker in 5 Steps

Let's learn Docker in 5 Easy Steps. 

- Step 00 - Installing Docker
- Step 01 - A simple Docker use case - Run an existing application
- Step 02 - Playing with Docker - Containers and Images
- Step 03 - How does Docker work?
- Step 04 - Manually creating a docker image
- Step 05 - Dockerizing a Spring Boot Application using Dockerfile and Spotify Maven Plugin

### Step 00 - Installing Docker

- https://docs.docker.com/install/

### Step 01 - Run Sample docker examples

- Find docker example applications under examples folder

#### Traditional Deployment

![Traditional Deployment](images/docker-traditional-deployment.png)

#### Deployment with Docker

![Docker Deployment](images/docker-zz-deployment.png)


### Step 02 - Playing with Docker - Containers and Images

- Image is static
- Container is dynamic

```
  docker run in28min/todo-rest-api-h2:1.0.0.RELEASE
  docker run -p 5000:5000 in28min/todo-rest-api-h2:1.0.0.RELEASE
  docker run -p 8761:8761 springcloud/eureka
  docker run -d -p 5000:5000 in28min/todo-rest-api-h2:1.0.0.RELEASE
  docker run -d -p 8761:8761 springcloud/eureka
  docker images
  docker containers 
  docker containers ls
  docker container ls -l
  docker container ls -a
  docker container start fed549e69e9d
  docker container stop tender_ardinghelli
  docker container start c165f459e7d7
  docker container stop 151a77679241
  docker container logs c165f459e7d7
  docker container rm fed549e69e9d
  docker container prune
  docker container inspect 0967ba7aa180
  docker image history f8049a029560
  docker image remove f8049a029560
```

### Step 03 - How does Docker work?

#### Docker Architecture

![Docker Architecture](images/docker-architecture.png)

### Step 04 - Manually creating a new docker image

```
  716  docker run -dit openjdk:8-jdk-alpine
  720  docker container cp target/docker-in-5-steps-todo-rest-api-h2-1.0.0.RELEASE.jar 28d5e5d893fdb1530e9920ff66fee252adc834a8b572b77b3fdf7d316730127c:/tmp
  725  docker container exec romantic_aryabhata ls /tmp
    734  docker container commit romantic_aryabhata in28min/manual-todo-rest-api:v1
  735  docker container commit --change='CMD ["java","-jar","/tmp/docker-in-5-steps-todo-rest-api-h2-1.0.0.RELEASE.jar"]' romantic_aryabhata in28min/manual-todo-rest-api:v2
  743  docker run -d -p 5000:5000 in28min/manual-todo-rest-api:v2
```

### Step 05 : Containerizing Spring Boot Application using Dockerfile and Spotify Maven Plugin

Run com.in28minutes.rest.webservices.restfulwebservices.RestfulWebServicesApplication as a Java Application.

- mvn package
- docker run -d -p 5000:5000 in28min/docker-in-5-steps-todo-rest-api-h2:1.0.0.RELEASE
- docker login
- docker push in28min/docker-in-5-steps-todo-rest-api-h2:1.0.0.RELEASE

#### Troubleshooting

- Problem - Caused by: com.spotify.docker.client.shaded.javax.ws.rs.ProcessingException: java.io.IOException: No such file or directory
- Solution - Check if docker is up and running!
- Problem - Error creating the Docker image on MacOS - java.io.IOException: Cannot run program “docker-credential-osxkeychain”: error=2, No such file or directory
- Solution - https://medium.com/@dakshika/error-creating-the-docker-image-on-macos-wso2-enterprise-integrator-tooling-dfb5b537b44e


#### Creating Containers

To test execute API at http://localhost:5000/users/in28minutes/todos.

```
docker login
```

#### Hello World URLS

- http://localhost:5000/hello-world

```txt
Hello World
```

- http://localhost:5000/hello-world-bean

```json
{"message":"Hello World - Changed"}
```

- http://localhost:5000/hello-world/path-variable/in28minutes

```json
{"message":"Hello World, in28minutes"}
```


#### Todo JPA Resource URLs

- GET - http://localhost:5000/jpa/users/in28minutes/todos

```
[
  {
    "id": 10001,
    "username": "in28minutes",
    "description": "Learn JPA",
    "targetDate": "2019-06-27T06:30:30.696+0000",
    "done": false
  },
  {
    "id": 10002,
    "username": "in28minutes",
    "description": "Learn Data JPA",
    "targetDate": "2019-06-27T06:30:30.700+0000",
    "done": false
  },
  {
    "id": 10003,
    "username": "in28minutes",
    "description": "Learn Microservices",
    "targetDate": "2019-06-27T06:30:30.701+0000",
    "done": false
  }
]
```

##### Retrieve a specific todo

- GET - http://localhost:5000/jpa/users/in28minutes/todos/10001

```
{
  "id": 10001,
  "username": "in28minutes",
  "description": "Learn JPA",
  "targetDate": "2019-06-27T06:30:30.696+0000",
  "done": false
}
```

##### Creating a new todo

- POST to http://localhost:5000/jpa/users/in28minutes/todos with BODY of Request given below

```
{
  "username": "in28minutes",
  "description": "Learn to Drive a Car",
  "targetDate": "2030-11-09T10:49:23.566+0000",
  "done": false
}
```

##### Updating a new todo

- http://localhost:5000/jpa/users/in28minutes/todos/10001 with BODY of Request given below

```
{
  "id": 10001,
  "username": "in28minutes",
  "description": "Learn to Drive a Car",
  "targetDate": "2045-11-09T10:49:23.566+0000",
  "done": false
}
```

##### Delete todo

- DELETE to http://localhost:5000/jpa/users/in28minutes/todos/10001


#### H2 Console

- http://localhost:5000/h2-console
- Use `jdbc:h2:mem:testdb` as JDBC URL 
