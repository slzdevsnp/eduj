package org.szi.strucstream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.StructType;

import java.io.File;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;
import static org.apache.spark.sql.types.DataTypes.IntegerType;
import static org.apache.spark.sql.types.DataTypes.StringType;

public class PersonKafkaJson {
    private static final String TOPIC_INGRES ="json_incoming";
    private static final String TOPIC_EGRES="json_out";
    private static final String CHKPOINTLOC ="/tmp/chkp";
    private StructType schema = null;

    private void setSchema(){
        StructType lschema = new StructType()
                .add("id",IntegerType)
                .add("firstname",StringType)
                .add("middlename",StringType)
                .add("lastname",StringType)
                .add("dob_year",IntegerType)
                .add("dob_month",IntegerType)
                .add("gender",StringType)
                .add("salary",IntegerType);
        this.schema = lschema;
    }

    public static void main(String[] args) throws StreamingQueryException {
        System.setProperty("hadoop.home.dir", "/");
        Logger.getLogger("org.apache.spark.storage").setLevel(Level.ERROR);

        //create dir if not exists for checklocaion
        boolean dirCreated= new File(CHKPOINTLOC).mkdirs();

        SparkSession session = SparkSession.builder()
                .master("local[*]")
                .appName("kafkajson")
                .getOrCreate();

        //to improve sql aggregation performance
        session.conf().set("spark.sql.shuffle.partitions", "10");

        Dataset<Row> df = session.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", TOPIC_INGRES)
                .option("failOnDataLoss","false")
                .load();

        df.printSchema();

        PersonKafkaJson jk = new PersonKafkaJson();

        //jk.writeConsoleValueRaw(session, df); //OK
        //jk.writeConsoleValueSchema(session,df); //OK
        jk.writeKafkaValue(session,df); //OK
    }
    private void writeKafkaValue(SparkSession session, Dataset<Row> df)
            throws StreamingQueryException {
        setSchema();
        //we need a schema in order to convert json in source kfk msg to df columns
        Dataset<Row> columnsDf = df
                .select(col("timestamp").as("kfk_timestamp"),
                        from_json(col("value").cast("string"),this.schema ).as("payload"))
                .alias("kv")
                .select("payload.*");

        StreamingQuery query = columnsDf
                // key for kfk write can be null
                .selectExpr("to_json(struct(*)) AS value" )
                // generate a randomUUID from java for kafka key
                //.selectExpr("CAST(java_method('java.util.UUID','randomUUID') AS STRING) as key","to_json(struct(*)) AS value" )
                //generate a current timestamp fo kafka key (timestamp is already saved in a dedicated field)
                //.selectExpr("CAST(from_unixtime(unix_timestamp()) AS STRING) as key","to_json(struct(*)) AS value" )
                .writeStream()
                .format("kafka")
                .option("kafka.bootstrap.servers","localhost:9092")
                .option("topic", TOPIC_EGRES)
                .option("checkpointLocation",CHKPOINTLOC)
                .outputMode(OutputMode.Append())
                .start();
        query.awaitTermination();
    }

    private void writeConsoleValueSchema(SparkSession session, Dataset<Row> df)
            throws StreamingQueryException {
        setSchema();

        //this query does output
//        +-----------------------+--------------------------------------+
//        |kfk_timestamp                    |payload                               |
//        +-----------------------+--------------------------------------+
//        |2020-04-25 08:53:53.394|[2, Michael, Rose, , 2010, 3, M, 4000]|
//        +-----------------------+--------------------------------------+
        Dataset<Row> pjsonDf1 = df
                .select(col("timestamp").as("kfk_timestamp"),
                 from_json(col("value").cast("string"),this.schema ).as("payload"))
                .alias("kv")
                .select("kv.*");  // ("payload.*") gets individ fields, ("kv.*" gets key - value)

        //this query does output
//        +---+---------+----------+--------+--------+---------+------+------+
//        |id |firstname|middlename|lastname|dob_year|dob_month|gender|salary|
//        +---+---------+----------+--------+--------+---------+------+------+
//        |2  |Michael  |Rose      |        |2010    |3        |M     |4000  |
//        +---+---------+----------+--------+--------+---------+------+------+
        Dataset<Row> pjsonDf2 = df
                .select(col("timestamp").as("kfk_timestamp"),
                        from_json(col("value").cast("string"),this.schema ).as("payload"))
                .alias("kv")
                .select("payload.*");

        //select a right df for output
        StreamingQuery  qs = pjsonDf2
                .writeStream()
                .format("console")
                .option("truncate",false)
                .outputMode("append")
                .start();
        qs.awaitTermination();
    }
    private void writeConsoleValueRaw(SparkSession session, Dataset<Row> df)
            throws StreamingQueryException {

        StreamingQuery query = df
                .selectExpr("CAST(key AS STRING)","cast(value as string)")
                .writeStream()
                .format("console")
                .option("truncate",false)
                .outputMode("append")
                .start();
        query.awaitTermination();
    }

}
