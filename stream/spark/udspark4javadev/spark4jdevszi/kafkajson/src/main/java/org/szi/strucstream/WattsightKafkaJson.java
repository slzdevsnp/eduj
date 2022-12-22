package org.szi.strucstream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.ArrayType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import org.szi.strucstream.udfs.ArrayContainsNoMissingValues;

import java.io.File;

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.from_json;
import static org.apache.spark.sql.types.DataTypes.*;

public class WattsightKafkaJson {

    private static final String TOPIC_INGRES ="raw-wattsight";
    private static final String TOPIC_EGRES="std-wattsight";
    private static final String CHKPOINTLOC ="/tmp/chkp";
    private StructType schema = null;

    private Logger  log = Logger.getLogger(WattsightKafkaJson.class);

    private void setSchema(){
        StructType lschema = new StructType()
                .add("frequency",StringType)
                .add("time_zone", StringType)
                .add("id",IntegerType)
                .add("name", StringType)
                .add("tag",StringType, true)
                .add("issue_date", StringType)
                .add("created",StringType)
                .add("modified",StringType)
                .add("points", DataTypes.createArrayType(
                        new ArrayType(DoubleType,false)));


        this.schema = lschema;
    }


    public static void main(String[] args) throws StreamingQueryException {
        System.setProperty("hadoop.home.dir", "/");
        Logger.getLogger("org.apache.spark.storage").setLevel(Level.ERROR);

        //create dir if not exists for checklocaion
        boolean dirCreated= new File(CHKPOINTLOC).mkdirs();

        SparkSession session = SparkSession.builder()
                .master("local[*]")
                .appName("wskafkajson")
                .getOrCreate();

        //to improve sql aggregation performance
        session.conf().set("spark.sql.shuffle.partitions", "10");

        session.udf().register("array_contains_no_missed_vals",
                new ArrayContainsNoMissingValues(), DataTypes.BooleanType);

        Dataset<Row> df = session.readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "localhost:9092")
                .option("subscribe", TOPIC_INGRES)
                .option("failOnDataLoss","false")
                .load();
        df.printSchema();

        WattsightKafkaJson wk = new WattsightKafkaJson();
        ///////////////////////////////////////////////////
        wk.processValueSchema(session,df,false);
        //wk.writeKafkaValue(session,df);
    }
    private Dataset<Row> applyUdfs(SparkSession session, Dataset<Row>df){

        Dataset<Row>  withValidationDf = df
                .select(col("*"))
                .withColumn("d_validation",
                        functions.expr(" case when array_contains_no_missed_vals(points) then 'OK' else 'NOK' end"));
        return withValidationDf;
    }

    //this method helps to debug the streaming queries
    private void processValueSchema(SparkSession session, Dataset<Row> df, boolean  isToKafka)
            throws StreamingQueryException {
        setSchema();

        //this query does output individual fields as columns
        Dataset<Row> json2colDf = df
                .select(col("timestamp").as("kfk_timestamp"),
                        from_json(col("value").cast("string"),this.schema ).as("payload"))
                .alias("kv")
                .select("payload.*");

        Dataset<Row>  validDf = applyUdfs(session,json2colDf);

        log.debug(validDf.toString());

        if(!isToKafka) {
            StreamingQuery qs = validDf
                    .writeStream()
                    .format("console")
                    .option("truncate", false)
                    .outputMode("append")
                    .start();
            qs.awaitTermination();
        }else{
            StreamingQuery qs = validDf
                    // key for kfk write can be null
                    .selectExpr("to_json(struct(*)) AS value" )
                    .writeStream()
                    .format("kafka")
                    .option("kafka.bootstrap.servers","localhost:9092")
                    .option("topic", TOPIC_EGRES)
                    .option("checkpointLocation",CHKPOINTLOC)
                    .outputMode(OutputMode.Append())
                    .start();
            qs.awaitTermination();
        }
    }
}
