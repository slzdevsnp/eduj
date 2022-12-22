package com.github.slzdevsnp.kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerDemoWithThread {

    public static void main(String[] args) {
        new ConsumerDemoWithThread().run();

    }

    private void run(){
            Logger logger = LoggerFactory.getLogger(ConsumerDemoWithThread.class);
            String bootstrapServers = "127.0.0.1:9092";
            String groupId = "my-sixth-application";
            String ingTopic = "first_topic";

            CountDownLatch latch = new CountDownLatch(1);
            logger.info("creating the consumer thread");
            Runnable myConsumerRunnable = new ConsumerRunnable(bootstrapServers,
                    groupId,
                    ingTopic,
                    latch
            );
            Thread myThread = new Thread(myConsumerRunnable);
            //start the thread
            myThread.start();

            //add a shutdown hook (reacts on stop,exit signal)
            Runtime.getRuntime().addShutdownHook( new Thread( () -> {
                logger.info("Caught shutdownn hook");
                ((ConsumerRunnable) myConsumerRunnable).shutdown();
                try{
                    latch.await();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                logger.info("Application has exited");
            }
            ));

            try {
                latch.await();
            }catch (InterruptedException e){
                logger.error("Application got interrupted",e);
            } finally {
                logger.info("Application is closing");
            }
    }

    public class ConsumerRunnable implements Runnable {

        private CountDownLatch latch;
        private  KafkaConsumer<String, String> consumer;
        private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);


        public ConsumerRunnable(String bootstrapServers,
                                String groupId,
                                String topic,
                                CountDownLatch latch){

            this.latch = latch;
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //earliest/latest/none

            consumer  = new KafkaConsumer<String, String>(properties);
            consumer.subscribe(Arrays.asList(topic)); //subscribe to a collection of topics

        }

        @Override
        public void run() {

            try {
                while (true) {  //avoid using while(true) in prod
                    ConsumerRecords<String, String> records =
                            consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0
                    for (ConsumerRecord<String, String> record : records) {
                        logger.info("Key: " + record.key() + ", Value: " + record.value());
                        logger.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                    }
                    //consumer reads all values from part 0, then from part 1 then from part 2
                }
            }catch (WakeupException e){
                logger.info("Rcvd shutdown sig!");
            } finally{
                consumer.close();
                latch.countDown();
            }

        }

        public void shutdown(){
            //the wakeup() method iterrupts consumer.poll()
            //it throws exception
            consumer.wakeup();
        }
    }
}
