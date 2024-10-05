package org.example.cloudproject.controller;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Paths;


public class AwsFileUpload {
    public static void main(String[] args) {


        String bucketName = "saidalo-bucket-two";
        String accessKey = "AKIAXTORPGGI52FBP5TA";
        String filePath = "";


        Region region = Region.US_EAST_1;

        S3Client s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(accessKey)
                .build();


        s3.putObject(putObjectRequest, Paths.get(filePath));
        System.out.println("Fayl muvaffaqiyatli yuklandi!");


    }

}
