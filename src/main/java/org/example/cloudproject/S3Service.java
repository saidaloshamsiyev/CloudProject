package org.example.cloudproject;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;


@Service

public class S3Service {

    private final S3Client s3Client;
    private String bucketName;

    public S3Service(@Value("${aws.region}") String region,
                     @Value("${aws.access-key-id}") String accessKeyId,
                     @Value("${aws.secret-access-key}") String secretAccessKey) {
         s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKeyId, secretAccessKey)))
                .build();
    }

    public String uploadFileToS3(File file, String fileName) {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();

            PutObjectResponse response = s3Client.putObject(putObjectRequest,
                    RequestBody.fromFile(file));

            return response.eTag();
        } catch (Exception e) {
            throw new RuntimeException("Fayl yuklashda xatolik: " + e.getMessage(), e);
        }
    }

}
