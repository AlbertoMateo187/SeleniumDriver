package semana1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class teclas {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions accion= new Actions(driver);
		String url="https://sodocumentation.net/es/selenium-webdriver/topic/6048/manejar-una-alerta";
		driver.get(url);
		WebElement titulo=driver.findElement(By.xpath("(//h2)[3]"));
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		
		Thread.sleep(2000);
		//accion.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0065')).perform();
		Action keydown = accion.keyDown(Keys.CONTROL).sendKeys("j").build();
		  keydown.perform();
		//driver.findElement(By.tagName("h1")).sendKeys(selectAll);
		Thread.sleep(2000);
		//driver.quit();
	}

}
