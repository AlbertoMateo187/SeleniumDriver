package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class frame {

	public static WebDriver driver;
	static {
		driver=getDriver();
	}
	public static  WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		return driver;
	}
	public static void main(String[] args) {
		try {
			String URL = "https://demoqa.com/frames";
			driver.get(URL);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			driver.switchTo().frame(1);
			WebElement titulo=driver.findElement(By.xpath("(//h1[@id=\"sampleHeading\"])[1]"));
			System.out.println("El título es:::::::::::"  + titulo.getText());
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			titulo=driver.findElement(By.xpath("(//h1[@id=\"sampleHeading\"])[1]"));
			System.out.println("El título es:::::::::::"  + titulo.getText());
			
			
			driver.quit();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
	}
	}