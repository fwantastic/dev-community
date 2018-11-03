package com.community.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.dev.persistence.FileUpload;

public interface FileRepository extends JpaRepository<FileUpload, Long> {

}
