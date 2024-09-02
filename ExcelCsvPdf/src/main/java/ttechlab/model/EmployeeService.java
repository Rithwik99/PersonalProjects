package ttechlab.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

public interface EmployeeService {

	
	void saveCsv(MultipartFile file)throws IOException;
	List<Employee>getList();
	void saveem(Employee employee);
	public void GenratePdf(List<Employee>employeeList,HttpServletResponse httpServletResponse) throws DocumentException, IOException;
	public void GenrateExcel(List<Employee>employeeList,HttpServletResponse httpServletResponse)throws DocumentException, IOException;
	void saveExcel(MultipartFile file)throws Exception;
	void saveImage(MultipartFile file)throws Exception;
	List<List<String>>executeQueriesAndGetResults(List<String>queries)throws SQLException;
	
}
