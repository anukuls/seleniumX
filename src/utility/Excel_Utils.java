package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utils {

	private XSSFWorkbook ExcelWBook;
	private static XSSFSheet ExcelWSheet;
	private String Path;
	private Hashtable testdata = new Hashtable();
	

	// Default constructor
	public Excel_Utils() throws Exception {
		try {
			Path = Project_Constants.testdata;
			String SheetName = Project_Constants.sheet;
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}
	
	// Constructor to connect to the Excel with sheetname and Path
	public Excel_Utils(String Path, String SheetName) throws Exception {

		try {
			// Open the Excel file
			Path = Path;
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method is to get the rowcount of the excel.
	public int excel_get_rows() throws Exception {

		try {
			return ExcelWSheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			throw (e);
		}
	}

	// This method to get the data from a cell.
	public String getCellData(Object obj, String Field) throws Exception {
		
		String TC_ID = obj.getClass().getSimpleName();

		try {
			int col_id = 0;
			int row_id = 0;
			
			String testdata = "";
			Row header = ExcelWSheet.getRow(0);
			Iterator<Cell> cellIterator = header.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellvalue = cell.getStringCellValue();
				if (cellvalue.equals(Field)) {
					col_id = cell.getColumnIndex();
					break;
				}
			}

			Iterator<Row> rowIterator = ExcelWSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = row.getCell(0);
				if (cell.getStringCellValue().equals(TC_ID)) {
					row_id = cell.getRowIndex();
					break;
				}
			}

			testdata = ExcelWSheet.getRow(row_id).getCell(col_id)
					.getStringCellValue();
			return testdata;

		} catch (IllegalStateException e) {
			try {
				int col_id = 0;
				int row_id = 0;
				Row header = ExcelWSheet.getRow(0);
				Iterator<Cell> cellIterator = header.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					String cellvalue = cell.getStringCellValue();
					if (cellvalue.equals(Field)) {
						col_id = cell.getColumnIndex();
						break;
					}
				}

				Iterator<Row> rowIterator = ExcelWSheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					Cell cell = row.getCell(0);
					if (cell.getStringCellValue().equals(TC_ID)) {
						row_id = cell.getRowIndex();
					}
				}

				double testdataasdouble = ExcelWSheet.getRow(row_id)
						.getCell(col_id).getNumericCellValue();
				return String.valueOf(testdataasdouble);

			} catch (Exception e2) {
				System.out.println(e2);
				return String.valueOf(0.0);
			}
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	// This method to set the data of a cell as string
	public void setCellData(String TC_ID, String Field, String data) throws Exception {
		try {
			int col_id = 0;
			int row_id = 0;
			FileOutputStream excel_out =new FileOutputStream(Path);
			Row header = ExcelWSheet.getRow(0);
			Iterator<Cell> cellIterator = header.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellvalue = cell.getStringCellValue();
				if (cellvalue.equals(Field)) {
					col_id = cell.getColumnIndex();
					break;
				}
			}

			Iterator<Row> rowIterator = ExcelWSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = row.getCell(0);
				if (cell.getStringCellValue().equals(TC_ID)) {
					row_id = cell.getRowIndex();
					break;
				}
			}

			 ExcelWSheet.getRow(row_id).getCell(col_id)
					.setCellValue(data);
			 ExcelWBook.write(excel_out);

		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Overloaded method to set cell data as double
	public void setCellData(String TC_ID, String Field, double data) throws Exception {
		try {
			int col_id = 0;
			int row_id = 0;
			FileOutputStream excel_out =new FileOutputStream(Path);
			Row header = ExcelWSheet.getRow(0);
			Iterator<Cell> cellIterator = header.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellvalue = cell.getStringCellValue();
				if (cellvalue.equals(Field)) {
					col_id = cell.getColumnIndex();
					break;
				}
			}

			Iterator<Row> rowIterator = ExcelWSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = row.getCell(0);
				if (cell.getStringCellValue().equals(TC_ID)) {
					row_id = cell.getRowIndex();
					break;
				}
			}

			 ExcelWSheet.getRow(row_id).getCell(col_id)
					.setCellValue(data);
			 ExcelWBook.write(excel_out);

		}catch (Exception e) {
			System.out.println(e);
		}
	}

	// This method return hash if test data for a Test Case ID
	public Hashtable getTestCaseRow(String TC_ID) throws Exception{
		try{
			int row_id=0;
			Row header = ExcelWSheet.getRow(0);
			Iterator<Row> rowIterator = ExcelWSheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Cell cell = row.getCell(0);
				if (cell.getStringCellValue().equals(TC_ID)) {
					row_id = cell.getRowIndex();
					break;
				}
			}
			Row tc_data = ExcelWSheet.getRow(row_id);
			Iterator<Cell> headerIterator = header.cellIterator();
			Iterator<Cell> tcDataIterator = tc_data.cellIterator();
			testdata.clear();
			String value = "";
			while(headerIterator.hasNext()){
				String key = headerIterator.next().getStringCellValue();
				Cell value_cell = tcDataIterator.next();
				try{
					value = value_cell.getStringCellValue();
				}catch(IllegalStateException e){
					value = String.valueOf(value_cell.getNumericCellValue());
				}
				catch(Exception e){
					System.out.println(e);
				}
				testdata.put(key, value);
			}
			return testdata;
		}catch(Exception e){
			return testdata;
		}
		
	}
}