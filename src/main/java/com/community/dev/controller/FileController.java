package com.community.dev.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.community.dev.persistence.FileUpload;
import com.community.dev.service.FileService;
import com.community.dev.service.FileUploadService;
import com.community.dev.util.MediaUtility;

@Controller
@RequestMapping("/files")
public class FileController {

	private static final String FILES_URL = "/files/";
	private static final String S3_ARTICLE_FILES = "article_files/";
	private static final String UNDERSCORE = "_";

	@Autowired
	private FileService fileService;

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile sourceFile)
			throws IllegalStateException, IOException {

		FileUpload fileUpload = new FileUpload();
		fileUpload.setOriginalFileName(sourceFile.getOriginalFilename());
		fileUpload.setContentType(sourceFile.getContentType());
		fileUpload.setFileSize(sourceFile.getSize());
		fileUpload.setIsActive(Boolean.TRUE);

		fileUpload = fileUploadService.save(fileUpload);

		fileUpload.setSaveFileName(
				S3_ARTICLE_FILES + fileUpload.getFileUploadId() + UNDERSCORE + fileUpload.getOriginalFileName());

		fileUpload = fileUploadService.save(fileUpload);

		PutObjectResult result = fileService.saveFile(sourceFile, fileUpload);

		return ResponseEntity.ok().body(FILES_URL + fileUpload.getFileUploadId());
	}

	@GetMapping("/{fileUploadId}")
	public ResponseEntity<?> form(@PathVariable Long fileUploadId, Model model) {

		FileUpload fileUpload = fileUploadService.findByUploadId(fileUploadId);

		if (fileUpload == null) {
			return ResponseEntity.notFound().build();
		}

		ByteArrayOutputStream baos = fileService.downloadFile(fileUpload.getSaveFileName());

		HttpHeaders headers = new HttpHeaders();

		String fileName = fileUpload.getOriginalFileName();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"");

		if (MediaUtility.containsImageMediaType(fileUpload.getContentType())) {
			headers.setContentType(MediaType.valueOf(fileUpload.getContentType()));
		} else {
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		}

		return ResponseEntity.ok().headers(headers).body(baos.toByteArray());
	}

}
