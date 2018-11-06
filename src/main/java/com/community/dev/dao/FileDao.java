package com.community.dev.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;

public interface FileDao {

	ByteArrayOutputStream downloadFile(String fileName);

	PutObjectResult saveFile(MultipartFile multipartFile, String fileName) throws IOException;

}