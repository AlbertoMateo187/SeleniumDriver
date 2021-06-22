package flujosHS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VacionesFirefox {

	public static void main(String[] args) throws InterruptedException {
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver","C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://qa_knr.humansite.com.mx/hlogin.aspx");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		//Login
		WebElement Company = driver.findElement(By.cssSelector("input[id=\"vMB_EPR_COD\"]"));
		WebElement User = driver.findElement(By.cssSelector("input[id=\"vUSUID\"]"));
		WebElement Pass = driver.findElement(By.cssSelector("input[id=\"vUSUPSW\"]"));
		WebElement In = driver.findElement(By.cssSelector("input[name=\"vIMAGEN8\"]"));
		Company.sendKeys("KNR");
		User.sendKeys("EQUIPOQA");
		Pass.sendKeys("124578");
		JavascriptExecutor scroll = (JavascriptExecutor) driver;
		In.click();
		Thread.sleep(10000);
		
		
		//driver.quit();
		}
}

