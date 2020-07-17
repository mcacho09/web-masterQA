package com.retailsbs.logistikapp.web.ctrl.upload;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	MultipartFile file;
	Long id_state;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Long getId_state() {
		return id_state;
	}

	public void setId_state(Long id_state) {
		this.id_state = id_state;
	}
	
}
