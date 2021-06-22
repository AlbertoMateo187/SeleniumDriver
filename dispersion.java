package nomina;
import java.io.File;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;
public class dispersion {

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
	public static void main(String[] args)  throws InterruptedException{
		try {	
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream( "C:\\Users\\Mario\\flujosHS\\src\\propiedades\\archivo.properties"));
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("pass");
			String url = propiedades.getProperty("url");
			String empresa = propiedades.getProperty("empresa");
			String razonSocial = propiedades.getProperty("razónSocial");
			String nombreBanco = propiedades.getProperty("nombreBanco");
			String idDeduccion=propiedades.getProperty("idDeduccion");
			String concepto=propiedades.getProperty("concepto");
			String agrupacion=propiedades.getProperty("agrupacion");
			String agrupacion2=propiedades.getProperty("agrupacion2");
			String percepcion=propiedades.getProperty("percepcion");
			String idPercepcion=propiedades.getProperty("idPercepcion");
			String nombrePercepcion=propiedades.getProperty("nombrePercepcion");
			//System.out.println("USUARIO: "+usuario + "\n" +"PASS: "+ pass);
			dispersion puente = new dispersion ();
			driver.get(url);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			puente.validarEmpresa( empresa,razonSocial);
			puente.login(user, pass,url);
			puente.nomina();
			puente.agrupaciones(idDeduccion);
			puente.nomina();
			puente.conceptos(concepto,agrupacion,agrupacion2);
			puente.consultarDetalle(idDeduccion);
			puente.deducciones(agrupacion2);
			puente.percepciones(percepcion,idPercepcion,nombrePercepcion);
			
			
		}
		catch (FileNotFoundException e) {
			   System.out.println("Error, El archivo no exite");
		} 
		catch (IOException e) {
				System.out.println("Error, No se puede leer el archivo");
		}
		}
		public static void validarEmpresa(String empresa,String razonSocial) throws InterruptedException{		
			try {
				driver.findElement(By.id("vMB_EPR_COD")).sendKeys(empresa);
				Thread.sleep(3000);
				System.out.println("LOG::::::::::: SE INGRESO LA EMPRESA");
				//Validar nombre de la empresa
				driver.findElement(By.id("IMAGE2")).click();
				Thread.sleep(3000);
				String alerta=null;
				alerta=driver.switchTo().alert().getText();
				System.out.println("Algo ha salido mal");
				System.out.println("LOG:::::::::::::::::::::::" +  alerta);
				driver.quit();
				}
			catch( Exception e ){
				WebElement nombre=driver.findElement(By.tagName("text"));
				if(nombre.getText().equals(razonSocial)) {
					System.out.println("LOG::::::::::::::: LA EMPRESA ES CORRECTA");
				}
				else {
					System.out.println("LOG::::::::::::::: LA EMPRESA ES inCORRECTA");
					}
			}
		}
		public static void login(String user,String pass,String url) throws InterruptedException{		
			try {
				//Ingresar Usuario y Password
				driver.findElement(By.id("vUSUID")).sendKeys(user);
				System.out.println("LOG::::::::::: SE INGRESO EL USUARIO");
				driver.findElement(By.id("vUSUPSW")).sendKeys(pass);
				System.out.println("LOG::::::::::: SE INGRESO EL PASSWORD");
				driver.findElement(By.id("vIMAGEN8")).click();
				Thread.sleep(2000);
				String alerta=null;
				alerta=driver.switchTo().alert().getText();
				System.out.println("Algo ha salido mal");
				System.out.println("LOG:::::::::::::::::::::::" +  alerta);
				driver.quit();
				}
	
			catch( Exception e ) {
				String currentURL=null;
				currentURL=driver.getCurrentUrl();
				if(currentURL!=url) {
					System.out.println("LOG::::::::::::::::::::::: ACCEDIMOS AL SISTEMA");
				}
			else {
				System.out.println("LOG::::::::::::::::::::::: NO ACCEDIMOS AL SISTEMA");
					}
				}	
			}
		public static void agrupaciones(String idDeduccion) throws InterruptedException{		
			try {
				System.out.println(idDeduccion);
				//Tipo de agrupaciones
				WebElement agrupaciones=driver.findElement(By.partialLinkText("Tipos agrupaciones"));
				agrupaciones.click();
				Thread.sleep(2000);
				WebElement titulo=driver.findElement(By.id("TEXTBLOCK2"));
				System.out.println(titulo.isDisplayed());
				System.out.println("LOG::::::::::: Entramos a la pantalla " + "   "  + titulo.getText());
				//Buscar la agrupación Deducciones
				List <WebElement> deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_TIPOAGRUPACIONID_')]"));
				int 
				c=deduccion.size(),
				b=0;
				boolean cond=false;
				while(cond==false) {
				for(WebElement a:deduccion) {
					jse.executeScript("scroll(0,27);");
					if(a.getText().contains(idDeduccion)) {
						a.click();
						System.out.println("Encontrado");
						cond=true;
						Thread.sleep(2000);
						break;
					}
					else {
						b=b+1;
						cond=false;
						if(b==c) {
							driver.findElement(By.className("PagingButtonsNext")).click();
							jse.executeScript("scroll(0,-270);");
							Thread.sleep(4000);
							b=0;
							deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_TIPOAGRUPACIONID_')]"));
						}
					}
				}
				}
				//Clic al botón Buscar
				driver.findElement(By.name("IMAGE7")).click();
				Thread.sleep(2000);
				b=0;
				//Buscar la clave de deducción 002
				deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLAGRUPACIONID')]"));
				System.out.println(deduccion.size());
				for(WebElement a:deduccion) {
					b=b+1;
					if(a.getText().equals("002")) {
						System.out.println(b);
					}
				}
				c=0;
				//Buscar la clave de deducción ISR
				deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLAGRUPACIONDSC')]"));
				System.out.println(deduccion.size());
				for(WebElement a:deduccion) {
					c=c+1;
					if(a.getText().equals("ISR")) {
						System.out.println(b);
					}
				}
				if(b==c) {
					System.out.println("La relación de claves es correcta 002 - ISR");
					System.out.println("LOG:::::::::::::::::::::::::::::::::::   FIN DEL FLUJO DE DEDUCCIONES");
				}
				else {
					System.out.println("La relación de claves es incorrecta 002 - ISR");
					driver.quit();
				}
			}
			catch ( Exception e ) {
				e.printStackTrace();
				driver.quit();
			}
		}
		public static void conceptos(String concepto,String agrupacion,String agrupacion2) throws InterruptedException{		
			try {
				//Seleccionar la opción Conceptos
				WebElement agrupaciones=driver.findElement(By.partialLinkText("Conceptos"));
				agrupaciones.click();
				Thread.sleep(5000);
				//Consultar Nombre de la pantalla
				WebElement titulo=driver.findElement(By.id("TEXTBLOCK63"));
				System.out.println("LOG:::::::::::::::::::::::::: El nombre de la pantalla es "+ titulo.getText());
				//Ingresar el ID del concepto
				driver.findElement(By.id("vB_NOMBRE")).sendKeys(concepto);
				//Consultar concepto
				driver.findElement(By.name("IMAGE1")).click();
				Thread.sleep(2000);
				WebElement tipoConcepto=driver.findElement(By.id("span_vTIPCONFORDSC"));
				System.out.println("LOG:::::::::::::::::::::::EL TIPO DE CONCEPTO ES ::::::::::::::::"+tipoConcepto.getText() );
				jse.executeScript("scroll(0,600);");
				//Modificar concepto
				driver.findElement(By.name("IMAGE20")).click();
				Thread.sleep(2000);
				ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
				System.out.println(tabs);
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				jse.executeScript("scroll(0,100);");
				List <WebElement> tipoAgrupacion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONID')]"));
				int 
				c=tipoAgrupacion.size(),
				b=0;
				boolean cond=false;
				while(cond==false) {
				for(WebElement a:tipoAgrupacion) {
					jse.executeScript("scroll(0,27);");
					System.out.println(a.getText());
					if(a.getText().contains(agrupacion)) {
						b=b+1;
						jse.executeScript("scroll(0,600);");
						cond=true;
						break;
						
					}
					else {
						b=b+1;
						cond=false;
						if(b==c) {
							driver.findElement(By.id("IMAGE4")).click();
							Thread.sleep(4000);
							b=0;
							tipoAgrupacion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONID')]"));
						}
					}
				}
				}
				//Dar clic al Detalle del tipo de Agrupación
				if(b<10) {
					driver.findElement(By.id("span_vDETALLE_000"+b)).click();
					Thread.sleep(4000);
				}
				else {
					driver.findElement(By.id("span_vDETALLE_00"+b)).click();
					Thread.sleep(4000);
				}
				//Verificar checkbox activo ISR
				driver.switchTo().frame(0);
				Thread.sleep(2000);
				int x=0;
				WebElement activo=null;
				List <WebElement> agru=driver.findElements(By.xpath("//*[contains(@id,'span_CTLAGRUPACIONID')]"));
				for(WebElement a:agru) {
					x=x+1;
					if(a.getText().equals(agrupacion2)) {
						activo=driver.findElement(By.name("CTLAPLICA_000"+x));
						if (activo.isSelected()==true) {
							System.out.println("LOG::::::::::::::::::::::  EL CHECK DE ISR ESTÁ ACTIVO");
							driver.findElement(By.name("IMAGE3")).click();
							Thread.sleep(1000);
						}	
						else {
							activo.click();
							Thread.sleep(1000);
							driver.findElement(By.name("IMAGE3")).click();
							Thread.sleep(1000);
						}
						break;
					}
				}
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
				driver.findElement(By.id("gxp0_cls")).click();
			}
			catch ( Exception e ) {
				e.printStackTrace();
				driver.quit();
			}
		}
		public static void deducciones(String agrupacion2) throws InterruptedException{		
			try {
				driver.switchTo().frame(0);
				//Buscar la clave de deducción 002
				int b=0,
				c=0;
				List <WebElement> deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLAGRUPACIONID')]"));
				int tamaño=deduccion.size();
				System.out.println(":::::::::::::::::::::::::::" + tamaño);
				boolean cond=false;
				while (cond==false) {
					for(WebElement a:deduccion) {
					if(a.getText().equals("002")) {
						System.out.println(b);
						b=b+1;
						cond=true;
						break;
					}
						
						else {
							b=b+1;
							System.out.println(b);
							if(b==tamaño) {
								System.out.println("ENTRAMOS AL CICLO");
								driver.findElement(By.name("IMAGE15")).click();
								Thread.sleep(3000);
								deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLAGRUPACIONID')]"));
								cond=false;
								b=0;
							}
					
				}
				}
				}
				c=0;
				//Buscar la clave de deducción ISR
				deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLAGRUPACIONDSC')]"));
				System.out.println(deduccion.size());
				for(WebElement a:deduccion) {
					c=c+1;
					if(a.getText().equals("ISR")) {
						break;
					}
				}
				if(b==c) {
					System.out.println("La relación de claves es correcta 002 - ISR");
					WebElement activo=driver.findElement(By.name("CTLAPLICA_000"+b));
					if(activo.isSelected()==true) {
						driver.findElement(By.name("IMAGE3")).click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
						Thread.sleep(2000);
						driver.findElement(By.id("gxp0_cls")).click();
					}
					else {
						activo.click();
						Thread.sleep(2000);
						driver.findElement(By.name("IMAGE3")).click();
						Thread.sleep(2000);
						driver.switchTo().defaultContent();
						Thread.sleep(2000);
						driver.findElement(By.id("gxp0_cls")).click();
					}
					System.out.println("LOG:::::::::::::::::::::::::::::::::::   FIN DEL FLUJO DE DEDUCCIONES");
					ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
					driver.close();
					driver.switchTo().window(tabs.get(0));
					
				}
				else {
					System.out.println("La relación de claves es incorrecta 002 - ISR");
					driver.quit();
				}
				
			}
			catch ( Exception e ) {
				e.printStackTrace();
				driver.quit();
			}
		}
		public static void consultarDetalle(String idDeduccion) throws InterruptedException{		
			try {
				jse.executeScript("scroll(0,600);");
				List <WebElement> deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONID')]"));
				int 
				c=deduccion.size(),
				b=0;
				boolean cond=false;
				while(cond==false) {
				for(WebElement a:deduccion) {
					System.out.println(a.getText());
					if(a.getText().contains(idDeduccion)) {
						b=b+1;
						cond=true;
						break;
						
					}
					else {
						b=b+1;
						cond=false;
						if(b==c) {
							driver.findElement(By.id("IMAGE4")).click();
							Thread.sleep(4000);
							b=0;
							deduccion=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONID')]"));
						}
					}
				}
				}
				//Dar clic al Detalle del tipo de Agrupación Deducciones
				if(b<10) {
					driver.findElement(By.id("span_vDETALLE_000"+b)).click();
					Thread.sleep(4000);
				}
				else {
					driver.findElement(By.id("span_vDETALLE_00"+b)).click();
					Thread.sleep(4000);
				}
				Thread.sleep(2000);
			}
			catch ( Exception e ) {
				e.printStackTrace();
				driver.quit();
			}
		}
		public static void nomina() throws InterruptedException{		
			try {
				//Clic a la opción Nómina
				WebElement nomina=driver.findElement(By.id("MPW0005TEXTBLOCK5"));
				nomina.click();
				Thread.sleep(3000);
				WebElement engrane=driver.findElement(By.name("MPW0005vCONFIG"));
				engrane.click();
				Thread.sleep(2000);
			}
			catch ( Exception e ) {
				e.printStackTrace();
				driver.quit();
			}
		}
		public static void percepciones(String percepcion,String idPercepcion, String nombrePercepcion) throws InterruptedException{		
			try {
				System.out.println("::::::::::::::::::::::::::::::::::PERCEPCIONES::::::::::::::::::::::::::::::::::");
				//Limpiar Pantalla
				driver.findElement(By.id("vB_NOMBRE")).clear();
				Thread.sleep(2000);
				//Ingresar el ID del concepto
				driver.findElement(By.id("vB_NOMBRE")).sendKeys(percepcion);
				//Consultar concepto
				driver.findElement(By.name("IMAGE1")).click();
				Thread.sleep(2000);
				WebElement tipoConcepto=driver.findElement(By.id("span_vTIPCONFORDSC"));
				System.out.println("LOG:::::::::::::::::::::::EL TIPO DE CONCEPTO ES ::::::::::::::::"+tipoConcepto.getText() );
				jse.executeScript("scroll(0,600);");
				//Modificar concepto
				driver.findElement(By.name("IMAGE20")).click();
				Thread.sleep(4000);
				ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
				System.out.println(tabs);
				driver.switchTo().window(tabs.get(1));
				driver.manage().window().maximize();
				//Buscar la clave de Percepción
				jse.executeScript("scroll(0,600);");
				int b=0,
				c=0;
				List <WebElement> percepciones=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONID')]"));
				int tamaño=percepciones.size();
				System.out.println(":::::::::::::::::::::::::::" + tamaño);
				boolean cond=false;
				while (cond==false) {
					for(WebElement a:percepciones) {
					if(a.getText().equals(idPercepcion)) {
						System.out.println(b);
						b=b+1;
						cond=true;
						break;
					}
						
						else {
							b=b+1;
							System.out.println(b);
							if(b==tamaño) {
								System.out.println("ENTRAMOS AL CICLO");
								driver.findElement(By.name("IMAGE4")).click();
								Thread.sleep(3000);
								percepciones=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONID')]"));
								cond=false;
								b=0;
							}
					
				}
				}
				}
				c=0;
				//Buscar la clave de Percepción CFD Total Gravado percepciones
				percepciones=driver.findElements(By.xpath("//*[contains(@id,'span_CTLTIPOAGRUPACIONDSC')]"));
				System.out.println(percepciones.size()+"::::::::::::::::::::::::");
				for(WebElement a:percepciones) {
					c=c+1;
					if(a.getText().equals(nombrePercepcion)) {
						System.out.println(c);
						break;
					}
				}
				if(b==c) {
					System.out.println("La relación de PERCEPCIONES es correcta");
					if(b<10) {
						driver.findElement(By.id("span_vDETALLE_000"+b)).click();
						Thread.sleep(3000);
					}
					else {
						driver.findElement(By.id("span_vDETALLE_00"+b)).click();
						Thread.sleep(3000);
					}
					
			}
				else {
					System.out.println("La relación de PERCEPCIONES es INCORRECTA");
					driver.quit();
				}
				//Validar checkbox activo
				percepciones=driver.findElements(By.id("//*[contains(@id,'span_CTLAGRUPACIONID_0001')]"));
				int f=percepciones.size();
				System.out.println(f);
				if(f<=1) {
					
				}
			}
			catch ( Exception e ) {
				e.printStackTrace();
				driver.quit();
			}
		}
	}	