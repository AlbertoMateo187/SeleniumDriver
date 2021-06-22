package Ejercicios;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class escribir {

	public static void main(String[] args) {
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		try {
		//WritableWorkbook workbook =
		//Workbook.createWorkbook(new File("C:\\Users\\Mario\\Desktop\\Nueva carpeta\\"+timestamp+".xls"));
				WritableWorkbook workbook = Workbook.createWorkbook(new File ("c:\\practicas\\"+ timestamp + ".xls"));
		WritableSheet sheet = workbook.createSheet("Hoja1", 0);
		int a=0;
		sheet.addCell(new jxl.write.Label(a, a,"Lista de números:" ));
		for(int i=0;i<100;i++) {
			sheet.addCell(new jxl.write.Label(1, i, "   " +i ));
		}
		workbook.write();
		workbook.close();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por Exception");
			
			}

	}

}
