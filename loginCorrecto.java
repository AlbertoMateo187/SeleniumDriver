package semana1;

import java.io.FileInputStream;
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

	public class loginCorrecto {
	public static WebDriver driver;
	public static JavascriptExecutor jse;
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
			String url=null,user = null,pass = null, empresa=null, rs=null;
			String datos[];
			datos = new String[10];
			loginCorrecto puente = new loginCorrecto ();
			puente.leerDatos(datos);
			url=datos[1];
			user=datos[3];
			pass=datos[5];
			empresa=datos[7];
			rs=datos[9];
			driver.get(url);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			puente.ingresar(empresa, pass, user, rs, url);
			//driver.quit();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por Exception");
			//driver.quit();
		}
			}
	public static void leerDatos(String datos[]){
		try {
			String ruta="C:\\Users\\Mario\\Desktop\\loginHS.xlsx";
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
	public static void ingresar(String empresa, String pass, String user, String rs, String url) throws InterruptedException{
		try {
			driver.findElement(By.id("vMB_EPR_COD")).sendKeys(empresa);
			Thread.sleep(3000);
			System.out.println("LOG::::::::::: SE INGRESO LA EMPRESA");
			//Validar nombre de la empresa
			driver.findElement(By.id("IMAGE2")).click();
			Thread.sleep(3000);
			WebElement nombre=driver.findElement(By.tagName("text"));
			Thread.sleep(1000);
			System.out.println(driver.switchTo().alert().getText());
			driver.switchTo().alert().accept();
			/*
			WebElement nombre=driver.findElement(By.tagName("text"));		
				if(nombre.getText().equals(rs)) {
					System.out.println("LOG::::::::::::::::: EL NOMBRE DE LA EMPRESA ES CORRECTO");
					driver.findElement(By.id("vUSUID")).sendKeys(user);
					Thread.sleep(2000);
					System.out.println("LOG::::::::::: SE INGRESO EL USUARIO");
					driver.findElement(By.id("vUSUPSW")).sendKeys(pass);
					//Thread.sleep(2000);
					System.out.println("LOG::::::::::: SE INGRESO EL PASSWORD");
					//Thread.sleep(2000);
					driver.findElement(By.id("vIMAGEN8")).click();
					
					/*
					System.out.println("Se dio clic para ingresar al sistema");
					//Thread.sleep(2000);
					String urlActual= driver.getCurrentUrl();
					//if(urlActual!=url) {
					System.out.println("LOG::::::::::::::::::::::::::::::::: SE ACCEDIO CORRECTAMENTE AL SISTEMA");
					System.out.println("LOG::::::::::: VAMOS A CERRAR SESIÓN");
					jse.executeScript("scroll(100,0);");
					Thread.sleep(2000);
					driver.findElement(By.id("MPW0005SALIR")).click();
					Thread.sleep(2000);
					//}
					urlActual= driver.getCurrentUrl();
					if(urlActual.contains(url)) {
						System.out.println("LOG::::::::::::::::::::::: SE HA CERRADO SESIÓN CORRECTAMENTE");
					}
					else {
						
						System.out.println("LOG::::::::::::::::::::::: NO SE HA CERRADO SESIÓN");
				}
				}
				
		}
				else {
					System.out.println("pruebas");
					driver.switchTo().alert().accept();
					System.out.println(driver.switchTo().alert().getText());
				}*/
		}
		catch(Exception  r ){
			driver.switchTo().alert().accept();
			System.out.println(driver.switchTo().alert().getText());
			System.out.println("ALGO HA SALIDO MAL");
			
			Thread.sleep(4000);
			//r.printStackTrace();
			//driver.quit();
			
		}
	}
	}
		
	

