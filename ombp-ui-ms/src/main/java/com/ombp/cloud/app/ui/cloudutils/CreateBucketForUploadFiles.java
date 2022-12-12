package com.ombp.cloud.app.ui.cloudutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GetBucketLocationRequest;

public class CreateBucketForUploadFiles implements AllCreds {

	private static Logger LOG = LoggerFactory.getLogger(CreateBucketForUploadFiles.class);
	
	public static void createBucket(String bucketName) {
		
        try {
        	BasicAWSCredentials awsCreds = new BasicAWSCredentials(CloudProperties.AWS_ACCESS_KEY, CloudProperties.AWS_ACCESS_PASSWORD);
        	
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(CloudProperties.getRegions())
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .build();

            if (!s3Client.doesBucketExistV2(bucketName)) {
                // Because the CreateBucketRequest object doesn't specify a region, the
                // bucket is created in the region specified in the client.
                s3Client.createBucket(new CreateBucketRequest(bucketName));

                // Verify that the bucket was created by retrieving it and checking its location.
                String bucketLocation = s3Client.getBucketLocation(new GetBucketLocationRequest(bucketName));
                LOG.info("Bucket location: " + bucketLocation);
            }
            else 
            {
            	LOG.info("Bucket with Name :: "+ bucketName + " already exists!");
            }
        } catch (AmazonServiceException e) {
        	LOG.error("", e);
        } catch (SdkClientException e) {
        	LOG.error("", e);
        }
	}

}
