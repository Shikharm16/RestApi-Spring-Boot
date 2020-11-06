# RestApi-Spring-Boot
Employee data rest-api in java using spring-boot.
Multiple request are implemented like post,get,patch.delete for employee data with mysql as database.<br />
Redis is used for caching.<br />
Kafka and rabbitmq are used for message broker for post and patch request.<br />

#Ports Used: http://localhost:port/

Elastic search: 9200<br />
kibana: 5601<br />
rabbitmq : 5672<br />
rabbitmq management : 15672<br />
member-info (Current Project) : 8080<br />
redis-server : 6379<br />
kafka - zookeeper : 2181 (socketconnection)<br />
kafka - server : 9092<br />
kafka-client-socket: (client: /127.0.0.1:64664)<br />
mysql : 3306<br />

#URL's: http://localhost:8080

url-"/employee"       request - get    Output - All employees.<br />
url-"/employee"       request - post   Output - Post employee with rabbitmq.<br />
url-"/employee"       request - patch  Output - Update employee with rabbitmq.<br />
url-"/employee/id"    request - Delete Output - Delete an employee.<br />
url-"/employee/id"    request - get    Output - get an employee.<br />
url-"/employee/kafka" request - post   Output - Post an employee with kafka.<br />
url-"/employee/redisentry" request-get   Output - Get employees which are cached in redis.<br />
url-"/employee/redis/id" request - get   Output - Get employee from redis.<br />

