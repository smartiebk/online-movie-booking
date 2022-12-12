package com.ombp.cloud.app.ui.cloudutils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.regions.Regions;

import software.amazon.awssdk.regions.Region;

@Component
public class CloudProperties {

	public static String AWS_ACCESS_KEY;
	
	public static String AWS_ACCESS_PASSWORD;
	
	public static String S3_BUCKET_NAME;
	
	public static Regions regions = Regions.AP_SOUTH_1;
	
	public static Region region = Region.AP_SOUTH_1;
	
	public static Long PRE_SIGNED_LINK_EXPIRY_FOR_SCAN;
	
	public static Integer LINK_VALIDITY_IN_DAYS;
	

	public static Regions getRegions() {
		return regions;
	}

	public static Region getRegion() {
		return region;
	}

	@Value("${cloud.prop.aws.accesskey}")
	public void setAwsAccessKey(String awsAccessKey) {
		CloudProperties.AWS_ACCESS_KEY = awsAccessKey;
	}

	@Value("${cloud.prop.aws.password}")
	public void setAwsAccessPassword(String awsAccessPassword) {
		CloudProperties.AWS_ACCESS_PASSWORD = awsAccessPassword;
	}

	@Value("${cloud.prop.s3.bucket.prefix}")
	public void setS3BucketName(String bucketPrefix) {
		CloudProperties.S3_BUCKET_NAME = bucketPrefix;
	}

	public static Long getPreSignedLinkExpiry() {
		PRE_SIGNED_LINK_EXPIRY_FOR_SCAN =1000 * 60 * 60 * 24 * Long.valueOf(CloudProperties.LINK_VALIDITY_IN_DAYS); 
		return  PRE_SIGNED_LINK_EXPIRY_FOR_SCAN;
	}

	@Value("${cloud.prop.s3.link.validity.in.days}")
	public void setLinkValidityInDays(String linkValidityInDays) {
		CloudProperties.LINK_VALIDITY_IN_DAYS = Integer.valueOf(linkValidityInDays);
	}
	
}
