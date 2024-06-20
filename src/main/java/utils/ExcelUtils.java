package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {
	
	 // Method to retrieve test data from an Excel file
	public static Object[][] getTestData(String filePath, String sheetName) throws IOException, InvalidFormatException {
        FileInputStream inputStream = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum(); // Get the last row number (0-based index)
        int colCount = sheet.getRow(0).getLastCellNum();// Get the number of columns in the first row

        // Initialize a 2D array to store data from Excel
        Object[][] data = new Object[rowCount][colCount];

        // Iterate through each row (skipping the header row)
        for (int i = 0; i < rowCount; i++) {
            Row row = sheet.getRow(i + 1); // Skip header row (starts from index 1)

         // Iterate through each cell in the current row
            for (int j = 0; j < colCount; j++) {
                Cell cell = row.getCell(j);// Get the cell at index j
             // Check if the cell is not null
                if (cell != null) {
                	// Determine the cell type and process accordingly
                	switch (cell.getCellType()) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();// Read string value
                        break;
                    case NUMERIC:
                    	// Check if numeric cell contains date and convert to string
                        cell.setCellType(CellType.STRING);
                        data[i][j] = cell.getStringCellValue();// Read numeric value as string
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();// Read boolean value
                        break;
                    case BLANK:
                        data[i][j] = "";// Handle blank cells
                        break;
                    default:
                        data[i][j] = "";// Default case for any other cell types
                        break;
                    }
                } else {
                    data[i][j] = ""; // Set empty string if cell is null
                }
            }
        }

        workbook.close();// Close the workbook
        inputStream.close();// Close the input stream

        return data;// Return the retrieved data from Excel as a 2D object array
    }
}
