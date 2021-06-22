package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class arrastrar {
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
				String URL="http://demo.guru99.com/test/drag_drop.html";
				driver.get(URL);
				driver.manage().window().maximize();
				Thread.sleep(1000);
				jse.executeScript("scroll(0,200);");
				WebElement inicio=driver.findElement(By.xpath("(//a[@class=\"button button-orange\"])[2]"));
				WebElement fin=driver.findElement(By.xpath("//ol[@class=\"field13 ui-droppable ui-sortable\"]"));
				accion.dragAndDrop(inicio,fin).perform();
				Thread.sleep(3000);
				inicio=driver.findElement(By.xpath("(//a[@class=\"button button-orange\"])[4]"));
				fin=driver.findElement(By.xpath("(//ol[@class=\"field13 ui-droppable ui-sortable\"])[2]"));
				accion.dragAndDrop(inicio,fin).perform();
				Thread.sleep(3000);
				inicio=driver.findElement(By.xpath("(//a[@class=\"button button-orange\"])[5]"));
				fin=driver.findElement(By.xpath("//ol[@class=\"field14 ui-droppable ui-sortable\"]"));
				accion.dragAndDrop(inicio,fin).perform();
				Thread.sleep(3000);
				inicio=driver.findElement(By.xpath("(//a[@class=\"button button-orange\"])[6]"));
				fin=driver.findElement(By.xpath("//ol[@class=\"field15 ui-droppable ui-sortable\"]"));
				accion.dragAndDrop(inicio,fin).perform();
				Thread.sleep(3000);
				//driver.quit();
	}
	
	catch (Exception e){
		e.printStackTrace();
		System.out.println("Se cerro el navegador por excepción");
		driver.quit();
	}
}
}
