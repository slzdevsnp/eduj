package com.virtualpairprogrammers.streaming;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

public class LogStreamAnalysis {


    public static void main(String[] args) throws InterruptedException {

        System.setProperty("hadoop.home.dir", "/"); //readlly needed on windows
        Logger.getLogger("org.apache.spark.storage").setLevel(Level.ERROR);

        System.setProperty("hadoop.home.dir", "/"); //readlly needed on windows
        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("startingSaprk");

        //Durations is main param for Dstream JavaStreamingContext
        JavaStreamingContext sc = new JavaStreamingContext(conf,
                                                        Durations.seconds(2));

        JavaReceiverInputDStream<String> inputData
                = sc.socketTextStream("localhost", 8989);

        LogStreamAnalysis lsa = new LogStreamAnalysis();

        //meat  either 1 or 2
        //lsa.procPrintBatchNoHistoryWingowing(sc,inputData);
        lsa.procPrintBatchAggByWindow(sc,inputData);

        sc.start(); //starts processing a stream of data
        sc.awaitTermination();
        //at every change of spark job,  restart the LoggingServer source
    }

    void procPrintBatchAggByWindow(JavaStreamingContext jsc, JavaReceiverInputDStream<String>  input ) {
        System.out.println("#### proc Bactches by Window");
        JavaDStream<String> results = input.map(x -> x);
        JavaPairDStream<String, Long> pairDStream
                = results.mapToPair(rawMsg -> new Tuple2<> (rawMsg.split(",")[0],1L));
        JavaPairDStream<String, Long> aggWndPairDStream =
                //reduceByKeyAndWindow . Duration is for the window agg
                //sliding is by batch duration
                pairDStream.reduceByKeyAndWindow((x, y) -> x + y, Durations.minutes(2));
        aggWndPairDStream.print();

    }
        void procPrintBatchNoHistoryWingowing(JavaStreamingContext jsc, JavaReceiverInputDStream<String>  input ){

        JavaDStream<String> results = input.map(x -> x);

        //NB! Tuple2  type is covered in section 5
        JavaPairDStream<String, Long> pairDStream = results
                .mapToPair(rawMsg -> new Tuple2<>(rawMsg.split(",")[0],1L));
        JavaPairDStream<String, Long> aggPairDStream
                = pairDStream.reduceByKey((x,y)->x+y);
        aggPairDStream.print();

    }

}
