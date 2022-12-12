package com.ombp.cloud.app.ui.cloudutils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zeroturnaround.zip.ZipUtil;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.ombp.cloud.app.ui.constants.UIConstants;

public class DownloadFileFromCloud implements AllCreds {

	private static Logger LOG = LoggerFactory.getLogger(DownloadFileFromCloud.class);
	
	public static synchronized boolean downloadScanFilesAndUnzip(String bucketName, String objectKey) {
        boolean done = true;
        TransferManager transferManager = null;
        String outputFileName = UIConstants.DOWNLOADED_SCANS_DIRECTORY + objectKey;
		try {
			BasicAWSCredentials awsCreds = new BasicAWSCredentials(CloudProperties.AWS_ACCESS_KEY, CloudProperties.AWS_ACCESS_PASSWORD);
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(CloudProperties.getRegions())
					.build();

			transferManager = TransferManagerBuilder.standard().withS3Client(s3Client).build();
			File downloadedZipFile = new File(outputFileName);
			
			Download download = transferManager.download(bucketName, objectKey, downloadedZipFile);
			
			download.waitForCompletion();
			
			try {
				FileUtils.copyFile(downloadedZipFile, new File(UIConstants.DOWNLOADED_SCANS_DIRECTORY + "SCAN_" +objectKey+".zip"));
			} catch (IOException e) {
				LOG.error("", e);
			}
			
			ZipUtil.explode(downloadedZipFile);
			
			LOG.info("File downloaded from Cloud successfully...::Object Key::" + objectKey);
        } catch (AmazonServiceException e) {
        	LOG.error("", e);
        	done = false;
        } catch (SdkClientException e) {
        	LOG.error("", e);
        	done = false;
        } catch (AmazonClientException e) {
        	LOG.error("", e);
        	done = false;
		} catch (InterruptedException e) {
			LOG.error("", e);
        	done = false;
		}
		finally {
			if(transferManager!=null) 
			{
				transferManager.shutdownNow();	
			}
		}
		return done;
    }

}
