package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class combo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		String url="https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";
		try {
			/*
		driver.get(url);
		String dia="Monday";
		WebElement combo=driver.findElement(By.tagName("select"));
		Select variable= new Select (combo);
		variable.selectByIndex(4);
		variable.selectByValue(dia);
		variable.selectByVisibleText("Friday");
		Thread.sleep(4000);*/
		url="https://demoqa.com/select-menu";
		driver.get(url);
		String color="9";
		WebElement combo=null;
		combo=driver.findElement(By.xpath("//div[@class=\" css-1hwfws3\"]"));
		combo.click();
		Thread.sleep(4000);
		driver.quit();
		

		
		}
		catch (Exception e) {
			e.printStackTrace();
			driver.quit();
			}


	}

}
