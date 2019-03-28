# Spring Cloud Tutorial

Config Repository: https://github.com/sivaprasadreddy/config-repo.git

### Spring Cloud Config Server

`> cd spring-cloud-config-server`

`> mvn spring-boot:run` 

Access http://localhost:8888/catalogservice/default or http://localhost:8888/catalogservice/prod

 
### Catalog Service

`> cd catalog-service`

`> mvn spring-boot:run` 

Access http://localhost:8181/env

### Order Service

`> cd order-service`

`> mvn spring-boot:run` 

Access http://localhost:8282/env


To reload configuration changes you can trigger POST - http://localhost:8181/bus/refresh or http://localhost:8282/bus/refresh
