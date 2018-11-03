package com.community.dev.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.community.dev.persistence.FileUpload;
import com.community.dev.repository.FileRepository;

public class FileServiceImpl implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public FileUpload save(FileUpload article) {
		return fileRepository.save(article);
	}

	@Override
	public FileUpload findByArticleId(Long fileId) {
		return fileRepository.findOne(fileId);
	}

}
