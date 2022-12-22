package com.sparkTutorial.advanced.accumulator;

import com.sparkTutorial.rdd.commons.Utils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.util.LongAccumulator;
import scala.Option;

public class StackOverFlowSurveyFollowUp {

    public static void main(String[] args) throws Exception {
        Logger.getLogger("org").setLevel(Level.ERROR);
        SparkConf conf = new SparkConf().setAppName("StackOverFlowSurvey").setMaster("local[1]");
        SparkContext sparkContext = new SparkContext(conf);
        JavaSparkContext javaSparkContext = new JavaSparkContext(sparkContext);

        final LongAccumulator total = new LongAccumulator();
        final LongAccumulator missingSalaryMidPoint = new LongAccumulator();
        final LongAccumulator processedBytes = new LongAccumulator();

        total.register(sparkContext, Option.apply("total"), false);
        missingSalaryMidPoint.register(sparkContext, Option.apply("missing salary middle point"), false);
        processedBytes.register(sparkContext, Option.apply("Processed bytes"), true);

        JavaRDD<String> responseRDD = javaSparkContext.textFile("in/2016-stack-overflow-survey-responses.csv");

        JavaRDD<String> responseFromCanada = responseRDD.filter(response -> {

            // update processedBytes accumulator with the size of the current response
            processedBytes.add(response.getBytes().length);
            
           // split the reponse using commas.
            String[] splits = response.split(Utils.COMMA_DELIMITER, -1);

            // increase the total accumulator by 1
            total.add(1);

            // increase the missingSalaryMidPoint accumulator by 1 if the salary middle point is not present in the reponse
            if (splits[14].isEmpty()) {
                missingSalaryMidPoint.add(1);
            }
            // return true if the reponse is from Canada
            return splits[2].equals("Canada");

        });

        System.out.println("Count of responses from Canada: " + responseFromCanada.count());
        System.out.println("Number of bytes processed: " + processedBytes.value());
        System.out.println("Total count of responses: " + total.value());
        System.out.println("Count of responses missing salary middle point: " + missingSalaryMidPoint.value());
    }
}
