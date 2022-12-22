package com.github.slzdevsnp.kafka.tutorial4;


import com.google.gson.JsonParser;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class StreamsFilterTweets {

    static String bootstrapServers="127.0.0.1:9092";
    static String appId="demo-kafka-streams";
    static String ingTopic ="twitter_tweets";
    static String routeTopic="important_tweets";

    public static void main(String[] args) {
        //create properties
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        properties.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG,
                Serdes.StringSerde.class.getName());
        properties.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,
                Serdes.StringSerde.class.getName());


        //create topology
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        int folowersThreshold = 10000;
        KStream<String, String> inputTopic = streamsBuilder.stream(ingTopic);
        KStream<String, String> filteredStream = inputTopic.filter(
                // filter for tweets which has a user of over 10000 followers
                (k, jsonTweet) ->  extractUserFollowersInTweet(jsonTweet,folowersThreshold) > folowersThreshold
        );
        filteredStream.to(routeTopic); //resent to a new topic
        //build topology
        KafkaStreams kafkaStreams = new KafkaStreams(
                streamsBuilder.build(),
                properties
        );
        //start our streams appliaction
        kafkaStreams.start();
        System.out.println("listening to the kafka filtered streams from topic :"+ ingTopic);
    }

    private static JsonParser jsonParser = new JsonParser();

    private static Integer extractUserFollowersInTweet(String tweetJson, int threshold){
        Integer nfollowers = null;
        // gson library
        try {
            nfollowers = jsonParser.parse(tweetJson)
                    .getAsJsonObject()
                    .get("user")
                    .getAsJsonObject()
                    .get("followers_count")
                    .getAsInt();
        }
        catch (NullPointerException e){
            return 0;
        }
        if (nfollowers.intValue() > threshold){
            System.out.println("current tweet with followers " + nfollowers
            + " copied to importantTweets");
        }
        return nfollowers;
    }
}
