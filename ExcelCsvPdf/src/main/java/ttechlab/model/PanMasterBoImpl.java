package ttechlab.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PanMasterBoImpl implements PanMasterBo{

	@Value("${excel.file.location}")
	private String uploadPath;
	
	
	@Override
	public boolean TransferFile(MultipartFile file, String masterName) throws IOException {
		Path destinationPath = new File(uploadPath + File.separator + file.getOriginalFilename()).toPath();
		System.out.println("####" + destinationPath);
		Files.copy(file.getInputStream(), destinationPath);
		
		return false;
	}

}
