package com.community.dev.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.community.dev.persistence.FileUpload;

public interface FileService {

	PutObjectResult saveFile(MultipartFile multipartFile, FileUpload fileUpload) throws IOException;

	ByteArrayOutputStream downloadFile(String fileName);

}