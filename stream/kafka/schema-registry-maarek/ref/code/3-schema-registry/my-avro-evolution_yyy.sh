#run docker for schema registry
docker run --net=host -it confluentinc/cp-schema-registry:3.3.0 bash


#run producer
kafka-avro-console-producer \
 --broker-list 127.0.0.1:9092 --topic yyy-avro \
 --property schema.registry.url=http://127.0.0.1:8081 \
  --property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"string"}]}'

{"f1":"value1"}
{"f1":"value2"}

#run consumer
kafka-avro-console-consumer --topic yyy-avro \
    --bootstrap-server 127.0.0.1:9092 \
    --property schema.registry.url=http://127.0.0.1:8081 \
    --from-beginning


#run producer with schema evolved
kafka-avro-console-producer \
    --broker-list localhost:9092 --topic yyy-avro \
    --property schema.registry.url=http://127.0.0.1:8081 \
    --property value.schema='{"type":"record","name":"myrecord","fields":[{"name":"f1","type":"string"},{"name": "f2", "type": "int", "default": 0}]}'

{"f1":"value4", "f2":10}
{"f1":"value5", "f2":11}

#rerun consumer