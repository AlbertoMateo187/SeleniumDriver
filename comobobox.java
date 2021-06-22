package semana1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class comobobox {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			JavascriptExecutor js=(JavascriptExecutor) driver;
			String url="https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";
			url="https://select2.org/getting-started/basic-usage";
			driver.get(url);
			driver.manage().window().maximize();
			js.executeScript("window.scrollBy(0,1000)");
			WebElement combo=driver.findElement(By.xpath("(//select)[3]"));
			Select combobox= new Select (combo);
			System.out.println(combobox.isMultiple());
			Thread.sleep(2000);
			for(int a=0;a<5;a++) {
				combobox.selectByIndex(a);
				Thread.sleep(1000);
			}
			for(int a=0;a<5;a++) {
				combobox.deselectByIndex(a);
				Thread.sleep(1000);
			}
			Thread.sleep(2000);
			
			
Thread.sleep(2000);
			driver.quit();
			
			/*
			WebElement opc=driver.findElement(By.id("select-demo"));
			Select combobox= new Select (opc);
			combobox.selectByIndex(5);
			Thread.sleep(2000);
			combobox.selectByValue("Monday");
			Thread.sleep(2000);
			combobox.selectByVisibleText("Friday");
			Thread.sleep(2000);
			*/
			
	}

}
