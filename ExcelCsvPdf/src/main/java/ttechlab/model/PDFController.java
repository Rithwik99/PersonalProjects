package ttechlab.model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PDFController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/export-to-pdf")
	public void generatePDFFile(HttpServletResponse httpServletResponse) throws DocumentException, IOException {
		httpServletResponse.setContentType("application/pdf");
		DateFormat dateFormat=new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime=dateFormat.format(new Date());
		String headerkey="Content-Disposition";
		String headervalue="attachment; filename=employee"+currentDateTime+".pdf";
		httpServletResponse.setHeader(headerkey, headervalue);
		List<Employee>employeeList=employeeService.getList();
		employeeService.GenratePdf(employeeList, httpServletResponse);
		
	}

	
	
}
