package flujosHS;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class vacaciones1 {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://qa_knr.humansite.com.mx/hlogin.aspx");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		//Login
		WebElement Company = driver.findElement(By.cssSelector("input[id=\"vMB_EPR_COD\"]"));
		WebElement User = driver.findElement(By.cssSelector("input[id=\"vUSUID\"]"));
		WebElement Pass = driver.findElement(By.cssSelector("input[id=\"vUSUPSW\"]"));
		WebElement In = driver.findElement(By.cssSelector("input[name=\"vIMAGEN8\"]"));
		Company.sendKeys("KNR");
		User.sendKeys("EQUIPOQA");
		Pass.sendKeys("124578");
		JavascriptExecutor scroll = (JavascriptExecutor) driver;
		In.click();
		Thread.sleep(10000);
		
		WebElement Nómina = driver.findElement(By.linkText("Nómina"));
		Nómina.click();
		System.out.println("Log.............................Nómina");
		Thread.sleep(3000);
		WebElement Incidencias = driver.findElement(By.linkText("Incidencias"));
		Incidencias.click();
		System.out.println("Log.............................Pestaña Incidencias seleccionado correctamente");
		Thread.sleep(2000);
		String alerta = driver.switchTo().alert().getText();
		System.out.println("Log............................." +alerta+ "(Alerta)");
		driver.switchTo().alert().accept();
		System.out.println("Log.............................Alerta aceptada");
		Thread.sleep(2000);
		WebElement Nom = driver.findElement(By.name("IMGNOM"));
		Nom.click();
		System.out.println("Log.............................Se accedió a la selección de Nómina correctamente");
		Thread.sleep(2000);
		driver.switchTo().frame(1);
		System.out.println("Log.............................Se accedió a la sección de Nómina");
		Thread.sleep(2000);
		WebElement NOM = driver.findElement(By.cssSelector("input[id=\"vLINKSELECTION_0003\"]"));
		NOM.click();
		System.out.println("Log.............................Se seleccionó tipo de nómina");
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input[name=\"BTN_ACTUALIZAR\"]")).click();
		System.out.println("Log.............................Se actualizó la pantalla nómina");
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		WebElement No_Employed = driver.findElement(By.name("vEMP_NIEC"));
		No_Employed.sendKeys("51");
		System.out.println("Log.............................Se ingresó el número de empleado correctamente");
		Thread.sleep(2000);
		WebElement Lupa_Nom = driver.findElement(By.name("CON"));
		Lupa_Nom.click();
		Thread.sleep(2000);
		//driver.switchTo().defaultContent();
		//scroll.executeScript("window.scrollBy(0,150)");
		Thread.sleep(1000);
		driver.switchTo().frame(0);
		
		Thread.sleep(2000);
		//driver.switchTo().frame(1);
		
		List <WebElement> campos=driver.findElements(By.xpath("//input[@placeholder=\"Descripción\"]"));
		System.out.println(campos.size());
		driver.findElement(By.id("vC286")).sendKeys("test");
		System.out.println("Encontre el elemento");
		Thread.sleep(5000);
		
		/*
		System.out.println("Log.............................Se accedió al catálogo de incidencias correctamente");
		driver.findElement(By.id("vC286")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.name("BTNBUSCARNOM")).click();
		System.out.println("Log.............................Búsqueda de incidencias correcto");
		driver.findElement(By.cssSelector("img[id=\"vLINKSELECTION_0002\"]")).click();
		System.out.println("Log.............................Incidencia seleccionada correctamente");
		driver.switchTo().defaultContent();
		*/
		
		//driver.quit();
		}
}
