package com.virtualpairprogrammers.streaming;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

import static org.apache.spark.sql.functions.to_json;

import java.util.UUID;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.concat_ws;
import static org.apache.spark.sql.types.DataTypes.*;

public class ViewingFiguresStructuredVersion {




    public static void main(String[] args) throws StreamingQueryException {

        System.setProperty("hadoop.home.dir", "/");
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        Logger.getLogger("org.apache.spark.storage").setLevel(Level.ERROR);

        SparkSession session = SparkSession.builder()
                .master("local[*]")
                .appName("structuredViewingReport")
                .getOrCreate();

        //to improve sql aggregation performance
        session.conf().set("spark.sql.shuffle.partitions", "10");

        Dataset<Row> df = session.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", "viewrecords")
                .load();

        df.printSchema();
        //start some daaframe operations
        df.createOrReplaceTempView("viewing_figures");

        //meat
        ViewingFiguresStructuredVersion vfsv = new ViewingFiguresStructuredVersion();
        //vfsv.writeSink2ConsoleGrpedSorted(session);
        //vfsv.writeSink2ConsoleUpdatedGrped(session);
        //vfsv.writeSink2ConsoleUpdatedGrpedWnd(session);
        vfsv.writeSink2KafkaUpdatedGrpedWnd(session);
    }

    private void writeSink2KafkaUpdatedGrpedWnd(SparkSession session) throws StreamingQueryException {

        String qsql = "select window, cast (value as string) as course_name,sum(5) as seconds_watched"
                + " from viewing_figures group by window(timestamp, '2 minutes'), course_name";
        Dataset<Row> results = session
                .sql(qsql);

        final StructType schema = new StructType()
                .add("window", DataTypes.createArrayType(new ArrayType(DataTypes.TimestampType,false)))
                .add("course_name", StringType,false)
                .add("seconds_watched", IntegerType,true);


        StreamingQuery query = results
                .selectExpr("cast(1 as string) as key", "cast(course_name as string) as value")
                .writeStream()
                .format("kafka")
                .option("kafka.bootstrap.servers","localhost:9092")
                .option("topic", "recresults")
                .option("checkpointLocation","/tmp/chkp")
                .outputMode(OutputMode.Update()) //Update aggregates values which were changed
                .start();
        query.awaitTermination();
    }

    private void writeSink2ConsoleUpdatedGrpedWnd(SparkSession session) throws StreamingQueryException {
        String qsql = "select window, cast (value as string) as course_name,sum(5) as seconds_watched"
                + " from viewing_figures group by window(timestamp, '2 minutes'), course_name";
        Dataset<Row> results = session
                .sql(qsql);

        StreamingQuery query = results.writeStream()
                .format("console")
                .outputMode(OutputMode.Update()) //Update aggregates values which were changed
                .option("truncate",false)
                .option("numrows",50)
                .start();
        query.awaitTermination();
    }

    private void writeSink2ConsoleUpdatedGrped(SparkSession session) throws StreamingQueryException {
        String qsql = "select cast (value as string) as course_name,sum(5) as seconds_watched"
                + " from viewing_figures group by course_name";
        Dataset<Row> results = session
                .sql(qsql);

        StreamingQuery query = results.writeStream()
                .format("console")
                .outputMode(OutputMode.Update()) //Update aggregates values which were changed
                .start();
        query.awaitTermination();
    }
    private void writeSink2ConsoleGrpedSorted(SparkSession session) throws StreamingQueryException {
            Dataset<Row> results = session
                    .sql("select cast (value as string) as course_name," +
                                    " sum(5) as seconds_watched " +
                                    " from viewing_figures" +
                                    " group by course_name" +
                                    " order by seconds_watched desc");

            StreamingQuery query = results.writeStream()
                .format("console")
                .outputMode(OutputMode.Complete()) //complete used for queries with group by
                .start();
        query.awaitTermination();
    }


}
