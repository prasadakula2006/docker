# Steps to run the docker spring boot application on docker container

```
docker run -d -p 5000:5000 in28min/todo-rest-api-h2:1.0.0.RELEASE
```

```
docker run -d -p 8761:8761 springcloud/eureka
```

```
docker run -d -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_USER=todos-user -e MYSQL_PASSWORD=dummytodos -e MYSQL_DATABASE=todos --p 3306:3306 mysql:5.7
```
