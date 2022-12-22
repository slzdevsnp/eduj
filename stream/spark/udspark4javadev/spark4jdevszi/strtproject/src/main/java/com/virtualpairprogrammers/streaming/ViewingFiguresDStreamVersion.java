package com.virtualpairprogrammers.streaming;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.*;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ViewingFiguresDStreamVersion {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("hadoop.home.dir", "/");
        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setAppName("viewingFigures").setMaster("local[*]");

        JavaStreamingContext sc = new JavaStreamingContext(conf, Durations.seconds(2));

        Collection topics = Arrays.asList("viewrecords");

        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092,anotherhost:9092");
        //kafka api Deserializer
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        //group together consumers. (1 member of a group consumes events,
        //                          == this event is consumed by this group
        kafkaParams.put("group.id", "viewfig_group");//required

        kafkaParams.put("auto.offset.reset", "latest"); //start consuming from this offset
        kafkaParams.put("enable.auto.commit", false); //

        JavaInputDStream<ConsumerRecord<String,String>> stream
                = KafkaUtils.createDirectStream(sc,
                    LocationStrategies.PreferConsistent(),
                    ConsumerStrategies.Subscribe(topics, kafkaParams));

        ViewingFiguresDStreamVersion vfds = new ViewingFiguresDStreamVersion();

        //meat work
        //vfds.procIncomingStreamPrint(stream);
        //vfds.procIncomingStreamAgg(stream);
        //vfds.procIncomingStreamAggWnd(stream);
        vfds.procIncomingStreamAggWndSlide(stream);

        sc.start();
        sc.awaitTermination();

    }

    private void procIncomingStreamAggWndSlide(JavaInputDStream<ConsumerRecord<String,String>> streamIn) {
        JavaPairDStream<Long,String> results
                = streamIn.mapToPair(ev -> new Tuple2<>(ev.value(), 5L))
                .reduceByKeyAndWindow((x,y)->x+y,Durations.minutes(60)
                                                ,Durations.seconds(10)) //agg across last hour
                .mapToPair(ev -> ev.swap())                            //show output every 10 secs
                .transformToPair(rdd->rdd.sortByKey(false)); //false == descending order

        results.print(50);
    }


    private void procIncomingStreamAggWnd(JavaInputDStream<ConsumerRecord<String,String>> streamIn) {
        JavaPairDStream<Long,String> results
                = streamIn.mapToPair(ev -> new Tuple2<>(ev.value(), 5L))
                .reduceByKeyAndWindow((x,y)->x+y,Durations.minutes(60)) //agg across last hour
                .mapToPair(ev -> ev.swap())
                .transformToPair(rdd->rdd.sortByKey(false)); //false == descending order

        results.print(50);
    }


    private void procIncomingStreamAgg(JavaInputDStream<ConsumerRecord<String,String>> streamIn) {
        JavaPairDStream<Long,String> results
                = streamIn.mapToPair(ev -> new Tuple2<>(ev.value(), 5L))
                .reduceByKey((x,y)->x+y)
                .mapToPair(ev -> ev.swap())  //to sort not by key but by value
                .transformToPair(rdd->rdd.sortByKey(false)); //false == descending order

        results.print(50);
    }


    private void procIncomingStreamPrint(JavaInputDStream<ConsumerRecord<String,String>> streamIn){
        //5 seconds been consumed for this video
        JavaDStream<String> results = streamIn.map(e -> e.value());
        results.print();
    }


}
