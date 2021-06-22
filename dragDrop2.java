package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragDrop2 {
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
	public static void main(String[] args) {
		try {
			String URL = "https://demoqa.com/droppable";
			driver.get(URL);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//a[@role=\"tab\"])[3]")).click();
			WebElement ini=driver.findElement(By.id("dragBox"));
			WebElement fin=driver.findElement(By.xpath("(//p)[3]"));
			System.out.println(fin.getLocation().x + "     " + fin.getLocation().y);
			
			int c=fin.getLocation().x;
			int d=ini.getLocation().x;
			int e=c-d;
			
			
			accion.dragAndDropBy(ini, e, 0).perform();
			Thread.sleep(4000);
			
			accion.dragAndDropBy(ini, -e, 0).perform();
			Thread.sleep(4000);
			driver.quit();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
	}
	}

/*
int c=fin.getLocation().x;
			int d=ini.getLocation().x;
			int e=c-d;
			
			accion.dragAndDropBy(ini, e, 0).perform();
			Thread.sleep(4000);
			
			accion.dragAndDropBy(ini, -e, 0).perform();
			Thread.sleep(4000);


*/