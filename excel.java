package semana1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import jxl.*;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class excel {

public static void main(String[] args) throws InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
	JavascriptExecutor jse = (JavascriptExecutor) driver;
	driver.manage().window().maximize();
	Actions accion = new Actions(driver);
	Thread.sleep(2000);
	//Declarar variable Timestamp
	String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
	//Se crea el libro Excel
	try {
	WritableWorkbook workbook =
	Workbook.createWorkbook(new File("C:\\Users\\Mario\\Desktop\\Nueva carpeta\\"+timestamp+".xlsx"));
	//Flujo para seleccionar varios registros
	String URL = "http://demoqa.com/elements";
	driver.get(URL);
	jse.executeScript("scroll(0,1000);");
	List <WebElement> menu=driver.findElements(By.xpath("//div[@class=\"header-text\"]"));
	Thread.sleep(2000);
	for(WebElement e:menu) {
	if(e.getText().contains("Interactions")) {
	e.click();
	Thread.sleep(2000);
	jse.executeScript("scroll(0,1000);");
	break;
	}
	}
	List <WebElement> submenu=driver.findElements(By.xpath("//span[@class=\"text\"]"));
	Thread.sleep(2000);
	for(WebElement f:submenu) {
	if(f.getText().contains("Selectable")) {
	f.click();
	Thread.sleep(1000);
	break;
	}
	}
	driver.findElement(By.xpath("//a[@id=\"demo-tab-grid\"]")).click();
	List <WebElement> opc=driver.findElements(By.xpath("//li[@class=\"list-group-item list-group-item-action\"]"));
	String[] numeros = {"One","Five","Nine"};
	//Se crea una nueva hoja dentro del libro
	WritableSheet sheet = workbook.createSheet("Hoja1", 0);
	//sheet = workbook.createSheet("Hoja2", 1);
	int g=0;
	sheet.addCell(new jxl.write.Label(g, g,"Valores seleccionados:" ));
	for(int i=0;i<numeros.length;i++) {
	for (WebElement e:opc) {
	if(e.getText().contains(numeros[i])){
	sheet.addCell(new jxl.write.Label(0, i+1, "- "+numeros[i]));
	accion.click(e).perform();
	//break;
	}
	}
	}
	int b=numeros.length;
	sheet.addCell(new jxl.write.Label(0, b+1, "Se han seleccionado los valores"));
	Thread.sleep(5000);
	sheet.addCell(new jxl.write.Label(0, b+2, "Flujo concluido exitosamente"));
	workbook.write();
	workbook.close();
	driver.quit();
	} catch(Exception e){
	e.printStackTrace();
	System.out.println("Se cerro el navegador por Exception");
	driver.quit();
	}
	}

	}


