package semana1;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class examen {
	public static WebDriver driver;
	public static JavascriptExecutor jse;
	static {
		driver=getDriver();
		jse=getJse();
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
	public static void main(String[] args) throws InterruptedException{
		try {
			String url="https://www.google.com";
			examen puente= new examen();
			driver.get(url);
			driver.manage().window().maximize();
			//Ingresar a Demo Guru
			puente.demoGuru();
			//Ingresar a Guru99 Payment Gateway
			puente.payment();
			//Obtener tarjeta bancaria
			puente.copiarTarjeta();
			//Confirmar compra
			puente.closeVideo();
			puente.confirmacion();
			driver.quit();		
}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			//driver.quit();
		}
	}
	public void demoGuru() throws InterruptedException {
		String pagina="demo-guru";
		Actions accion= new Actions(driver);
		driver.findElement(By.name("q")).sendKeys(pagina);
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.tagName("h3")).click();
		/*
		driver.findElement(By.xpath("//button[@id=\"details-button\"]")).click();
		driver.findElement(By.id("proceed-link")).click();
		*/
		String url=driver.getCurrentUrl();
		String urlOK="http://demo.guru99.com/V4/";
		System.out.println(url);
		if(url.contains(urlOK)) {
		System.out.println("La página es la correcta");
		}
		else {
			System.out.println("La página es incorrecta");
		}
	}
	public void payment() throws InterruptedException {
		driver.findElement(By.linkText("Payment Gateway Project")).click();
		Thread.sleep(1000);
		jse.executeScript ("window.scrollBy(0,400)");
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		driver.findElement(By.id("closeBtn")).click();
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebElement cantidad=driver.findElement(By.name("quantity"));
		Select Quantity=new Select(cantidad);
		Quantity.selectByIndex(8);
		Thread.sleep(1000);
		driver.findElement(By.tagName("input")).submit();
	}
	public void copiarTarjeta() throws InterruptedException {
		Thread.sleep(5000);
		String numTarjeta="";
		String num=""; 
		String numCVV="";
		String expAño="";
		String expMes="";
		int a=1;
		driver.findElement(By.linkText("Generate Card Number")).click();
		Thread.sleep(2000);
		ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		jse.executeScript("window.scrollBy(0,800)");
		List <WebElement> tarjeta=driver.findElements(By.tagName("h4"));
			for(WebElement e:tarjeta) {
				
					switch (a) 
					{
						case 1:  
							num=e.getText();
							numTarjeta = num.substring(num.length()-16,num.length());
							System.out.println("Número de tarjeta::::::::::::"+ numTarjeta);      
							break;
						case 2:  
							num=e.getText();
							numCVV = num.substring(num.length()-3,num.length());
							System.out.println("Número de CVV::::::::::::"+numCVV);
							break;
						case 3:  
							num=e.getText();
							expAño = num.substring(num.length()-4,num.length());
							System.out.println("el Año es::::::::::::"+expAño);
							expMes = num.substring(num.length()-7,num.length()-5);
							System.out.println("El Mes es::::::::::::"+expMes);
							break;
	        }
			a=a+1;
		}
		//Regresar a la pantalla de pago
		driver.close();
		driver.switchTo().window(tabs.get(0));
		//Validar estar en Pantalla pago
		Thread.sleep(5000);
		WebElement titulo=driver.findElement(By.tagName("h2"));
		if(titulo.getText().contains("Payment Process")) {
			System.out.println("Estamos en la página para pagar");
		}
		else {
			System.out.println("No estamos en la página para pagar");
		}
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		System.out.println("Entre al frame");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id=\"closeBtn\"]")).click();
		driver.switchTo().defaultContent();
		//Ingresar los datos de la tarjeta
		driver.findElement(By.name("card_nmuber")).sendKeys(numTarjeta);
		driver.findElement(By.name("cvv_code")).sendKeys(numCVV);
		WebElement mesExp=driver.findElement(By.id("month"));
		Select mes= new Select(mesExp);
		WebElement añoExp=driver.findElement(By.id("year"));
		Select año= new Select(añoExp);
		mes.selectByVisibleText(expMes);
		año.selectByVisibleText(expAño);
		System.out.println("Se ingresarón los datos de la tarjeta exitosamente");
		driver.findElement(By.name("submit")).click();
		System.out.println("Se realizo la compra");
		Thread.sleep(4000);
	}	
	public void confirmacion() throws InterruptedException {
		Thread.sleep(5000);
		WebElement titulo=driver.findElement(By.tagName("h2"));
		System.out.println("Estamos en la página :::::::::" + titulo.getText());
		WebElement orden=driver.findElement(By.xpath("(//td[@align=\"center\"])[2]"));
		System.out.println("El número de orden es::::::::" + orden.getText());
		driver.findElement(By.xpath("//a[@class=\"button special\"]")).click();
		System.out.println("Se finalizo el flujo de la compra");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void closeVideo() throws InterruptedException {
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		System.out.println("Entre al frame");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@id=\"closeBtn\"]")).click();
		driver.switchTo().defaultContent();
	}
}