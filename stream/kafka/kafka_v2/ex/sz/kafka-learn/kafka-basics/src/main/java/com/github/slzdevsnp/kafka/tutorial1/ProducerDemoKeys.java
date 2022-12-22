package com.github.slzdevsnp.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {

    public static void main(String[] args)  throws ExecutionException, InterruptedException {


        final Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer  (msg key,values are strings)
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);
        for (int i = 0; i < 10; i++){

            String topic = "first_topic";
            String value = "hello world " + Integer.toString(i);
            String key = "id_" + Integer.toString(i);
            // create a producer record
            ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,key,value);
            //add header values to kafka record
            record.headers().add("client_id", key.getBytes(StandardCharsets.UTF_8));
            record.headers().add("client_data", ("dataxx"+Integer.toString(i)).getBytes(StandardCharsets.UTF_8));


            logger.info("Key: " + key); // log the key
            ///  checking in the log
            // id_0 is going to partition 1
            // id_1 partition 0
            // id_2 partition 2
            // id_3 partition 0
            // id_4 partition 2
            // id_5 partition 2
            // id_6 partition 0
            // id_7 partition 2
            // id_8 partition 1
            // id_9 partition 2
            ///// when code is rerun the same keys go to the same partitions
            //// this is important

            // send data - asynchronous
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        // the record was successfully sent
                        logger.info("Received new metadata. \n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition: " + recordMetadata.partition() + "\n" +
                                "Offset: " + recordMetadata.offset() + "\n" +
                                "Timestamp: " + recordMetadata.timestamp());
                    } else {
                        logger.error("Error while producing", e);
                    }
                }
            }).get(); //blocks the send to make it synchronous, don't do it in prod
        }

        // flush data
        producer.flush();  //forces to send the record before the program exists
        // flush and close producer
        producer.close();

    }
}
