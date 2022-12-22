package com.virtualpairprogrammers;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainSzi {
    Logger logger = Logger.getLogger(MainSzi.class.getName());

    public static void main(String[] args) {

        MainSzi m = new MainSzi();
        List<Double> dInputData = new ArrayList<>();
        dInputData.add(34.4);
        dInputData.add(90.32);
        dInputData.add(20.43);
        dInputData.add(20.95);
        dInputData.add(99.33);

        List<Integer> iInputData = new ArrayList<>();
        iInputData.add(25);
        iInputData.add(16);
        iInputData.add(144);
        iInputData.add(165);


        SparkConf conf = new SparkConf()
                .setAppName("startingSpark")
                .setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);

        //meat
        m.basicReduceExample(sc,dInputData);
        m.basicMapExample(sc,iInputData);

        //to hold the system
        System.out.println("##### WARN: dirty hack to access Spark WebUi: press any key to terminate job");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        sc.close();
    }

    private void basicReduceExample(JavaSparkContext sc, List<Double> l){
        System.out.println("### basic reduce example");
        JavaRDD<Double> myRdd = sc.parallelize(l);
        Double result = myRdd.reduce((x,y)->x+y);
        System.out.printf("myrdd reduced to:%f%n",result);
    }

    private void basicMapExample(JavaSparkContext sc, List<Integer> l){
        System.out.println("### basic map example");
        JavaRDD<Integer> myRdd = sc.parallelize(l);
        JavaRDD<Double> sqrtRdd = myRdd.map((x)-> Math.sqrt(x));
        //sqrtRdd.foreach(v -> System.out.println(v)); //with lambda expr
        sqrtRdd.collect().forEach(System.out::println);  //throws java.io.NotSerializableException

        System.out.println("\ncounted elements:"+ sqrtRdd.count());

        //work for a count with Long
        Long count = sqrtRdd.map(x -> 1L)
                     .reduce((x,y)->x+y);


    }
}
