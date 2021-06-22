package semana1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class array {



		public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		Actions accion = new Actions(driver);
		Thread.sleep(2000);
		//Flujo para seleccionar varios registros
		String URL = "http://demoqa.com/elements";
		driver.get(URL);
		jse.executeScript("scroll(0,1000);");
		List <WebElement> menu=driver.findElements(By.xpath("//div[@class=\"header-text\"]"));
		Thread.sleep(2000);
		for(WebElement e:menu) {
		if(e.getText().contains("Interactions")) {
		e.click();
		Thread.sleep(2000);
		jse.executeScript("scroll(0,1000);");
		break;
		}
		}
		List <WebElement> submenu=driver.findElements(By.xpath("//span[@class=\"text\"]"));
		Thread.sleep(2000);
		for(WebElement f:submenu) {
		if(f.getText().contains("Selectable")) {
		f.click();
		Thread.sleep(1000);
		break;
		}
		}
		driver.findElement(By.xpath("//a[@id=\"demo-tab-grid\"]")).click();
		List <WebElement> opc=driver.findElements(By.xpath("//li[@class=\"list-group-item list-group-item-action\"]"));
		System.out.println(opc.size());
		
		String[] numeros = {"one","five","six","nine"};
		System.out.println(numeros.length);
		
		for(int i=0;i<numeros.length;i++) {
		for (WebElement e:opc) {
		if(e.getText().contains(numeros[i])){
		accion.click(e).perform();
		//break;
		}
		}
		}
		Thread.sleep(5000);
		
		//driver.quit();
		}
		}



