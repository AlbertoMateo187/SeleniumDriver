package semana1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

	public class enviarArchivo {

		public static WebDriver driver;
		public static JavascriptExecutor jse;

		static {
			driver=getDriver();
			jse=getJse();
			}

		public static WebDriver getDriver() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			return driver;
		}

		public static JavascriptExecutor getJse() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			return jse;
			}

		public static void main(String[] args) throws InterruptedException {

			try {
				String URL = "http://www.way2automation.com/way2auto_jquery/";
				driver.get(URL);
				String ruta="C:\\Users\\Mario\\Desktop\\Eclipse.docx";
				driver.manage().window().maximize();
				driver.findElement(By.xpath("//a[@class=\"fancybox\" and @href=\"#login\"]")).click();
				driver.findElement(By.xpath("(//input[@type=\"text\" and @name=\"username\"])[2]")).sendKeys("kell123");
				driver.findElement(By.xpath("(//input[@type=\"password\" and @name=\"password\"])[2]")).sendKeys("Qandyland123");
				driver.findElement(By.xpath("(//input[@type=\"submit\" and @class=\"button\"])[2]")).submit();
				Thread.sleep(2000);
				driver.navigate().to("http://www.way2automation.com/way2auto_jquery/registration.php");
				
				
				jse.executeScript("scroll(0,500);");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@type=\"file\"]")).sendKeys("C:\\Users\\Mario\\Desktop\\Datos.txt");
				//driver.quit();
				}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Se cerro el navegador por excepción");
				driver.quit();
	}
	}
	}
