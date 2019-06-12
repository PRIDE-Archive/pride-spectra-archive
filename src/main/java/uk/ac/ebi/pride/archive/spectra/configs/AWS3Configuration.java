package uk.ac.ebi.pride.archive.spectra.configs;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"uk.ac.ebi.pride.archive.spectra.services"})
public class AWS3Configuration {

    @Value("${spectra.archive.s3.accesskey}")
    String s3AccessKeyId;

    @Value("${spectra.archive.s3.url}")
    String s3url;

    @Value("${spectra.archive.s3.secretkey}")
    String s3SecretAccessKey;

    @Value("${spectra.archive.s3.region}")
    String region;

    @Value("${spectra.archive.s3.bucketname}")
    String bucketName;

    AmazonS3 s3Client;

    @Bean
    AmazonS3 amazonS3Client(){

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(this.s3AccessKeyId, this.s3SecretAccessKey);

//        this.s3Client = AmazonS3ClientBuilder.standard()
//                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3url, region))
//                .enableAccelerateMode()
//                .build();

        this.s3Client = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("https://s3.embassy.ebi.ac.uk/"+ bucketName, "eu-west-2"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withPathStyleAccessEnabled(true)
                .build();

        return s3Client;
    }
}
