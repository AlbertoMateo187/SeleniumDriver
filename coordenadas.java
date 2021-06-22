package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class coordenadas {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String url="https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";
		try {
		driver.get(url);
		
		WebElement campo=driver.findElement(By.id("printMe"));
		//campo.click();
		int b,c;
				b=campo.getLocation().x;
				c=campo.getLocation().y;
		System.out.println(campo.getLocation());
		
		js.executeScript("scroll(" +b+ ""+c+");");
		Thread.sleep(5000);
		campo.click();
		driver.quit();
	}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			}

	}

}
