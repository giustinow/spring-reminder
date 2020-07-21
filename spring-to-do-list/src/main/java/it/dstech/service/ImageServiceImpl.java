package it.dstech.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import it.dstech.model.Image;
import it.dstech.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public Image saveFile(MultipartFile file) {
		// Normalize file name
				String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				try {
					// Check if the file's name contains invalid characters
					if (fileName.contains("..")) {
						return null;
					}
					Image dbFile = new Image(fileName, file.getContentType(), file.getBytes());
					return imageRepository.save(dbFile);
				} catch (IOException ex) {
					return null;
				}
	}

	@Override
	public Image retriveFile(Long fileId) {
		return imageRepository.findById(fileId).orElseThrow(() -> null); // se il risultato della ricerca è null, restituisce null, altrimenti restituisce il risultato
	}

}
