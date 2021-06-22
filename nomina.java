package flujosHS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class nomina {
		public static WebDriver driver;
		public static JavascriptExecutor jse;
		public static String proyecto;
			static {
			driver=getDriver();
			jse=getJse();
			}
			public static WebDriver getDriver() {
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			//WebDriver driver = new ChromeDriver();
			System.setProperty("webdriver.gecko.driver","C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\geckodriver.exe");
			WebDriver driver = new FirefoxDriver();
			return driver;
			}
			public static JavascriptExecutor getJse() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			return jse;
			}
			public static void main(String[] args)  throws InterruptedException{
			try {	
				String url=null,user = null,pass = null, empresa=null, rs=null, resultado=null;
				String exito="FLUJO CONCLUIDO EXITOSAMENTE";
				String datos[];
				datos = new String[12];
				nomina puente = new nomina ();
				puente.datos2(datos);
				for(int a=0;a<datos.length;a++) {
					System.out.println(datos[a]);
				}
				url=datos[1];
				user=datos[3];
				pass=datos[5];
				empresa=datos[7];
				rs=datos[9];
				resultado=datos[11];
				driver.get(url);
				driver.manage().window().maximize();
				Thread.sleep(2000);
				//Login
				System.out.println("LOGIN::::::::::::::: PANTALLA DE LOGIN");
				puente.login(empresa, user, pass,rs,url,resultado,exito);
				puente.vacaciones();
				puente.nomina1();
				//puente.correo(url,exito);
				//driver.quit();
				}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Se cerro el navegador por Exception");
				//driver.quit();
				}
			}
			public static void datos2(String datos[]){
				try {
					String projectPaht=System.getProperty("user.dir");
					String ruta=projectPaht+"\\Archivos\\loginHS.xlsx";
					FileInputStream f= new FileInputStream(ruta);
					XSSFWorkbook libro= new XSSFWorkbook (f);
					XSSFSheet hoja= libro.getSheetAt(0);
					Iterator <Row> filas=hoja.iterator();
					Iterator <Cell> celdas;
					Row fila;
					Cell celda;
					int y=0;
					System.out.println(datos.length);
					DataFormatter formatter = new DataFormatter();
					while(filas.hasNext()) {
						fila=filas.next();
						celdas=fila.cellIterator();
						while(celdas.hasNext()) {
							celda=celdas.next();
							String contenidoCelda = formatter.formatCellValue(celda);
							datos[y]=contenidoCelda;
							y++;
							}
						}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				return;
			}
			public static void login(String empresa, String user, String pass, String rs,String url, String resultado, String exito) throws InterruptedException, FileNotFoundException, UnsupportedEncodingException{
				String newURL=null;
				String alerta=null;
				File Archivo;
				PrintWriter Escribir;
				String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
				Archivo =new File (resultado+timestamp+".doc");
				try {
				driver.findElement(By.id("vMB_EPR_COD")).sendKeys(empresa);
				Thread.sleep(3000);
				System.out.println("LOG::::::::::: SE INGRESO LA EMPRESA");
				//Validar nombre de la empresa
				driver.findElement(By.id("IMAGE2")).click();
				Thread.sleep(1000);
				alerta=driver.switchTo().alert().getText();
				System.out.println("Algo ha salido mal");
				System.out.println(alerta);
				Escribir=new PrintWriter (Archivo,"utf-8");
				Escribir.println(alerta);
				Escribir.close();
				driver.switchTo().alert().accept();
				System.out.println("Se va cerrar la pantalla");
				driver.close();
				}
				catch( Exception r ){
					try {
					WebElement nombre=driver.findElement(By.tagName("text"));
					System.out.println(nombre.isDisplayed());
					System.out.println(nombre.getText());
					if(nombre.getText().equals(rs)) {
						System.out.println("LOG::::::::::::::::: EL NOMBRE DE LA EMPRESA ES CORRECTO");
						driver.findElement(By.id("vUSUID")).sendKeys(user);
						Thread.sleep(2000);
						System.out.println("LOG::::::::::: SE INGRESO EL USUARIO");
						driver.findElement(By.id("vUSUPSW")).sendKeys(pass);
						Thread.sleep(2000);
						System.out.println("LOG::::::::::: SE INGRESO EL PASSWORD");
						Thread.sleep(2000);
						driver.findElement(By.id("vIMAGEN8")).click();
						Thread.sleep(2000);
						alerta=driver.switchTo().alert().getText();
						System.out.println("Algo ha salido mal");
						System.out.println(alerta);
						/*
						Escribir=new PrintWriter (Archivo,"utf-8");
						Escribir.println(alerta);
						Escribir.close();
						*/
						driver.switchTo().alert().accept();
						System.out.println("Se va cerrar la pantalla");
						driver.close();
						}
					}
						catch( Exception e ){
							newURL=driver.getCurrentUrl();
							if(newURL!=url) {
							System.out.println("LOG::::::::::::::::::::::: ACCEDIMOS AL SISTEMA");
							
							/*
							Archivo =new File (resultado+timestamp+".doc");
							Escribir=new PrintWriter (Archivo,"utf-8");
							Escribir.println(exito);
							Escribir.close();
							*/
							}
							else {
								System.out.println("No se accedio al sistema");
							}
					}
				}
			}
			public static void vacaciones() throws InterruptedException{
				jse.executeScript("scroll(0,3000);");
				Thread.sleep(2000);
				WebElement dias=driver.findElement(By.id("span_W0125W0195vVECDIASALD_0001"));
				System.out.println(dias.getText());
				System.out.println("fin");
			}
			public static void nomina1() throws InterruptedException, IOException{
				jse.executeScript("scroll(0,-3000);");
				String alerta=null;
				Thread.sleep(2000);
				//Clic a la opci�n N�mina
				WebElement nomina=driver.findElement(By.id("MPW0005TEXTBLOCK5"));
				nomina.click();
				Thread.sleep(3000);
				WebElement pago=driver.findElement(By.id("MPW0005NOM1"));
				System.out.println("::::::::::::::::::::::"+ pago.isDisplayed());
				pago.click();
				Thread.sleep(3000);
				alerta=driver.switchTo().alert().getText();
				System.out.println(alerta);
				driver.switchTo().alert().accept();
				Thread.sleep(6000);
				driver.findElement(By.name("IMGNOM")).click();
				Thread.sleep(2000);
				driver.switchTo().frame(1);
				Thread.sleep(2000);
				driver.findElement(By.name("vLINKSELECTION_0001")).click();
				Thread.sleep(2000);
				driver.switchTo().defaultContent();
				WebElement corpo=driver.findElement(By.id("vF_PRCODIGO"));
				corpo.clear();
				Thread.sleep(1000);
				corpo.sendKeys("ORDINARI");
				Thread.sleep(1000);
				driver.findElement(By.name("BTN_ACTUALIZAR")).click();
				Thread.sleep(2000);
				jse.executeScript("scroll(0,2000);");
				driver.findElement(By.name("BTN_DISPERSION")).click();
				System.out.println("Entramos a DISPERSI�N");
				Thread.sleep(3000);
				ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
				System.out.println(tabs);
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				String nombreBanco="BANAMEX";
				WebElement banco=driver.findElement(By.id("vBANCOID"));
				Select opcion = new Select (banco);	
				opcion.selectByVisibleText(nombreBanco);
				Thread.sleep(3000);
				driver.findElement(By.id("vFECHA1_dp_trigger")).click();
				Thread.sleep(3000);
				//driver.findElement(By.xpath("//td[@class=\"day selected today\"]")).click();
				List <WebElement> dias=driver.findElements(By.xpath("//td[@class]"));
				System.out.println(dias.size());
				for(WebElement a:dias) {
					String dia=a.getAttribute("class");
					if(dia.contains("day today")) {
						a.click();
						driver.findElement(By.id("IMAGE5")).click();
						Thread.sleep(5000);
						System.out.println(driver.findElement(By.id("span_vDATO1")).getText());
						break;
					}
				}
				//Ingresar la secuencia
				driver.findElement(By.id("vDATO2")).sendKeys("99");
				//Ingresar la Descripci�n
				WebElement descripcion=driver.findElement(By.id("vDATO3"));
				descripcion.clear();
				descripcion.sendKeys("PAGO DE NOMINA");
				jse.executeScript("scroll(0,2000);");
				//Ingresar la Compa�ia
				WebElement compania=driver.findElement(By.id("vDATO4"));
				compania.clear();
				compania.sendKeys("LABORATORIOS KENER SA DE CV");
				//Ingresar la Detalle
				WebElement detalle=driver.findElement(By.id("vDATO5"));
				detalle.clear();
				detalle.sendKeys("NOMINA");
				
				//Guardar Datos
				driver.findElement(By.xpath("//span[@onclick=\"gx.evt.doClick('BUTTON1', event)\"]")).click();
				Thread.sleep(5000);
				System.out.println("Le dimos clic a Aceptar");
				
				//Activar checks
				List <WebElement> checks=driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));
				System.out.println(checks.size());
				for(WebElement b:checks) {
					System.out.println(b.getAttribute("id"));
					if(b.getAttribute("id").contains("vMON")) {
						b.click();
					}
				}
				Thread.sleep(3000);
				//clic a n�mina
				driver.findElement(By.id("MPW0005TEXTBLOCK5")).click();
				Thread.sleep(2000);
				//Clic a la bandeja de reportes
				driver.findElement(By.id("MPW0005NOM11")).click();
				Thread.sleep(2000);
				jse.executeScript("scroll(0,2000);");
				//Ir a la  �ltima p�gina
				driver.findElement(By.name("IMAGE13")).click();
				Thread.sleep(2000);
				//Seleccionar el �ltimo registro
				List <WebElement> registros=driver.findElements(By.xpath("//*[contains(@id,'vBOTON')]"));
				System.out.println(registros.size());
				int reg=registros.size();
				int ultimo=0;
				for(WebElement a:registros) {
					ultimo=ultimo+1;
					System.out.println(ultimo);
					if(ultimo==reg) {
						a.click();
						Thread.sleep(2000);
						}
				}
				//Consultar registros
				driver.findElement(By.className("Btn_ActionBar")).click();
				Thread.sleep(4000);
				WebElement datos=driver.findElement(By.tagName("pre"));
				System.out.println(datos.getText());
				File archivo=new File("C:\\Users\\Mario\\Desktop\\IW\\Nayarit\\SP05\\Evidencias\\Back\\test.txt");

				//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
				FileWriter escribir=new FileWriter(archivo,true);

				//Escribimos en el archivo con el metodo write
				escribir.write(datos.getText());

				//Cerramos la conexion
				escribir.close();
				}
				
			
}
