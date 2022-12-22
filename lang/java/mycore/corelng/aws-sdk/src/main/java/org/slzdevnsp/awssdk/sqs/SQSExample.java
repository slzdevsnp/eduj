package org.slzdevnsp.awssdk.sqs;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

/* sqs example code from
 https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/sqs/src/main/java/com/example/sqs/SQSExample.java

referenced at https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-sqs-message-queues.html

expected to have a valid login credentials  (aws_metis_login ) //did not work on 20201-06-07
*/

@Slf4j
public class SQSExample {

    public static void main(String[] args) {


        SqsClient sqsClient = SqsClient.builder()
                .region(Region.EU_CENTRAL_1)
                .credentialsProvider(ProfileCredentialsProvider.create("default"))
                .build();
        listQueues(sqsClient);
        /*String qUrl = getQueueUrl(sqsClient,"metis-pointconnect-pcns");
        log.info("fetched qUrl - {}",qUrl);*/
    }

    public static String getQueueUrl(SqsClient sqsClient, String queueName) {
        try {
            GetQueueUrlResponse getQueueUrlResponse =
                    sqsClient.getQueueUrl(GetQueueUrlRequest.builder().queueName(queueName).build());
            String queueUrl = getQueueUrlResponse.queueUrl();
            return queueUrl;

        } catch (
                SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        return "";
    }


    public static void listQueues(SqsClient sqsClient) {

        System.out.println("\n ### List Queues");

        String prefix = "metis";

        try {
            ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder().queueNamePrefix(prefix).build();
            ListQueuesResponse listQueuesResponse = sqsClient.listQueues(listQueuesRequest);

            for (String url : listQueuesResponse.queueUrls()) {
                System.out.println("#### " + url);
            }

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

}
