package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragDrop {

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
			driver.findElement(By.xpath("(//a[@role=\"tab\"])[2]")).click();
			WebElement inicioA=driver.findElement(By.id("acceptable"));
			WebElement inicioB=driver.findElement(By.xpath("//div[@id=\"notAcceptable\"]"));
			WebElement fin=driver.findElement(By.xpath("(//div[@id=\"droppable\"])[2]"));
			accion.dragAndDrop(inicioA, fin).perform();
			Thread.sleep(3000);
			accion.dragAndDropBy(inicioA, -300, 0).perform();
			Thread.sleep(3000);
			accion.dragAndDrop(inicioB, fin).perform();
			Thread.sleep(3000);
			accion.dragAndDropBy(inicioB, -300, 0).perform();
			Thread.sleep(3000);
			System.out.println("Se pasaron los 2 cuadros");
			Thread.sleep(3000);
			driver.quit();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
	}
	}