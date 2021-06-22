package flujo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CRI {
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
			public static void main(String[] args)  throws InterruptedException, IOException{
			try {	
				Properties propiedades = new Properties();
				//String projectPaht=System.getProperty("user.dir");
				String ruta="C:\\Users\\Mario\\PROSA\\src\\ArchivoCRI";
				propiedades.load(new FileInputStream( ruta));
				String user = propiedades.getProperty("user");
				String pass = propiedades.getProperty("pass");
				String url = propiedades.getProperty("url");
				driver.get(url);
				driver.manage().window().maximize();
				Thread.sleep(4000);
				driver.switchTo().frame(0);
				System.out.println("Entramos al frame");
				String titulo=driver.findElement(By.tagName("h4")).getText();
				System.out.println(titulo);
				//driver.findElement(By.tagName("button")).click();
				driver.findElement(By.xpath("//input[@placeholder=\"Usuario\"]")).sendKeys(user);
				System.out.println("Entramos al user");
				driver.findElement(By.name("contra")).sendKeys(pass);
	
				System.out.println("Entramos al pass");
				driver.findElement(By.tagName("button")).click();
				driver.switchTo().defaultContent();
				Thread.sleep(1000);
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
				driver.findElement(By.xpath("(//th[@class=\"prev\"])[2]")).click();
				Thread.sleep(1000);
				List <WebElement> meses=driver.findElements(By.xpath("//span[contains(@class,'month')]"));
				for(WebElement b:meses) {
					if(b.getText().contains("Dic")) {
						b.click();
						break;
					}
				}
				Thread.sleep(1000);
				List <WebElement> dias=driver.findElements(By.xpath("//td[@class=\"day\"]"));
				for(WebElement c:dias) {
					if(c.getText().contains("24")) {
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
				
				for(WebElement d:archivos) {

					//System.out.println(d.getText());
					if(d.getText().contains("test")&& d.getText().contains("Procesado2")) {
						
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
					x=x+1;
					System.out.println(x + "               "         + y);
					if(y>=3) {
						if(d.getText().contains("test")&& d.getText().contains("Pendiente")) {
						estatus=true;
						System.out.println("EL ARCHIVO TIENE ESTATUS PENDIENTE");
						break;
						}
					}
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
				File archivo=new File("C:\\Users\\Mario\\Desktop\\IW\\Nayarit\\test"+timeStamp+".txt");

				//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
				FileWriter escribir=new FileWriter(archivo,true);
				
				//Escribimos en el archivo con el metodo write
				escribir.write("Se cerro el navegador por Exception");

				//Cerramos la conexion
				escribir.close();
				System.out.println("Se cerro el navegador por Exception");
				//driver.quit();
				}
			}		
}

