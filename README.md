# RestApi-Spring-Boot
Employee data rest-api in java using spring-boot.
Multiple , Request are implemented like post,get,patch.delete for employee data with mysql as database.<br />
Redis is used for caching.<br />
Kafka and rabbitmq are used for message broker for post and patch , Request.<br />

#Ports Used: http://localhost:port/

Elastic search : 9200<br />
kibana : 5601<br />
rabbitmq-server : 5672<br />
rabbitmq management : 15672<br />
Apache-server (Current Project) : 8080<br />
redis-server : 6379<br />
kafka - zookeeper : 2181 (server-socketconnection)<br />
kafka - server : 9092<br />
kafka-client-socket : /127.0.0.1:64664 <br />
mysql : 3306<br />

#URL's: http://localhost:8080

url-"/employee"       , Request - get    , Output - All employees.<br />
url-"/employee"       , Request - post   , Output - Post employee with rabbitmq.<br />
url-"/employee"       , Request - patch  , Output - Update employee with rabbitmq.<br />
url-"/employee/id"    , Request - Delete , Output - Delete an employee.<br />
url-"/employee/id"    , Request - get    , Output - get an employee.<br />
url-"/employee/kafka" , Request - post   , Output - Post an employee with kafka.<br />
url-"/employee/redisentry" , Request-get   , Output - Get employees which are cached in redis.<br />
url-"/employee/redis/id" , Request - get   , Output - Get employee from redis.<br />

#Software Requirements:
<ol>
<li> JAVA (14)</li>
<li> Postman </li>
<li> mysql </li>
<li> Rabbitmq and Erlang</li>
<li> Kafka </li>
<li> Redis </li>
</ol>
