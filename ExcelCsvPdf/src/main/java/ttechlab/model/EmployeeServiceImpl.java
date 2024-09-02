package ttechlab.model;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.*;
import org.apache.batik.apps.svgbrowser.Application;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Value("${excel.file.location}")
	private String uploadPath;
	@Value("${image.file.location}")
	private String uploadImagePath;

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private ImageEntityDao imageEntityDao;

	@Override
	public List<Employee> getList() {
		// TODO Auto-generated method stub
		return employeeDao.findAll();
	}

	@Override
	public void saveCsv(MultipartFile file) throws IOException {
		System.out.println(file.getOriginalFilename());
		if (file.isEmpty()) {
			throw new IllegalArgumentException("please upload csv file");
		}
		System.out.println("entered");
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			CsvToBean<Employee> CsvToBean = new CsvToBeanBuilder<Employee>(bufferedReader).withType(Employee.class)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<Employee> employeeList = CsvToBean.parse();
			employeeDao.saveAll(employeeList);

		}

	}

	

	@Override
	public void GenratePdf(List<Employee> employeeList, HttpServletResponse httpServletResponse)
			throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
		document.open();

		Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTitle.setSize(20);

		Paragraph paragraph = new Paragraph("List of employee", fontTitle);
		paragraph.setAlignment(paragraph.ALIGN_CENTER);

		document.add(paragraph);
		PdfPTable pdfTable = new PdfPTable(5);
		pdfTable.setWidthPercentage(100);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(CMYKColor.YELLOW);
		cell.setPadding(5);
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setColor(CMYKColor.BLACK);
		cell.setPhrase(new Phrase("ID", font));
		pdfTable.addCell(cell);
		cell.setPhrase(new Phrase("firstname", font));
		pdfTable.addCell(cell);
		cell.setPhrase(new Phrase("lastname", font));
		pdfTable.addCell(cell);
		cell.setPhrase(new Phrase("email", font));
		pdfTable.addCell(cell);
		cell.setPhrase(new Phrase("mobileNo", font));
		pdfTable.addCell(cell);
		for (Employee employee : employeeList) {
			pdfTable.addCell(String.valueOf(employee.getId()));
			pdfTable.addCell(employee.getFirstname());
			pdfTable.addCell(employee.getLastname());
			pdfTable.addCell(employee.getEmail());
			pdfTable.addCell(String.valueOf(employee.getPhoneNo()));
		}
		document.add(pdfTable);
		document.close();
	}

	@Override
	public void saveem(Employee employee) {
		employeeDao.save(employee);

	}

	@Override
	public void GenrateExcel(List<Employee> employeeList, HttpServletResponse httpServletResponse)
			throws DocumentException, IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("EmployeeList");
		XSSFRow row = sheet.createRow(0);
		CellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStyle.setFont(font);
		ExcelVo excelVo = new ExcelVo();
		excelVo.createCell(row, 0, "ID", cellStyle);
		excelVo.createCell(row, 1, "First Name", cellStyle);
		excelVo.createCell(row, 2, "last Name", cellStyle);
		excelVo.createCell(row, 3, "E-mail", cellStyle);
		excelVo.createCell(row, 4, "phoneNo", cellStyle);

		int rowCount = 1;
		CellStyle cellStyle2 = workbook.createCellStyle();
		XSSFFont xssfFont = workbook.createFont();
		xssfFont.setFontHeight(14);
		cellStyle2.setFont(font);

		for (Employee employee : employeeList) {
			System.out.println("shh" + employeeList);
			Row row2 = sheet.createRow(rowCount++);
//			int columnCount = 0;
//			excelVo.createCell(row2, columnCount, employee.getId(), cellStyle2);
//			excelVo.createCell(row2, columnCount, employee.getFirstname(), cellStyle2);
//			excelVo.createCell(row2, columnCount, employee.getLastname(), cellStyle2);
//			excelVo.createCell(row2, columnCount, employee.getEmail(), cellStyle2);
//			excelVo.createCell(row2, columnCount, employee.getPhoneNo(), cellStyle2);

			row2.createCell(0).setCellValue(employee.getId());
			row2.createCell(1).setCellValue(employee.getFirstname());
			row2.createCell(2).setCellValue(employee.getLastname());
			row2.createCell(3).setCellValue(employee.getEmail());
			row2.createCell(4).setCellValue(employee.getPhoneNo());
		}
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	@Override
	public void saveImage(MultipartFile file) throws Exception {
		Path imagePath = new File(uploadImagePath + File.separator + file.getOriginalFilename()).toPath();
		Files.copy(file.getInputStream(), imagePath);
		ImageEntity imageEntity = new ImageEntity();
//		imageEntity.setName(file.getOriginalFilename());
//		imageEntity.setImageData(file.getBytes());
		imageEntityDao.save(imageEntity);
	}

	@Override
	public void saveExcel(MultipartFile file) throws Exception {
			if (file.isEmpty()) {
				throw new IllegalArgumentException("uplaod excel sheet");
			}
//			String filepath=ApplicationProperties.getValue("excel.file.location");
			Path destinationPath = new File(uploadPath + File.separator + file.getOriginalFilename()).toPath();
			System.out.println("####" + destinationPath);
			Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
				Sheet sheet = workbook.getSheetAt(0);
				Iterator<Row> rows = sheet.iterator();

				
				int rowNumber = 0;
				while (rows.hasNext()) {
					Row currentRows = rows.next();

					// skip header
					if (rowNumber == 0) {
						rowNumber++;
						continue;
					}

					Iterator<Cell> cellsInRow = currentRows.iterator();
					Employee employee = new Employee();
					int cellIndex = 0;

					while (cellsInRow.hasNext()) {
						Cell currentCells = cellsInRow.next();
						switch (cellIndex) {
						case 0:
							employee.setId((long) currentCells.getNumericCellValue());
							break;
						case 1:
							employee.setFirstname(currentCells.getStringCellValue());
							break;
						case 2:
							employee.setLastname(currentCells.getStringCellValue());
							break;
						case 3:
							employee.setEmail(currentCells.getStringCellValue());
							break;
						case 4:
							employee.setPhoneNo((long) currentCells.getNumericCellValue());
							break;
						default:
							break;
						}
						cellIndex++;
					}
					if (employeeDao.existsByEmail(employee.getEmail())) {
						System.out.println("it exist and ignoring it" + employee.getEmail() + employee.getFirstname());
						

					} else {
						employeeDao.save(employee);
					}

				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}

		}
	private static final String DB_URL = "jdbc:mysql://localhost:3306/PDF_CSV_XLS_DB";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "Welcome1";

	@Override
	public List<List<String>> executeQueriesAndGetResults(List<String> queries) throws SQLException {
		System.out.println("@@@");
		List<List<String>>queryResult=new ArrayList<>();
		Connection connection = null;
		try{
			connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
			for(String query:queries) {
				try(java.sql.Statement statement=connection.createStatement();
						ResultSet resultSet=statement.executeQuery(query)){
					   ResultSetMetaData metaData = resultSet.getMetaData();
		                int columnCount = metaData.getColumnCount();
		               System.out.println("@"+columnCount);
		                
					while(resultSet.next()) {
						 List<String> row = new ArrayList<>();
						 for(int i=1;i<columnCount;i++) {
							 row.add(resultSet.getString(i));
						 }
						 queryResult.add(row);
						
					}
					
				}catch (SQLException e) {
					e.printStackTrace();//query
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();//connection
		}
		connection.close();
		return queryResult;
	}
		
	}


