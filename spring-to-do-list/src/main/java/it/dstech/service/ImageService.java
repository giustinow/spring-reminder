package it.dstech.service;

import org.springframework.web.multipart.MultipartFile;

import it.dstech.model.Image;

public interface ImageService {
	
	public Image saveFile(MultipartFile file); // upload

	public Image retriveFile(Long fileId); // download

}
