package ttechlab.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

@Controller
public class ExcelController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/export-to-excel")
	public void exportToExcel(HttpServletResponse httpServletResponse)throws IOException{
		httpServletResponse.setContentType("application/octet-stream");
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime=dateFormat.format(new Date());
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=users_"+currentDateTime+".xlsx";
		httpServletResponse.setHeader(headerKey, headerValue);
		List<Employee>employeeList=employeeService.getList();
		employeeService.GenrateExcel(employeeList, httpServletResponse);
		
		
	}
	
	
	@PostMapping("/upload-excel-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println("upload");
		try {
			employeeService.saveExcel(file);
			return ResponseEntity.ok("File uploaded successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload file: " + e.getMessage());
		}
	}
	
	
	
	@PostMapping("/test")
	public ResponseEntity<String>operation(@RequestParam("file")MultipartFile file){
		
		try {
			employeeService.saveExcel(file);
			System.out.println("----");
			//load excelsheet
			InputStream inputStream=file.getInputStream();
			Workbook workbook=new XSSFWorkbook(inputStream);
			//get the sheet
			 Sheet sheet = workbook.getSheetAt(0);
			 
			 //asuming query is fire idenx 6
			List<String> queries=new ArrayList<>();
			java.util.Iterator<Row> iterator = sheet.iterator();
			while(iterator.hasNext()) {
				Row currentRow=iterator.next();
				Cell cell=currentRow.getCell(6);
				if(cell!=null) {
					String query=cell.getStringCellValue();
					queries.add(query);
				}
			}
			List<List<String>>queryResults=employeeService.executeQueriesAndGetResults(queries);
			queryResults.stream().forEach(e->{
				System.out.println("--------->"+e);
			});
			Workbook resultWorkbook = new XSSFWorkbook();
			Sheet resultSheet = resultWorkbook.createSheet("Query Results");
			int rowNum = 0;
			for (List<String> result : queryResults) {
			    Row row = resultSheet.createRow(rowNum++);
			    int cellNum = 0;
			    for (String value : result) {
			        Cell cell = row.createCell(cellNum++);
			        cell.setCellValue(value);
			    }
			}
	            FileOutputStream fileOut = new FileOutputStream("query_results.xlsx");
	            resultWorkbook.write(fileOut);
	            fileOut.close();

	            // Close workbooks and input stream
	            workbook.close();
	            resultWorkbook.close();
	            inputStream.close();
			
		}catch (Exception e) {
		e.printStackTrace();
		}
		return null;
		
	}


	
	
	
	
	

}
