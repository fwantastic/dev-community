package com.community.dev.service;

import com.community.dev.persistence.FileUpload;

public interface FileService {

	FileUpload save(FileUpload article);

	FileUpload findByArticleId(Long fileId);

}