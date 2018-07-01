package sigma.aws.sample;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configured with DefaultAWSCredentialsProviderChain
 * which get AWS credentials from environment variables:
 * AWS_ACCESS_KEY_ID (or AWS_ACCESS_KEY)
 * AWS_SECRET_KEY (or AWS_SECRET_ACCESS_KEY)
 */
@Configuration
public class AWSConfiguration {

    @Bean
    public AmazonS3 amazonS3() {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
        return s3Client;
    }

    @Bean
    public AmazonSQS amazonSQS() {
        return AmazonSQSClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    @Bean
    public RestHighLevelClient elastic() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 8081)));
    }
}
