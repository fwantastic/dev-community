package com.community.dev.controller;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.community.dev.persistence.FileUpload;
import com.community.dev.service.FileService;

@Controller
@RequestMapping("/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile sourceFile)
			throws IllegalStateException, IOException {

		FileUpload fileUpload = new FileUpload();
		fileUpload.setOriginalFileName(sourceFile.getOriginalFilename());
		fileUpload.setContentType(sourceFile.getContentType());
		fileUpload.setFileSize(sourceFile.getSize());
		// fileUpload.setSaveFileName(saveFileName);
		// fileUpload.setFilePath(filePath);
		fileUpload.setIsActive(Boolean.TRUE);

		fileUpload = fileService.save(fileUpload);

		String extension = FilenameUtils.getExtension(sourceFile.getOriginalFilename());

		File destinationFile = new File("C:\\ryan\\test\\" + fileUpload.getFileUploadId() + "." + extension);

		sourceFile.transferTo(destinationFile);

		return ResponseEntity.ok().body("/image/" + 1);
	}

}
