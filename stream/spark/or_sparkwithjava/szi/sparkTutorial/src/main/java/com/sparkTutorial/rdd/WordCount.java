package com.sparkTutorial.rdd;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.Map;

public class WordCount {

    public static void main(String[] args) throws Exception {

        Logger.getLogger("org").setLevel(Level.ERROR);
        //can use up to 3 cores of the cpu
        SparkConf conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]");
        //entry point
        JavaSparkContext sc = new JavaSparkContext(conf);

        //sc.textFile is loading data in RDD from FS, amazon S3 is a more realistic distributed FS
        JavaRDD<String> lines = sc.textFile("in/word_count.text");
        // .ttextFile(), flatMap() are transForms
        JavaRDD<String> words = lines.flatMap(line -> Arrays.asList(line.split(" ")).iterator());

        //NB map.entrySet()  countByValue() is an action
        Map<String, Long> wordCounts = words.countByValue();

        for (Map.Entry<String, Long> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
