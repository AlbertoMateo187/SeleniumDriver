package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class radiobuttons {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		
		String url="https://www.google.com";
		driver.get(url);
		url="http://demo.guru99.com/test/radio.html#";
		driver.navigate().to(url);
		List <WebElement> radios=driver.findElements(By.name("webform"));
		radios.get(2).click();
		Thread.sleep(2000);
		url="https://www.seleniumeasy.com/test/basic-radiobutton-demo.html";
		driver.navigate().to(url);
		//radios = driver.findElements(By.xpath("//input[@type=\"radio\"]"));
		
		radios=driver.findElements(By.name("optradio"));
		System.out.println(radios.size());
		for(WebElement a: radios) {
			System.out.println(a.getAttribute("value"));
			System.out.println(a.isEnabled());
			a.click();
			System.out.println(a.isSelected());
		}
		radios=driver.findElements(By.name("gender"));
		System.out.println(radios.size());
		for(WebElement a: radios) {
			System.out.println(a.getAttribute("value"));
			System.out.println(a.isEnabled());
			a.click();
			System.out.println(a.isSelected());
			
		}
		radios=driver.findElements(By.name("ageGroup"));
		System.out.println(radios.size());
		for(WebElement a: radios) {
			System.out.println(a.getAttribute("value"));
			System.out.println(a.isEnabled());
			a.click();
			System.out.println(a.isSelected());
			
		}
		url="https://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html";
		driver.navigate().to(url);
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,1000)");

		driver.findElement(By.xpath("(//span[@class=\"select2-selection__arrow\"])[2]")).click();
		radios=driver.findElements(By.xpath("//li[@role=\"treeitem\"]"));
		 Boolean isChecked=driver.findElement(By.xpath("//li[@role=\"treeitem\"]")).isEnabled();
		for(WebElement a:radios) {
			
			System.out.println(a.getText()+a.isSelected()+"       "+ a.getAttribute("aria-disabled"));
			//a.click();
			System.out.println(isChecked);
		}
		
		driver.quit();
			
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador excepción");
			driver.quit();
		}
	}
		

}
