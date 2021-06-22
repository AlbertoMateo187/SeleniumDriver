package semana1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class windows {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	static {
		driver=getDriver();
		jse=getJse();
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
	public static void main(String[] args) {
		try {
			String url="https://demoqa.com/browser-windows";
			driver.get(url);
			driver.manage().window().maximize();
			driver.findElement(By.id("tabButton")).click();
			Thread.sleep(1000);
			System.out.println("Los id de mi navegador son :::::: " + driver.getWindowHandles());
			ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
			System.out.println(tabs);
			driver.switchTo().window(tabs.get(1));
			System.out.println(driver.findElement(By.tagName("h1")).getText());
			Thread.sleep(2000);
			//driver.switchTo().window(tabs.get(0));
			Thread.sleep(4000);
			String tituloPagina = driver.getTitle();
			System.out.println(driver.getTitle());
			System.out.println("La url actual es :::" +driver.getCurrentUrl());
			
			driver.switchTo().window(tabs.get(0));
			driver.findElement(By.id("windowButton")).click();
			WebElement boton=driver.findElement(By.id("windowButton"));
			tabs = new ArrayList <String>(driver.getWindowHandles());
			System.out.println(tabs);
			driver.switchTo().window(tabs.get(2));
			driver.close();
			driver.switchTo().window(tabs.get(0));
			int a=0;
			a=boton.getLocation().getY();
			System.out.println(a);
			jse.executeScript("scroll(0,"+ a+");");

			//driver.close();
			Thread.sleep(3000);
			/*
			driver.findElement(By.id("messageWindowButton")).click();
			Thread.sleep(1000);
			tabs = new ArrayList <String>(driver.getWindowHandles());
			System.out.println(tabs);
			driver.switchTo().window(tabs.get(1));
			driver.close();
			driver.switchTo().window(tabs.get(0));
			
			driver.quit();
			*/
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}

	}

}
