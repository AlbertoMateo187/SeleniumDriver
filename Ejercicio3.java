package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio3 {
		
		public static void main(String[] args) throws InterruptedException  {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			try {
			
			String texto="Gobierno de Nayarit";
			String url="https://www.google.com";
			driver.get(url);
			driver.manage().window().maximize();
			driver.findElement(By.name("q")).sendKeys(texto);
			Thread.sleep(3000);
			driver.findElement(By.name("u")).clear();
			//driver.close();
			driver.quit();
			}
		
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			}
		}

	}


