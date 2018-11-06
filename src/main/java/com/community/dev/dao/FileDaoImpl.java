package com.community.dev.dao;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;

@Repository
public class FileDaoImpl implements FileDao {

	private static final Logger logger = LoggerFactory.getLogger(FileDaoImpl.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Override
	public PutObjectResult saveFile(MultipartFile multipartFile, String fileName) throws IOException {
		File file = convertMultiPartToFile(multipartFile);

		PutObjectResult result = s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));

		if (file.exists()) {
			file.delete();
		}

		return result;
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@Override
	public ByteArrayOutputStream downloadFile(String fileName) {
		try {
			S3Object s3Object = s3Client.getObject(new GetObjectRequest(bucketName, fileName));

			InputStream is = s3Object.getObjectContent();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len;
			byte[] buffer = new byte[4096];
			while ((len = is.read(buffer, 0, buffer.length)) != -1) {
				baos.write(buffer, 0, len);
			}

			return baos;
		} catch (IOException ioe) {
			logger.error("IOException: " + ioe.getMessage());
		} catch (AmazonServiceException ase) {
			logger.error("sCaught an AmazonServiceException from GET requests, rejected reasons:");
			logger.error("Error Message:    " + ase.getMessage());
			logger.error("HTTP Status Code: " + ase.getStatusCode());
			logger.error("AWS Error Code:   " + ase.getErrorCode());
			logger.error("Error Type:       " + ase.getErrorType());
			logger.error("Request ID:       " + ase.getRequestId());
			throw ase;
		} catch (AmazonClientException ace) {
			logger.error("Caught an AmazonClientException: ");
			logger.error("Error Message: " + ace.getMessage());
			throw ace;
		}

		return null;
	}

}
