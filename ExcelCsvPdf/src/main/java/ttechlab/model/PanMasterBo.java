package ttechlab.model;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface PanMasterBo {
	
	public boolean TransferFile(MultipartFile file,String masterName) throws IOException;

}
