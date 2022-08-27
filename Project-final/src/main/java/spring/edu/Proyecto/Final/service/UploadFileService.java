package spring.edu.Proyecto.Final.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {
	private String folder="images//";

	private static final String DIRECTORY = "src/main/resources/static/uploads";

	public String save(MultipartFile image) {
		try {
			String photoName = image.getOriginalFilename();
			Path photoPath = Paths.get(DIRECTORY, photoName).toAbsolutePath();
			Files.copy(image.getInputStream(), photoPath, StandardCopyOption.REPLACE_EXISTING);
			return photoName;
		} catch (IOException e) {
			throw new IllegalArgumentException("Error saving image");
		}
	}
	public void deleteImage(String name) {
		String route="images//";
		File file= new File(route+name);
		file.delete();
	}

}
