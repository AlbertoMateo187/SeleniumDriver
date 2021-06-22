package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class maximizar {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://www.toolsqa.com/");
			driver.manage().window().maximize();
			WebElement menu=driver.findElement(By.xpath("(//span[@class=\"menu-text\"])[2]"));
			List <WebElement> lista=driver.findElements(By.xpath("//span[@class=\"menu-text\"]"));
			System.out.println(lista.size());
			Actions accion = new Actions(driver);
			accion.moveToElement(menu).perform();
			Thread.sleep(4000);
			menu=driver.findElement(By.xpath("(//span[@class=\"menu-text\"])[3]"));
			accion.moveToElement(menu).perform();
			Thread.sleep(4000);
			menu=driver.findElement(By.linkText("Agile & Scrum"));
			accion.moveToElement(menu).perform();
			menu.click();
			//driver.quit();

			

	}

}
