package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class moverElemento {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		String url="https://www.toolsqa.com/";
		driver.get(url);
		driver.manage().window().maximize();
		Actions accion = new Actions(driver);
		WebElement nombre=driver.findElement(By.linkText("VIDEOS"));
		accion.moveToElement(nombre).perform();
		Thread.sleep(2000);
		WebElement link=driver.findElement(By.partialLinkText("Paid"));
		accion.moveToElement(link).perform();
		link.click();
		Thread.sleep(2000);
		driver.quit();

		}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			}
	}

}
