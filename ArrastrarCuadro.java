package semana1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ArrastrarCuadro {

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
int pos=0 ,pos1= 1;
WebElement fin = null;
Actions accion = new Actions(driver);
WebElement cuadro=null;
	driver.get(URL);
	driver.manage().window().maximize();
	driver.findElement(By.xpath("//a[@class=\"fancybox\" and @href=\"#login\"]")).click();
	driver.findElement(By.xpath("(//input[@type=\"text\" and @name=\"username\"])[2]")).sendKeys("kell123");
	driver.findElement(By.xpath("(//input[@type=\"password\" and @name=\"password\"])[2]")).sendKeys("Qandyland123");
	driver.findElement(By.xpath("(//input[@type=\"submit\" and @class=\"button\"])[2]")).submit();
	Thread.sleep(2000);
	driver.navigate().to("http://www.way2automation.com/way2auto_jquery/draggable.php");
	//Thread.sleep(2000);
	//Ingresar al frame
	driver.switchTo().frame(0);
	System.out.println("Entramos al frame");
	cuadro=driver.findElement(By.xpath("//div[@id=\"draggable\"]"));
	//Salir del frame para bajar el scroll
	driver.switchTo().defaultContent();
	jse.executeScript("scroll(0,300);");
	System.out.println("Se bajo el scroll ");
	driver.switchTo().frame(0);
	int a=0;
	for (a=0;a<=35;a++) {
		accion.dragAndDropBy(cuadro,20,0).perform();
			}
	for (int y=0; y<15; y++) {
		accion.dragAndDropBy(cuadro,0,20).perform();
	}
	for (a=0;a<=35;a++) {
		accion.dragAndDropBy(cuadro,-20,0).perform();
			}
	for (a=0;a<=15;a++) {
		accion.dragAndDropBy(cuadro,0,-20).perform();
			}
	driver.quit();
}

catch (Exception e){
	e.printStackTrace();
	System.out.println("Se cerro el navegador por excepción");
	//driver.quit();
}
}
}
