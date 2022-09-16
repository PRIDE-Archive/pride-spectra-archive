package uk.ac.ebi.pride.archive.spectra.configs;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

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

    @Bean
    @Qualifier("s3Client")
    S3Client s3Client() {

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(this.s3AccessKeyId, this.s3SecretAccessKey);
        StaticCredentialsProvider awsCredentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);
        return S3Client.builder()
                .endpointOverride(URI.create(s3url))
                .region(Region.of(region))
                .credentialsProvider(awsCredentialsProvider).build();
    }
}
