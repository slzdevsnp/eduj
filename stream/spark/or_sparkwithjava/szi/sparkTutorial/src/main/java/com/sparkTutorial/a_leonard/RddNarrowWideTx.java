package com.sparkTutorial.a_leonard;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RddNarrowWideTx {

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org").setLevel(Level.ERROR);
        // will use all local cpu cores
        SparkConf conf = new SparkConf().setAppName("collect").setMaster("local[*]");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        List<String> inputWords
                = Arrays.asList("a", "b", "c", "c", "a", "a", "c", "b", "d");

        JavaRDD<String> rdds = jsc.parallelize(inputWords);

        //no dependency reported
        System.out.println("deb rdds" + rdds.rdd().dependencies());

        //narrow
        JavaPairRDD<String, Integer> pairs = rdds.mapToPair(e -> new Tuple2<>(e, 1));
        //OnetoOne dependency reported
        System.out.println("deb pairs" + pairs.rdd().dependencies());

        //wide
        JavaPairRDD<String, Iterable<Integer>> grp_pairs = pairs.groupByKey();

        //OnetoOne dependency reported
        System.out.println("deb grp_pairs" + grp_pairs.rdd().dependencies());
        System.out.println("debug: grp_pairs " + grp_pairs.toDebugString());

        //action
        Map<String, Iterable<Integer>> elems = grp_pairs.collectAsMap();

        for (String k : elems.keySet()) {
            System.out.println("key: " + k + ", value:"+elems.get(k));
        }
    }

}
