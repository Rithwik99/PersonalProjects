package ttechlab.model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class ExcelVo {
	
	
	
	public static void createCell(Row row,int columnCount,Object value,CellStyle cellStyle) {
		XSSFWorkbook workbook=new XSSFWorkbook();
		XSSFSheet sheet=workbook.createSheet("EmployeeList");
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Integer) {
			cell.setCellValue((Integer)value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean)value );
		}else {
			cell.setCellValue(String.valueOf(value));
		}
		cell.setCellStyle(cellStyle);
		
	}
}


//Cell cell=row.createCell(columnCount);
//if(value instanceof Integer) {
//	cell.setCellValue((Integer)value);
//}else if(value instanceof Boolean) {
//	cell.setCellValue((Boolean)value );
//}else if(value instanceof Long) {
//	cell.setCellValue(String.valueOf(value));
//}else {
//	cell.setCellValue((String)value);
//}
//cell.setCellStyle(cellStyle);
//
//}
