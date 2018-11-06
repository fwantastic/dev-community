package com.community.dev.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.community.dev.dao.FileDao;
import com.community.dev.persistence.FileUpload;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	@Override
	public PutObjectResult saveFile(MultipartFile multipartFile, FileUpload fileUpload) throws IOException {
		return fileDao.saveFile(multipartFile, fileUpload.getSaveFileName());
	}

	@Override
	public ByteArrayOutputStream downloadFile(String fileName) {
		return fileDao.downloadFile(fileName);
	}

}
