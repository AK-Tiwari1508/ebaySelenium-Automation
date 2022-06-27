package ReadExcelData;

import java.io.File;

import java.io.FileInputStream;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@SuppressWarnings("unused")
public class ReadExcel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String path = System.getProperty("user.dir");
		System.out.println(path);
		File src = new File(path +"/TestData/LoginData.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
		
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet1 = wb.getSheetAt(0);
		
		int rowCount = sheet1.getLastRowNum();
		
		System.out.println(rowCount);
		
		for(int i=0; i<rowCount; i++) {
			
			String data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
			System.out.println(data0);
		}
		
		
		
		 
		
		
		
		

	}

}
