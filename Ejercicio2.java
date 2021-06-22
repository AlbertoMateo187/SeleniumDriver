package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String url="https://www.google.com";
		String texto="Wikipedia";
		driver.get(url);
		driver.manage().window().maximize();
		WebElement buscar=driver.findElement(By.name("q"));
		buscar.sendKeys(texto);
		buscar.sendKeys(Keys.ENTER);
		driver.findElement(By.tagName("h3")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Portal de la comunidad")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("Políticas")).click();
		//driver.findElement(By.cssSelector("a[accesskey=\"e\"]")).click();
		driver.findElement(By.xpath("//a[@accesskey=\"e\"]")).click();
		Thread.sleep(5000);
		System.out.println(driver.findElement(By.id("firstHeading")).getText());
		driver.quit();
		
	}
}
