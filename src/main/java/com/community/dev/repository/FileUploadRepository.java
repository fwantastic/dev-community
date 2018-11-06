package com.community.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.dev.persistence.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

}
