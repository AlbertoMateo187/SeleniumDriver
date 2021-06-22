package semana1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class metlife {

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
	
	public static void main(String[] args)  throws InterruptedException{
	
	try {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
	String url = "http://192.241.225.45:36809/";
	//Abrir navegador
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		
		WebElement titulo=driver.findElement(By.tagName("h1"));
		System.out.println("El título del sistema es:::::::   "   + titulo.getText());
		//seleccionar empresa
	
		List <WebElement> text=driver.findElements(By.xpath("//div[@class=\"ant-row mt-3\"]"));
		int c=1;
		for(WebElement a:text) {
			System.out.println(a.getText());
			if(a.getText().contains("ROBA790804B72")) {
				js.executeScript("window.scrollBy(0,500)");
				WebElement empresa=driver.findElement(By.xpath("(//button[@role])["+c+"]"));
				empresa.click();
				System.out.println(c);
				Thread.sleep(2000);
				break;
			}
			c=c+1;
			System.out.println(c);
			}
		js.executeScript("window.scrollBy(0,-500)");
		Thread.sleep(2000);
		//Ingresar a usuarios
		String nombre="Alberto", 
				apellidoP="Mateo",
				apellidoM="Jiménez",
				user="Mateo1995",
				compañia="FUNDACION METLIFE MEXICO A.C";
		driver.findElement(By.xpath("//div[@class=\"ant-menu-submenu-title\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@class=\"ant-menu-item\"]")).click();
		Thread.sleep(2000);
		/*
		driver.findElement(By.xpath("//button[@id=\"createUsrBtn\"]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id=\"nameUser\"]")).sendKeys(nombre);
		driver.findElement(By.xpath("//input[@id=\"surnamePaternal\"]")).sendKeys(apellidoP);
		driver.findElement(By.xpath("//input[@id=\"surnameMaternal\"]")).sendKeys(apellidoM);
		driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys(user);
		Actions accion= new Actions(driver);
		WebElement combo=driver.findElement(By.xpath("//span[@class=\"ant-select-selection-search\"]"));
		combo.click();
		for(int i = 0; i <= 2; i++){
		accion.sendKeys(Keys.DOWN).build().perform();//press down arrow key
		}
		Actions actions = new Actions(driver);
	    actions.sendKeys(Keys.ENTER).build().perform();
	    js.executeScript("window.scrollBy(0,700)");
	    List <WebElement> empresas=driver.findElements(By.xpath("//label[@class=\"ant-checkbox-wrapper\"]"));
	    System.out.println(":::::::::::::::::::::::LISTA DE EMPRESAS:::::::::::::::::::::::::");
	    c=1;
	    for(WebElement e:empresas) {
	    	System.out.println(c+ "-" + e.getText());
	    	if(e.getText().contains(compañia)) {
	    		e.click();
	    	}
	    	c=c+1;
	    }
	    driver.findElement(By.xpath("//button[@id=\"saveBtn\"]")).click();
	    Thread.sleep(5000);*/
		WebElement combo;
		Actions accion= new Actions(driver);
		Actions actions = new Actions(driver);
		List <WebElement> empresas;
	    ////Conocer ID de la empresa
	    js.executeScript("window.scrollBy(0,1100)");
	    combo=driver.findElement(By.xpath("//span[@class=\"ant-select-selection-item\"]"));
	    combo.click();
		for(int i = 0; i <= 2; i++){
		accion.sendKeys(Keys.DOWN).build().perform();//press down arrow key
		}
		actions = new Actions(driver);
	    actions.sendKeys(Keys.ENTER).build().perform();
	    Thread.sleep(5000);
	    js.executeScript("window.scrollBy(0,-800)");
	    Thread.sleep(5000);
	    System.out.println("::::::::::::::Buscar ID::::::::::::::::::");
	    empresas=driver.findElements(By.xpath("//tr[@data-row-key]"));
	    c=1;
	    for(WebElement e:empresas) {
	    	
	    	js.executeScript("window.scrollBy(0,30)");
	    	//System.out.println(c +"-" + e.getText());
	    	if(e.getText().contains(user)) {
	    		WebElement id=driver.findElement(By.xpath("(//td[@class=\"ant-table-cell ant-table-cell-fix-left\"])["+c+"]"));
	    		System.out.println("El id del usuario es ::::" + id.getText());
	    	}
	    	c++;
	    }
	    
		driver.quit();
	}
	catch(Exception e){
	e.printStackTrace();
	System.out.println("Se cerro el navegador por Exception");
	driver.quit();
	}
	}
}
