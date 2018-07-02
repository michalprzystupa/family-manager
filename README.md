# family-manager

Command "mvn clean package" build all docker images

# How to start docker containers:
- docker network create {network-name}
- docker run --rm --net={network-name} --name db -v {path-for-db-data-volume}:/var/lib/mysql family/database:1.0
- docker run --rm --net={network-name} --name spring -p 8081:8081 family/spring-service:1.0
- docker run --rm --net={network-name} --name angular -p {angular-port}:4200 family/angular-service:1.0

# example:
- docker network create family-network
- docker run --rm --net=family-network --name db -v /var/lib/mysql/family-manager:/var/lib/mysql family/database:1.0
- docker run --rm --net=family-network --name spring -p 8081:8081 family/spring-service:1.0
- docker run --rm --net=family-network --name angular -p 8080:4200 family/angular-service:1.0

Rest api is available at http://localhost:8081/families/{path}
Angular application start point is available at http://localhost:{angular-port}/{path}

comments:
- pesel always has 11 digits and identify one person
- correct birth date format is yyyy-mm-dd
