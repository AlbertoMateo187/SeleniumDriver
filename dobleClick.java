package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dobleClick {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		Actions accion = new Actions(driver);
		String url="https://demoqa.com/buttons";
		try {
			driver.get(url);
			//driver.findElement(By.xpath("(//button)[4]")).click();
			//driver.findElement(By.xpath("(//button[@class=\"btn btn-primary\"])[3]")).click();
			WebElement boton=driver.findElement(By.xpath("(//button[@class=\"btn btn-primary\"])[3]"));
			String id=boton.getAttribute("id");
			boton=driver.findElement(By.id(id));
			boton.click();
			Thread.sleep(2000);
			driver.quit();	
		}

		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			}
	}

}
