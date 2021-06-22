package semana1;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class alertas {

public static void main(String[] args)  throws InterruptedException{
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();

	String URL = "https://www.seleniumeasy.com/test/javascript-alert-box-demo.html";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	try {
		driver.get(URL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		String alerta=null;
		driver.findElement(By.xpath("//button[@class=\"btn btn-default\"]")).click();
		Thread.sleep(5000);
		alerta=driver.switchTo().alert().getText();
		System.out.println(alerta);
		driver.switchTo().alert().accept();
		Thread.sleep(2000);

driver.findElement(By.xpath("//button[@class=\"btn btn-default btn-lg\"]")).click();
Thread.sleep(4000);
alerta=driver.switchTo().alert().getText();
System.out.println(alerta);
driver.switchTo().alert().dismiss();
Thread.sleep(2000);
System.out.println(driver.findElement(By.id("confirm-demo")).getText());
driver.findElement(By.xpath("//button[@onclick=\"myPromptFunction()\"]")).click();
Thread.sleep(4000);

driver.switchTo().alert().sendKeys("Pantaleon");
Thread.sleep(4000);

alerta=driver.switchTo().alert().getText();
System.out.println(alerta);
driver.switchTo().alert().dismiss();
System.out.println(driver.findElement(By.id("prompt-demo")).getText());

//driver.quit();

} catch (Exception e){
e.printStackTrace();
driver.quit();
}

}

}


