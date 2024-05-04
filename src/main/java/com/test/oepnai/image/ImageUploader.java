package com.test.oepnai.image;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageUploader {

	public static final String SYSTEM_PATH = System.getProperty("user.dir");
	public static final String SLASH = File.separator;

	private final String url;
	private final String uploadDirPath;

	public ImageUploader(
		@Value("${image.upload.url}")
		final String url,
		@Value("${image.upload.directory}")
		final String uploadDirPath
	) {
		this.url = url;
		this.uploadDirPath = uploadDirPath;
	}

	public String upload(final MultipartFile image) {
		final String fileSavePath = SYSTEM_PATH + SLASH + uploadDirPath;
		makeDirectory(fileSavePath);

		final String saveFileName = parseSaveFileName(image);
		final File uploadPath = new File(fileSavePath, saveFileName);

		transferFile(image, uploadPath);
		return url + File.separator + uploadDirPath + File.separator + saveFileName;
	}

	private void makeDirectory(final String fileSavePath) {
		final File directory = new File(fileSavePath);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	private String parseSaveFileName(final MultipartFile image) {
		final String imageName = image.getOriginalFilename();
		final String extension = StringUtils.getFilenameExtension(imageName);
		final String fileBaseName = UUID.randomUUID().toString().substring(0, 8);

		return fileBaseName + "_" + System.currentTimeMillis() + "." + extension;
	}

	private void transferFile(final MultipartFile image, final File uploadPath) {
		try {
			image.transferTo(uploadPath);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
