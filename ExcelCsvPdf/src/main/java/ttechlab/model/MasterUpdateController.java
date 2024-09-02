package ttechlab.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MasterUpdateController {
	@Value("${excel.file.location}")
	private String uploadPath;
	
	@Autowired
	PanMasterBo panMasterBo; 

	
	@GetMapping()
	public ResponseEntity<String> handleUploadFile(MultipartFile file,HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object>model=new HashMap<String, Object>();
		String masterName=request.getParameter("mastername");
		String fileType=null;
		if(masterName.equalsIgnoreCase("mastername")) {
			fileType="csv";
		}else {
			fileType="xlsx";
		}
		Boolean isvalidateFailed=false;
		
		Boolean isTransferSucess=panMasterBo.TransferFile(file, masterName);
		
		return null;		
	}
}
