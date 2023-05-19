package helpers;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.*;

public class FileReader {
    private static HSSFSheet sheet;
    private static final String RELATIVE_PATH = "./src/main/resources/xlsFileUnderTest/";

    public static void initializeInputStream(String fileName) {
        try (FileInputStream file = new FileInputStream(new File(RELATIVE_PATH + fileName))) {

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            sheet = workbook.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getColumnsCount() {
        int columnsCount = 0;
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                columnsCount++;

            }
        }
        return columnsCount;
    }

    public static List<String> getFileHeader() {
        List<String> fileColumnNames = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.iterator();
        if (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellType(CELL_TYPE_STRING);
                fileColumnNames.add(cell.getStringCellValue());
            }
        }
        return fileColumnNames;
    }

    public static int getRowsCount() {
        int rowsCount = 0;
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            rowsCount++;
        }
        return rowsCount;
    }
}
