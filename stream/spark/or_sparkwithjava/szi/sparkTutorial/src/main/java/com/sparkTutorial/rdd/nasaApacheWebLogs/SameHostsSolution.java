package com.sparkTutorial.rdd.nasaApacheWebLogs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class SameHostsSolution {

    public static void main(String[] args) throws Exception {

        SparkConf conf = new SparkConf().setAppName("sameHosts").setMaster("local[2]");

        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> julyFirstLogs = sc.textFile("in/nasa_19950701.tsv");
        JavaRDD<String> augustFirstLogs = sc.textFile("in/nasa_19950801.tsv");

        //first field in \t delimited file
        JavaRDD<String> julyFirstHosts = julyFirstLogs.map(line -> line.split("\t")[0]);

        JavaRDD<String> augustFirstHosts = augustFirstLogs.map(line -> line.split("\t")[0]);
        //calls intersection the set operation
        JavaRDD<String> intersection = julyFirstHosts.intersection(augustFirstHosts);

        //this strips off the element coming from a header line in tcsv file
        JavaRDD<String> cleanedHostIntersection = intersection.filter(host -> !host.equals("host"));
        //lazy eval eval kicks in here
        cleanedHostIntersection.saveAsTextFile("out/nasa_logs_same_hosts.csv");
    }
}
