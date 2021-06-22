package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class selecVarios {
	public static WebDriver driver;
	public static Actions accion;
	public static JavascriptExecutor jse;
		static {
		driver=getDriver();
		accion=getAccion();
		jse=getJse();
		}
		public static  WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			return driver;
		}
		public static Actions getAccion() {
			Actions accion= new Actions(driver);
			return accion;
		}
		public static JavascriptExecutor getJse() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			return jse;
			}
		
		public static void main(String[] args) throws InterruptedException {
			driver.manage().window().maximize();
			//JavascriptExecutor jse=new JavascriptExecutor ();
			Thread.sleep(2000);
			//Flujo para seleccionar varios registros
			String URL = "http://www.way2automation.com/way2auto_jquery/";
			try {
			driver.get(URL);
			selecVarios puente=new selecVarios();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//a[@class=\"fancybox\" and @href=\"#login\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@type=\"text\" and @name=\"username\"])[2]")).sendKeys("kell123");
			driver.findElement(By.xpath("(//input[@type=\"password\" and @name=\"password\"])[2]")).sendKeys("Qandyland123");
			driver.findElement(By.xpath("(//input[@type=\"submit\" and @class=\"button\"])[2]")).submit();
			Thread.sleep(5000);
			WebElement menu=driver.findElement(By.xpath("//a[@href=\"javascript:\"]"));
			accion.moveToElement(menu).perform();
			WebElement subMenu=driver.findElement(By.xpath("//a[@href=\"selectable.php\"]"));
			accion.moveToElement(subMenu).perform();
			subMenu.click();
			/*
			driver.navigate().to("http://www.way2automation.com/way2auto_jquery/selectable.php");
			driver.findElement(By.xpath("//a[@href=\"#example-1-tab-3\"]")).click();
			*/
			driver.switchTo().frame(0);
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//a[@target=\"_self\"]")).click();
			driver.switchTo().frame(0);
			List <WebElement>lista=driver.findElements(By.xpath("//li[contains(@class,\"ui-widget-content\")]"));
			Thread.sleep(3000);
			System.out.println(lista.size());
			int a=lista.size();
			/*
			WebElement inicio= driver.findElement(By.xpath("(//li[@class=\"ui-widget-content ui-selectee\"])[2]"));
			WebElement fin=driver.findElement(By.xpath("(//li[@class=\"ui-widget-content ui-selectee\"])["+a+"]"));
			
			
			for (WebElement e:lista) {
			if(e.getText().contains("Item 2")) {
			 inicio=e;
			}
			if(e.getText().contains("Item"+" "+ a)) {
				 fin=e;
			}
			}
			
			
			
			accion.clickAndHold(inicio).moveToElement(fin).perform();//se declara de donde inicia la selección inicio a donde va terminar fin
			Thread.sleep(3000);
			*/
			
			//Seleccionar varios mediante tecla control
			accion.keyDown(Keys.CONTROL);//keydown es para mantener apretada una tecla, keys.control para decir que tecla se va seleccionar
			Thread.sleep(1000);
			for (WebElement e:lista) {
			
			//if(e.getText().contains("Item 1")||e.getText().contains("Item 4")||e.getText().contains("Item 5")) {
			
				accion.click(e).perform();
			//}
			}
			Thread.sleep(5000);
			puente.cuadro();
			driver.quit();
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Se cerro el navegador por excepción");
				driver.quit();
			}
		}
			public  void cuadro() throws InterruptedException{
				int numero=0;
				driver.switchTo().defaultContent();
				driver.findElement(By.xpath("//a[@href=\"#example-1-tab-2\"]")).click();
				driver.switchTo().frame(1);
				List <WebElement>lista2=driver.findElements(By.xpath("//li[@class=\"ui-state-default ui-selectee\"]"));
				Thread.sleep(3000);
				System.out.println(lista2.size());
				for(WebElement e:lista2) {
				numero=Integer.parseInt(e.getText());
				if(numero%2==0) {
					accion.click(e).perform();
				}
				}
				
				}
			}



