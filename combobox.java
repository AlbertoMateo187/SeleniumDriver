package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class combobox {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.google.com");
		driver.navigate().to("https://demoqa.com/select-menu");
		Thread.sleep(2000);
		Actions accion= new Actions(driver);
		/*
		
		WebElement combo2=driver.findElement(By.id("oldSelectMenu"));
		Select facility = new Select (combo2);
		facility.
		System.out.println(facility.getOptions().get(2));
		driver.quit();*/
		WebElement combo=driver.findElement(By.xpath("//div[@class=\" css-2b097c-container\"]"));
				combo.click();
				Thread.sleep(6000);
				driver.findElement(By.xpath("//div[@class=\"css-1g6gooi\"]")).sendKeys("A");
		Thread.sleep(2000);
		//WebElement opcion=driver.findElement(By.linkText("Group 1, option 1"));
		//accion.moveToElement(opcion).perform();
	
	}

}
