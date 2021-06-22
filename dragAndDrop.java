package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragAndDrop {
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions accion;
	static {
		driver=getDriver();
		jse=getJse();
		accion=getAccion();
	}
	public static  WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		return driver;
	}
	public static JavascriptExecutor getJse() {
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		return jse;
	}
	public static Actions getAccion() {
		Actions accion= new Actions(driver);
		return accion;
	}
	public static void main(String[] args) {
		try {
			String URL = "http://demoqa.com/";
			driver.get(URL);
			driver.manage().window().maximize();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@class=\"card-up\"]")).click();
			Thread.sleep(4000);
			int pos=0;
			//pos= driver.findElement(By.xpath("(//li[@class=\"block13 ui-draggable\"])[1]")).getLocation().y;
			//jse.executeScript("window.scrollBy(0,1000)");
			List <WebElement> menu=driver.findElements(By.xpath("//div[@class=\"element-group\"]"));
			for(WebElement e:menu) {
			if(e.getText().contains("Interactions")) {
			pos=e.getLocation().y;
			System.out.println(pos);
			jse.executeScript("scroll(0,"+ pos+");");
			Thread.sleep(4000);
			e.click();
			Thread.sleep(4000);
			break;
			}
			}
			jse.executeScript("scroll(0,+ 1000);");
			List <WebElement>submenu=driver.findElements(By.xpath("//li[@id=\"item-3\"]"));
			System.out.println(submenu.size());
			for(WebElement f:submenu) {
			if(f.getText().contains("Droppable")) {
			f.click();
			Thread.sleep(4000);
			break;
			}
			}
			
			WebElement inicio = driver.findElement(By.id("draggable"));
			WebElement fin=driver.findElement(By.id("droppable"));
			
			accion.dragAndDrop(inicio, fin).perform();
			
		
			Thread.sleep(4000);
			System.out.println("Se realizo el drag and drop");
			System.out.println(fin.getText());
			driver.quit();
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}

	}

	}


