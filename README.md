# RestApi-Spring-Boot
Employee data rest-api in java using spring-boot.
Multiple , Request are implemented like post,get,patch.delete for employee data with mysql as database.<br />
Redis is used for caching.<br />
Kafka and rabbitmq are used for message broker for post and patch , Request.<br />

<b> <h4>Ports Used: http://localhost:port/ 
 </h4></b>
<br/>
Elastic search : 9200<br />
kibana : 5601<br />
rabbitmq-server : 5672<br />
rabbitmq management : 15672<br />
Apache-server (Current Project) : 8080<br />
redis-server : 6379<br />
kafka - zookeeper : 2181 (server-socketconnection)<br />
kafka - server : 9092<br/>
kafka-client-socket : /127.0.0.1:64664 <br/>
mysql : 3306<br/>

<br/>
<b>URL's: http://localhost:8080
</b>
<br/>
url-"/employee"       , Request - get    , Output - All employees.<br />
url-"/employee"       , Request - post   , Output - Post employee with rabbitmq.<br />
url-"/employee"       , Request - patch  , Output - Update employee with rabbitmq.<br />
url-"/employee/id"    , Request - Delete , Output - Delete an employee.<br />
url-"/employee/id"    , Request - get    , Output - get an employee.<br />
url-"/employee/kafka" , Request - post   , Output - Post an employee with kafka.<br />
url-"/employee/redisentry" , Request-get   , Output - Get employees which are cached in redis.<br />
url-"/employee/redis/id" , Request - get   , Output - Get employee from redis.<br />
<br/>

<b>CMD Commands:</b>
<br/>
To start redis server - reids-server.exe <br/>
to start redis-client - redis-cli.exe <br/>
<b> KAFKA COMMANDS </b>
<br/>
> kafka-server-start.bat E:\kafka_2.13-2.6.0\config\server.properties </br>
> zookeeper-server-start.bat E:\kafka_2.13-2.6.0\config\zookeeper.properties</br>
> zookeeper-shell.bat localhost:2181 ls /brokers/ids </br>
> kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testing </br>
> kafka-console-producer.bat --broker-list localhost:9092 --topic testing </br>
> kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic testing --from-beginning</br>
> kafka-topics.sh --zookeeper localhost:2181 --alter --topic topic1 --config retention.ms=1000</br>
</br>

<b>
Software Requirements:
</b>
<ol>
<li> JAVA (14)</li>
<li> Postman </li>
<li> mysql </li>
<li> Rabbitmq and Erlang</li>
<li> Kafka </li>
<li> Redis </li>
</ol>
