# RestApi-Spring-Boot
Employee data rest-api in java using spring-boot.
Multiple request are implemented like post,get,patch.delete for employee data with mysql as database.
Redis is used for caching.
Kafka and rabbitmq are used for message broker for post and patch request.

#Ports Used: http://localhost:port/

Elastic search: 9200
kibana: 5601
rabbitmq : 5672
rabbitmq management : 15672
member-info (Current Project) : 8080
redis-server : 6379
kafka - zookeeper : 2181 (socketconnection)
kafka - server : 9092
kafka-client-socket: (client: /127.0.0.1:64664)
mysql : 3306

#URL's: http://localhost:8080

url-"/employee"       request - get    Output - All employees.
url-"/employee"       request - post   Output - Post employee with rabbitmq.
url-"/employee"       request - patch  Output - Update employee with rabbitmq.
url-"/employee/id"    request - Delete Output - Delete an employee.
url-"/employee/id"    request - get    Output - get an employee.
url-"/employee/kafka" request - post   Output - Post an employee with kafka.
url-"/employee/redisentry" request-get   Output - Get employees which are cached in redis.
url-"/employee/redis/id" request - get   Output - Get employee from redis.

