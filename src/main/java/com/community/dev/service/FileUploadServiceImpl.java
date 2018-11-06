package com.community.dev.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.dev.persistence.FileUpload;
import com.community.dev.repository.FileUploadRepository;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
	private FileUploadRepository fileRepository;

	@Override
	public FileUpload save(FileUpload fileUpload) {

		LocalDateTime now = LocalDateTime.now();

		if (fileUpload.getCreateDatetime() == null) {
			fileUpload.setCreateDatetime(now);
		}

		fileUpload.setUpdateDatetime(now);

		return fileRepository.save(fileUpload);
	}

	@Override
	public FileUpload findByUploadId(Long fileUploadId) {
		return fileRepository.findOne(fileUploadId);
	}

}
