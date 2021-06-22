package semana1;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class laureles {
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
				String url=null,user = null,pass = null, nombre=null, valor=null,validar=null, mensaje=null;
				String datos[];
				datos = new String[6];
				laureles puente = new laureles ();
				puente.datos2(datos);
				url=datos[0];
				user=datos[1];
				pass=datos[2];
				nombre=datos[3];
				valor=datos[4];
				mensaje=datos[5];
				validar="selected";
				driver.get(url);
				driver.manage().window().maximize();
				//Login
				driver.findElement(By.id("identifierId")).sendKeys(user);
				Thread.sleep(2000);
				driver.findElement(By.className("VfPpkd-RLmnJb")).click();
				Thread.sleep(2000);
				driver.findElement(By.name("password")).sendKeys(pass);
				Thread.sleep(2000);
				driver.findElement(By.className("VfPpkd-RLmnJb")).click();
				Thread.sleep(6000);
				System.out.println("INGRESAMOS A LAURELES");
				//Entregar laurel
				puente.entregarLaureles(nombre,validar);
				System.out.println("LOG:::::::::::::::::: ENTRAMOS A LA PANTALLA DE VALORES");
				puente.seleccionarValor(valor,validar);
				puente.mensaje(mensaje);
				driver.quit();
				}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Se cerro el navegador por Exception");
				driver.quit();
				}
			}
			public static void datos2(String datos[]){
				try {
					String ruta="C:\\Users\\Mario\\Desktop\\Laureles.xlsx";
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
							System.out.println("celda: "+ y + contenidoCelda);
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
			public static void entregarLaureles(String nombre, String validar) throws InterruptedException{
			validar="check";
			driver.findElement(By.className("txt_numero_cardP")).click();
			Thread.sleep(2000);
			System.out.println("LOG:::::::::::::::   INGRESAMOS A LA PANTALLA DE REGLAS");
			driver.findElement(By.id("sg-bt")).click();
			Thread.sleep(2000);
			WebElement check=driver.findElement(By.className("mdl-stepper__title-icon"));
			if(check.getText().equals(validar)) {
				System.out.println("LOG:::::::::::::::   INGRESAMOS A LA PANTALLA COLABORADOR");
				}
				else {
				System.out.println("LOG::::::::::::::::  NO ESTAMOS EN LA PANTALLA DESEADA");
				}
			validar="selected";
			WebElement colaborador=driver.findElement(By.className("mdl-textfield__input"));
			colaborador.sendKeys(nombre);
			Thread.sleep(1000);
			colaborador.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			List <WebElement> coincidencias=driver.findElements(By.className("colaboradores__tarjeta__texto__nombre_t"));
			System.out.println("LA LISTA DE COINCIDENCIAS ES ::::::"  + coincidencias.size());
			if(coincidencias.size()<=1) {
				driver.findElement(By.className("colaboradores__tarjeta__texto__nombre_t")).click();
				Thread.sleep(2000);
				WebElement seleccionado=driver.findElement(By.xpath("(//div[@class])[44]"));
				if(seleccionado.getAttribute("class").contains(validar)) {
				System.out.println("LOG:::::::::::: SE HA SELECCIONADO AL COLABORADOR");
				Thread.sleep(2000);
				driver.findElement(By.xpath("(//button[@id])[2]")).click();
				}
					else {
						System.out.println("LOG::::::::::::::: NO SE HA SELECCIONADO EL COLABORADOR");
					}
			}
			else {
				for(WebElement e:coincidencias) {
					System.out.println(e.getText());
				}
			}
			}
			public static void seleccionarValor( String valor, String validar) throws InterruptedException{
			int c=1;
			List <WebElement> valores= driver.findElements(By.className("valores__tarjeta__titulo"));
			System.out.println("LOG::::::::::::: LISTA DE VALORES:::::::::::::::::::");
			for(WebElement e:valores) {
				System.out.println(e.getText());
				if(e.getText().contains(valor)) {
					e.click();
				}
			}
			List <WebElement>etiqueta=driver.findElements(By.xpath("//*[contains(@style,'background')]"));
			System.out.println(etiqueta.size());
			for(WebElement f:etiqueta){
				if(f.getAttribute("class").contains(validar)) {
					System.out.println("LOG::::::::::::::::: SE HA SELECCIONADO EL VALOR");
					driver.findElement(By.xpath("(//button[@id])[3]")).click();
					System.out.println("LOG:::::::::::::::::::: SE HA INGRESADO A LA PANTALLA DE MENSAJE");
					break;
				}
			}
			}
			public static void mensaje( String mensaje) throws InterruptedException{
				driver.findElement(By.id("textfield-")).sendKeys(mensaje);
				System.out.println();
				
			}
			}
