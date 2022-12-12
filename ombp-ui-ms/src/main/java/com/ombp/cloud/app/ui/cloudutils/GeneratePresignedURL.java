package com.ombp.cloud.app.ui.cloudutils;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public class GeneratePresignedURL implements AllCreds {

	private static Logger LOG = LoggerFactory.getLogger(GeneratePresignedURL.class);
	
	public static Map<String, Object> getURL(String bucketName, String objectKey, Long expiry) {
		
		Map<String, Object> data =  new HashMap<String, Object>();
		
		String generatedURL = "";
        try {
        	BasicAWSCredentials awsCreds = new BasicAWSCredentials(CloudProperties.AWS_ACCESS_KEY, CloudProperties.AWS_ACCESS_PASSWORD);
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(CloudProperties.getRegions())
                    .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                    .build();

            if(expiry==null) 
            {
            	expiry = CloudProperties.getPreSignedLinkExpiry();
            }
            
            // Set the presigned URL to expire after one hour.
            java.util.Date expiration = new java.util.Date();
            long expTimeMillis = expiration.getTime();
            expTimeMillis += expiry;
            expiration.setTime(expTimeMillis);
            // Generate the presigned URL.
            LOG.info("Generating pre-signed URL.");
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(bucketName, objectKey)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            URL url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);

            generatedURL = url.toString();
            
            data.put("URL", generatedURL);
            data.put("expiry", expiration);
            
            LOG.info("Pre-Signed URL: " + url.toString());
        } catch (AmazonServiceException e) {
        	LOG.error("", e);
        } catch (SdkClientException e) {
        	LOG.error("", e);
        }
        
        return data;
	}

}
