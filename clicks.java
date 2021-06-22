package semana1;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class clicks {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		String url="https://www.google.com";
		driver.get(url);
		url="http://demo.guru99.com/test/simple_context_menu.html";
		driver.navigate().to(url);
		driver.manage().window().maximize();
		Actions accion= new Actions(driver);
		WebElement CD=driver.findElement(By.tagName("span"));
		accion.contextClick(CD).perform();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@class=\"context-menu-item context-menu-icon context-menu-icon-edit\"]")).click();
		Thread.sleep(1000);
		System.out.println("Se dio click derecho al botón");
		Thread.sleep(3000);
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		
		Thread.sleep(2000);
		WebElement DC=driver.findElement(By.tagName("button"));
		accion.doubleClick(DC).perform(); 
		System.out.println("Se dio doble click al botón");
		Thread.sleep(3000);	
		driver.quit();
}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
	}
}