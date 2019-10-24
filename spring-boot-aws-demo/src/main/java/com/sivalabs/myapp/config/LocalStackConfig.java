package com.sivalabs.myapp.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.metrics.RequestMetricCollector;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalStackConfig {

    @Value("${localstack.s3.port}")
    private int s3Port;

    @Value("${localstack.sqs.port}")
    private int sqsPort;

    @Value("${localstack.dynamodb.port}")
    private int dynamodbPort;

    @Value("${localstack.kinesis.port}")
    private int kinesisPort;

    @Value("${localstack.sns.port}")
    private int snsPort;

    @Value("${localstack.cloudwatch.port}")
    private int cloudwatchPort;

    @Value("${localstack.aws.region}")
    private String region;

    static {
        System.setProperty("com.amazonaws.sdk.disableCbor", "true");
    }

    @Bean
    AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getEndpointConfiguration(sqsPort))
                .build();
    }

    @Bean
    AmazonKinesis amazonKinesis() {
        return AmazonKinesisClientBuilder.standard()
                .withEndpointConfiguration(getEndpointConfiguration(kinesisPort))
                .withMetricsCollector(RequestMetricCollector.NONE)
                .build();
    }

    @Bean
    AmazonS3 amazonS3() {
       return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(getEndpointConfiguration(s3Port))
                .withPathStyleAccessEnabled(true)
                .withChunkedEncodingDisabled(true)
                .build();
    }

    @Bean
    AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(getEndpointConfiguration(dynamodbPort))
                .build();
    }

    @Bean
    AmazonCloudWatch amazonCloudWatch() {
        return AmazonCloudWatchClientBuilder.standard()
                .withEndpointConfiguration(getEndpointConfiguration(cloudwatchPort))
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpointConfiguration(int port) {
        String endpoint = "http://localhost:"+port;
        return new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    }

}
