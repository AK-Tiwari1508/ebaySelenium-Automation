package ReadExcelData;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataConfig {

	XSSFWorkbook wb;

	XSSFSheet sheet1;

	public ExcelDataConfig(String ExcelPath) {

		try {

			String path = System.getProperty("user.dir");
			File src = new File(path + ExcelPath);

			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public String getData(int SheetNumber, int row, int column) {

		sheet1 = wb.getSheetAt(SheetNumber);

		String data = sheet1.getRow(row).getCell(column).getStringCellValue();

		return data;
	}

	public int getRowCount(int sheetIndex) {

		int row = wb.getSheetAt(sheetIndex).getLastRowNum();

		row = row + 1;

		return row;
	}
 
}
