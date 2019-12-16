/*
 * Get already creating document '.xlsx'.
 * Put value to choosen cells.
 * Save document to new file.
 * @author Leshjev Ivan
 */
package poi_xssf_read_write_putValue;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XSSFDocument {
    
    private Workbook wb = null;
    
    public XSSFDocument(FileInputStream stream) throws IOException {
        wb = WorkbookFactory.create(stream);
    }
    
    public void putValue(int indexSheet, int indexRow, int indexColumn, String value) {
        Sheet sheet = wb.getSheetAt(indexSheet);
        Row row = sheet.getRow(indexRow);
        Cell cell = row.getCell(indexColumn);
        cell.setCellValue(value);
    }
    
    public void putValue(int indexSheet, String adressCell, String value) {
        Pattern row = Pattern.compile("\\d+");
        Matcher mR = row.matcher(adressCell);
        mR.find();
        int indexRow = Integer.valueOf(adressCell.substring(mR.start(), mR.end()))-1;
        
        Pattern column = Pattern.compile("\\D+");
        Matcher mC = column.matcher(adressCell);
        mC.find();
        String sColumn = adressCell.substring(mC.start(), mC.end());
        int indexColumn = getIndexColumnByLetter(sColumn);
        
        putValue(indexSheet, indexRow, indexColumn, value);
    }
    
    public void writeToFile(File file) throws FileNotFoundException, IOException {
        if (file.exists()) file.delete();
        try (OutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            wb.write(out);
        }
    }
    
    private int getIndexColumnByLetter(String letters) {
        int index = -1;
        letters.length();
        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            index += (int) (((int) letter) - 64)*Math.pow(26.0, (double) (letters.length() - i - 1));
        }
        return index;
    }
}