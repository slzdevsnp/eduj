package com.sparkTutorial.pairRdd.groupbykey;

import com.sparkTutorial.rdd.commons.Utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Map;

public class AirportsByCountrySolution {

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("airports").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("in/airports.text");

        JavaPairRDD<String, String> countryAndAirportNameAndPair =
                lines.mapToPair( airport -> new Tuple2<>(airport.split(Utils.COMMA_DELIMITER)[3],
                                                         airport.split(Utils.COMMA_DELIMITER)[1]));

        JavaPairRDD<String, Iterable<String>> airportsByCountry = countryAndAirportNameAndPair.groupByKey();

        for (Map.Entry<String, Iterable<String>> airports : airportsByCountry.collectAsMap().entrySet()) {
            System.out.println(airports.getKey() + " : " + airports.getValue());
        }
    }
}
