package com.ombp.cloud.app.ui.cloudutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class DeleteBucket implements AllCreds {

	private static Logger LOG = LoggerFactory.getLogger(DeleteBucket.class);
	
	public static void delete(String bucketName) {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(CloudProperties.AWS_ACCESS_KEY, CloudProperties.AWS_ACCESS_PASSWORD);
    	
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(CloudProperties.getRegions())
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
        
        if(s3Client.doesBucketExistV2(bucketName)) 
        {
        	emptyBucket(bucketName, s3Client);
            deleteBucket(bucketName, s3Client);	
        }
        
        LOG.info("Delete success for "+bucketName);
	}

	
	public static void emptyBucket(String bucketName, AmazonS3 s3Client) {
        try {
            ListObjectsV2Result objects = s3Client.listObjectsV2(bucketName);
            for (S3ObjectSummary s : objects.getObjectSummaries()) {
            	s3Client.deleteObject(bucketName, s.getKey());
            }
        } catch (AmazonServiceException ae) {
        	LOG.error("Failed to empty the bucket ", ae);
        } catch (SdkClientException se) {
            LOG.error("Failed to empty the bucket ", se);
        }
    }

	public static void deleteBucket(String bucketname, AmazonS3 s3Client) {
        try {
        	s3Client.deleteBucket(bucketname);
        } catch (AmazonS3Exception ae) {
            if (ae.getErrorCode().equals("NoSuchBucket")) {
                LOG.error("Bucket not found");
            } else {
                LOG.error("Failed to delete bucket " , ae);
            }
        } catch (SdkClientException se) {
            LOG.error("Failed to empty the bucket " , se);
        }
    }
}
