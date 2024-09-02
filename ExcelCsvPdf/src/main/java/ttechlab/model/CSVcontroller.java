package ttechlab.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class CSVcontroller {

	private static final Logger logger = LoggerFactory.getLogger(CSVcontroller.class);

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	private String Index() {
		return "index";
	}

	@PostMapping("/upload-csv-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println("hell");
		System.out.println("upload");
		try {
			logger.info(file.getOriginalFilename());
			employeeService.saveCsv(file);
			return ResponseEntity.ok("File uploaded successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload file: " + e.getMessage());
		}
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/database")
	public String getEmployeeList(Model model){
		model.addAttribute("employeeList", 	employeeService.getList());
		System.out.println(employeeService.getList());
		return "employeeList";
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/upload-image-file")
	public ResponseEntity<String> UploadImage(@RequestParam("file")MultipartFile file) {
		if(file.isEmpty()) {
			System.out.println("its empty");
		}
		
		
		try {
			employeeService.saveImage(file);
			return ResponseEntity.ok("uplaoded succsfully");
		}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("failed to uplaod the image"+e.getMessage());
		}
		
		
		
	}
	
	
}
