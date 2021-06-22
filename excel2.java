package semana1;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excel2 {


	    public static void main(String args[]){
	        try {
	            String rutaArchivoExcel = "C:\\Users\\Mario\\Downloads\\pruebasAut.xlsx";
	            FileInputStream inputStream = new FileInputStream(new File(rutaArchivoExcel));
	            Workbook workbook = new XSSFWorkbook(inputStream);
	            Sheet firstSheet = workbook.getSheetAt(0);
	            Iterator iterator = firstSheet.iterator();
	            
	            DataFormatter formatter = new DataFormatter();
	            while (iterator.hasNext()) {
	                Row nextRow = (Row) iterator.next();
	                Iterator cellIterator = nextRow.cellIterator();
	                while(cellIterator.hasNext()) {
	                    Cell cell = (Cell) cellIterator.next();
	                    String contenidoCelda = formatter.formatCellValue(cell);
	                    System.out.println("celda: " + contenidoCelda);
	                }
	                
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}