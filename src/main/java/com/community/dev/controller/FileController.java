package com.community.dev.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.community.dev.persistence.FileUpload;

@Controller
@RequestMapping("/file")
public class FileController {

	@PostMapping
	@ResponseBody
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile sourceFile)
			throws IllegalStateException, IOException {

		FileUpload fileUpload = new FileUpload();

		File destinationFile = new File("D:\\test_two\\" + sourceFile.getOriginalFilename());

		sourceFile.transferTo(destinationFile);

		return ResponseEntity.ok().body("/image/" + 1);
	}

}
