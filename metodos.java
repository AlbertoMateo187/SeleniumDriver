package semana1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class metodos {
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions accion;
	static {
		driver=getDriver();
		jse=getJse();
		accion=getAccion();
	}
	public static  WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		return driver;
	}
	public static JavascriptExecutor getJse() {
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		return jse;
	}
	public static Actions getAccion() {
		Actions accion= new Actions(driver);
		return accion;
	}
	public static void main(String[] args) throws InterruptedException{
		try {
			String url="http://demo.guru99.com/test/simple_context_menu.html";
			metodos puente=new metodos();
			driver.get(url);
			driver.manage().window().maximize();
			puente.clickDerecho();
			Thread.sleep(2000);
			puente.dobleClick();
			driver.quit();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
		}
	public  void clickDerecho() throws InterruptedException{
		WebElement CD=driver.findElement(By.tagName("span"));
		accion.contextClick(CD).perform();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[@class=\"context-menu-item context-menu-icon context-menu-icon-edit\"]")).click();
		Thread.sleep(2000);
		//System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();	
		}
	public  void dobleClick() throws InterruptedException{
		WebElement DC=driver.findElement(By.tagName("button"));
		accion.doubleClick(DC).perform(); 
		Thread.sleep(1000);	
		System.out.println(driver.switchTo().alert().getText());
	}
	}