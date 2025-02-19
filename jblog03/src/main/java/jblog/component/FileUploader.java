package jblog.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploader {
	private final String SAVE_PATH = "/Users/kilho/jblog-uploads";
	private final String URL = "/assets/upload_images";
	
	public String restore(MultipartFile file) throws RuntimeException{
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if(!uploadDirectory.exists() && !uploadDirectory.mkdirs()) {
				return null;
			}
			
			if(file.isEmpty()){
				return null;
			}
			
			String originFilename = Optional.ofNullable(file.getOriginalFilename()).orElse("");
			String extName = originFilename.substring(originFilename.lastIndexOf('.') + 1); // 확장자를 제외한 이름 저장
			String saveFilename = generateSaveFilename(extName);
			long fileSize = file.getSize();
			
		
			byte[] data = file.getBytes();
			
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(data);
			os.close();
			return URL + "/" + saveFilename;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private String generateSaveFilename(String extName) {
		Calendar calendar = Calendar.getInstance();
		
		return "" 
			+ calendar.get(Calendar.YEAR)
			+ calendar.get(Calendar.MONTH)
			+ calendar.get(Calendar.DATE)
			+ calendar.get(Calendar.HOUR)
			+ calendar.get(Calendar.MINUTE)
			+ calendar.get(Calendar.SECOND)
			+ calendar.get(Calendar.MILLISECOND)
			+ ("." + extName);
	}
}
