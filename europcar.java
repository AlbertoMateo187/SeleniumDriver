package Ejercicios;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

public class europcar {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pantaleon.berumen\\Downloads\\chromedriver_win32(1)\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		try {

		String url="https://www.europcar.com.mx/";
		driver.get(url);
		driver.manage().window().maximize();

		Actions accion = new Actions(driver);

		WebElement entrega =driver.findElement(By.id("idcheckoutLocationName"));
		entrega.sendKeys("MEXICO - POLANCO");
		Thread.sleep(2000);

		WebElement opcion=driver.findElement(By.xpath("//a[@onmouseout=\"javascript:seleccionFuera(this)\"]"));
		accion.moveToElement(opcion).perform();
		opcion.click();
		Thread.sleep(2000);

		WebElement btnEnvio =driver.findElement(By.xpath("(//input[@type=\"submit\"])[2]"));
		btnEnvio.click();
		Thread.sleep(7000);

		WebElement cierraVentana =driver.findElement(By.xpath("(//a[@data-dismiss=\"modal\"])[1]"));
		cierraVentana.click();
		Thread.sleep(2000);

		WebElement cierraVentana2 =driver.findElement(By.xpath("(//a[@data-dismiss=\"modal\"])[4]"));
		cierraVentana2.click();
		Thread.sleep(2000);

		WebElement auto =driver.findElement(By.xpath("(//p[@class=\"padL10 fontBold marginD5\"])[1]"));
		auto.click();
		Thread.sleep(6000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		//js.executeScript(“scroll(0,100);”);


		//driver.quit();

		}

		catch (Exception e) {
		e.printStackTrace();
		driver.quit();
		}


		}

		} 
