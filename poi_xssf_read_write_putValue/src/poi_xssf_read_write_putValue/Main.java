/*
 * Test for XSSFDocument.java
 * @author Leshjev Ivan
 */
package poi_xssf_read_write_putValue;

import java.io.File;
import java.io.FileInputStream;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Test started.");
        
        try {
            XSSFDocument doc = new XSSFDocument(new FileInputStream("D:\\f10.xlsx"));
            
            doc.putValue(0, 2, 2, "Test");
            
            doc.putValue(0, "BR7", "7");
            
            doc.writeToFile(new File("D:\\f10_new.xlsx"));



        } catch (Exception ex) {
            System.out.println("Test gave Exception." + ex.getMessage());
        } catch (Throwable ex) {
            System.out.println("Test gave Throwable." + ex.getMessage());
        }
        
        System.out.println("Test finished.");
    }
    
}