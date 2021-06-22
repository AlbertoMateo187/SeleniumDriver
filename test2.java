package semana1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class test2 {

	public static void main(String[] args) throws InterruptedException  {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		
		String url="https://www.google.com";
		driver.get(url);
		url="https://es.wikipedia.org/w/index.php?title=Especial:Entrar&returnto=Selenium";
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("wpName1")).sendKeys("12345");
		Thread.sleep(2000);
		driver.findElement(By.id("wpName000")).clear();
		Thread.sleep(2000);
		driver.navigate().back();
		driver.navigate().forward();
		//driver.close();
		driver.quit();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
	}

}
