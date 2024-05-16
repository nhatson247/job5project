package com.fpt.job5project.service.impl;

import com.fpt.job5project.exception.AppException;
import com.fpt.job5project.exception.ErrorCode;
import com.fpt.job5project.service.IStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Stream;


@Service
public class StorageServiceImpl implements IStorageService {
	private final Path storageFolder = Paths.get("src/main/resources/static/uploads");
	
	public StorageServiceImpl() {
		try {
			Files.createDirectories(storageFolder);
		} catch (IOException e) {
			throw new RuntimeException("Cannot initialize storage", e);
		}
	}
	
	private boolean isAcceptedFile(MultipartFile file) {
		//Install FileNameUtils
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] {"png", "jpg", "jpeg", "bmp", "docx", "pdf"}).contains(fileExtension.trim().toLowerCase());
	}
	
	@Override
	public String storeFile(MultipartFile file) {
		try {
			System.out.println(file != null);
			if (file == null) {
				throw new AppException(ErrorCode.IMAGE_NULL);
			}
			//check file is image?
			if (!isAcceptedFile(file)) {
				throw new AppException(ErrorCode.FILE_DOES_NOT_ACEPPT);
			}
			//must be <= 5Mb
			System.out.println("size :" + file.getSize());
			float fileSizeInMegabytes = file.getSize()/1000000.0f;
			System.out.println("fileSizeInMegabytes :" + fileSizeInMegabytes);

			if (fileSizeInMegabytes > 5.0f) {
				throw new AppException(ErrorCode.FILE_CAPATICY_TOO_BIG);
			}

			//File must be rename
			String currentDate = new SimpleDateFormat("yyyyMMdd-hhmmss").format(new Date()); // Lấy ngày hiện tại
			String generatedFileName = currentDate + "-" + file.getOriginalFilename();
			Path destinationFilePath = this.storageFolder.resolve(Paths.get(generatedFileName)).normalize().toAbsolutePath();
			if (!destinationFilePath.getParent().equals(this.storageFolder.toAbsolutePath())) {
				throw new AppException(ErrorCode.STORE_FILE_WRONG_PLACE);
			}
			
			try (InputStream inputStream = file.getInputStream()){
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
			
			return generatedFileName;
		} catch (IOException e) {
			throw new AppException(ErrorCode.IMAGE_NULL);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			//list all files in storage
			return Files.walk(this.storageFolder, 1)
					.filter(path -> !path.equals(this.storageFolder) && !path.toString().contains("._"))
					.map(this.storageFolder::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load stored files", e);
		}
	}

	@Override
	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			}
			else {
				throw new AppException(ErrorCode.COULD_NOT_FIND_FILE);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new AppException(ErrorCode.COULD_NOT_READ_FILE);
		}
	}

	@Override
	public void deleteAllFiles() {
		// TODO Auto-generated method stub

	}

}
