package flujo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class cargarArchivo {
		public static WebDriver driver;
		public static JavascriptExecutor jse;
		public static String proyecto;
			static {
			driver=getDriver();
			jse=getJse();
			}
			public static WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			return driver;
			}
			public static JavascriptExecutor getJse() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			return jse;
			}
			public static void main(String[] args)  throws InterruptedException, IOException, InvalidFormatException{
			try {	
				Properties propiedades = new Properties();
				//String projectPaht=System.getProperty("user.dir");
				String ruta="C:\\Users\\Mario\\PROSA\\src\\ArchivoCRI";
				propiedades.load(new FileInputStream( ruta));
				String user = propiedades.getProperty("user");
				String pass = propiedades.getProperty("pass");
				String url = propiedades.getProperty("url");
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				driver.get(url);
				driver.manage().window().maximize();
				Thread.sleep(4000);
				driver.switchTo().frame(0);
				System.out.println("Entramos al frame");
				String titulo=driver.findElement(By.tagName("h4")).getText();
				System.out.println(titulo);
				driver.findElement(By.xpath("//input[@placeholder=\"Usuario\"]")).sendKeys(user);
				System.out.println("Entramos al user");
				driver.findElement(By.name("contra")).sendKeys(pass);
				System.out.println("Entramos al pass");
				driver.findElement(By.tagName("button")).click();
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame(0);
				driver.switchTo().frame(0);
				//Entramos a la carga de archivo
				String archivo="CARGA ARCHIVO"+timeStamp;
				System.out.println(archivo);
				System.out.println(timeStamp.length()+ timeStamp);
				String dia = timeStamp.substring(8,10);
				System.out.println(dia);
				XWPFDocument docx = new XWPFDocument();
				XWPFParagraph par = docx.createParagraph();
				XWPFRun run = par.createRun();
				run.setText("FLUJO AUTOMATIZADO CRI");
				run.setFontSize(73);    
				// Obtienes el mes actual
				Month mesActual = LocalDate.now().getMonth();
				// Obtienes el nombre del mes
				String mes = mesActual.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
				
				System.out.println(mesActual);
				System.out.println(mes);
				String primeraLetra = mes.substring(0,1);
				String mayuscula = primeraLetra.toUpperCase();
				String demasLetras = mes.substring(1, 3);
				mes = mayuscula + demasLetras;
				System.out.println(mes);
				Thread.sleep(2000);
				System.out.println("Entramos al frame");
				driver.findElement(By.xpath("//ul[@data-target=\"#op10\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//a[@target=\"mainFrame\"])[2]")).click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().defaultContent();
				System.out.println("Salimos de los Frames");
				driver.switchTo().frame(0);
				driver.switchTo().frame(1);
				String nombre=driver.findElement(By.tagName("h3")).getText();
				System.out.println(nombre);
				driver.findElement(By.id("uploadFichero")).sendKeys("C:\\Users\\Mario\\archivo MASIVO DICIEMBRE.csv");
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				driver.findElement(By.id("IncorporaArchivoExecute_descripcion")).sendKeys(archivo);
				Thread.sleep(2000);
				driver.findElement(By.tagName("button")).click();
				Thread.sleep(2000);
				InputStream pic = new FileInputStream(ruta+"1"+timeStamp+".png");
				run.addPicture(pic, Document.PICTURE_TYPE_JPEG, "1", Units.toEMU(500), Units.toEMU(200));
				driver.switchTo().defaultContent();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.switchTo().frame(0);
				//Entramos a la opción de Reportes
				Thread.sleep(2000);
				System.out.println("Entramos al frame");
				driver.findElement(By.xpath("//ul[@data-target=\"#op06\"]")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//a[@target=\"mainFrame\"])[5]")).click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				driver.switchTo().defaultContent();
				System.out.println("Salimos de los Frames");
				
				driver.switchTo().frame(0);
				driver.switchTo().frame(1);
				//CLic al calendario
				driver.findElement(By.xpath("//span[@class=\"input-group-addon\"][2]")).click();
				Thread.sleep(2000);
				//CLic para ver los Meses
				driver.findElement(By.xpath("//th[@class=\"datepicker-switch\"]")).click();
				Thread.sleep(1000);
				//Clic para ir un año atrás
				//driver.findElement(By.xpath("(//th[@class=\"prev\"])[2]")).click();
				Thread.sleep(1000);
				List <WebElement> meses=driver.findElements(By.xpath("//span[contains(@class,'month')]"));
				for(WebElement b:meses) {
					if(b.getText().contains("Abr")) {
						b.click();
						break;
					}
				}
				pic = new FileInputStream(ruta+"3"+timeStamp+".png");
				run.addPicture(pic, Document.PICTURE_TYPE_JPEG, "1", Units.toEMU(500), Units.toEMU(200));
				Thread.sleep(1000);
				List <WebElement> dias=driver.findElements(By.xpath("//td[@class=\"day\"]"));
				for(WebElement c:dias) {
					if(c.getText().contains(dia)) {
						c.click();
						break;
					}
				}
				driver.findElement(By.tagName("button")).click();
				Thread.sleep(1000);
				int x=1,
						y=1;
				boolean estatus=false;
				while(estatus==false) {
				List <WebElement> archivos=driver.findElements(By.xpath("//tr[@class]"));
				System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::"+archivos.size());
				int z=archivos.size();
				for(WebElement d:archivos) {

					//System.out.println(d.getText());
					if(d.getText().contains(archivo)&& d.getText().contains("Procesado2")) {
						
						System.out.println("Archivo encontrado");
						estatus=true;
						String total=driver.findElement(By.xpath("(//td[@class=\"colListas6\"])"+"["+x+"]")).getText();
						String rechazadas=driver.findElement(By.xpath("(//td[@class=\"colListas7\"])"+"["+x+"]")).getText();
						String aceptadas=driver.findElement(By.xpath("(//td[@class=\"colListas8\"])"+"["+x+"]")).getText();
						System.out.println(total+ "    " + rechazadas+ "        "+ aceptadas);
						int total2 = Integer.parseInt(total),
								rechazadas2 = Integer.parseInt(rechazadas),
										aceptadas2 = Integer.parseInt(aceptadas),
											total3=rechazadas2+aceptadas2;
						if(total2==total3) {
							System.out.println("EL TOTAL COINCIDE");
							driver.findElement(By.xpath("(//td[@class=\"colListas9\"])"+"["+x+"]")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("(//td[@class=\"colListas10\"])"+"["+x+"]")).click();
							Thread.sleep(1000);
							driver.findElement(By.xpath("(//td[@class=\"colListas11\"])"+"["+x+"]")).click();
							Thread.sleep(1000);
						}
						else {
							System.out.println("EL TOTAL NO COINCIDE");
						}
						break;
					}
					if(y>=3) {
						if(d.getText().contains("pruebas de QA 06042021")) {
							System.out.println(d.getText());
							String status=driver.findElement(By.xpath("(//td[@class=\"colListas4\"])["+x+"]")).getText();
							estatus=true;
							System.out.println("EL ARCHIVO TIENE ESTATUS " + status);
							pic = new FileInputStream(ruta+"2"+timeStamp+".png");
							run.addPicture(pic, Document.PICTURE_TYPE_JPEG, "1", Units.toEMU(500), Units.toEMU(200));
							break;
						}
					}
					x=x+1;
						}
				x=1;
				y=y+1;
				
				Thread.sleep(2000);
				driver.findElement(By.tagName("button")).click();
				
				Thread.sleep(1000);
				}
			
			
				driver.switchTo().defaultContent();
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.switchTo().frame(0);
				driver.switchTo().frame(0);
				Thread.sleep(1000);
				FileOutputStream out = new FileOutputStream(ruta+timeStamp+".doc");
				docx.write(out);
				out.close();
				docx.close();
				/*
				driver.findElement(By.xpath("//a[@target=\"_top\"]")).click();
				Thread.sleep(3000);
				System.out.println("Flujo concluido");
				driver.quit();
				*/			
}
			catch(Exception e){
				e.printStackTrace();
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				String ruta="C:\\Users\\Mario\\Desktop\\IW\\Nayarit\\ERROR";
				File archivo=new File(ruta+timeStamp+".txt");

				//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
				FileWriter escribir=new FileWriter(archivo,true);
				
				//Escribimos en el archivo con el metodo write
				escribir.write("Se cerro el navegador por Exception");
				File screenshotFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshotFile, new File(ruta+timeStamp+".png"));
				//Cerramos la conexion
				escribir.close();
				System.out.println("Se cerro el navegador por Exception");
				XWPFDocument docx = new XWPFDocument();
				XWPFParagraph par = docx.createParagraph();
				XWPFRun run = par.createRun();
				run.setText("ERROR EN EL FLUJO AUTOMATIZADO");
				run.setFontSize(73);    
				InputStream pic = new FileInputStream(ruta+timeStamp+".png");
				run.addPicture(pic, Document.PICTURE_TYPE_JPEG, "1", Units.toEMU(500), Units.toEMU(200));
				FileOutputStream out = new FileOutputStream(ruta+timeStamp+".doc");
				docx.write(out);
				out.close();
				docx.close();
				driver.quit();
				}
			}
			}		


