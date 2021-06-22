package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class mouseOver {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		
		String url="https://www.google.com";
		driver.get(url);
		url="https://www.toolsqa.com/";
		driver.navigate().to(url);
		driver.manage().window().maximize();
		Actions accion= new Actions(driver);
		WebElement menu=driver.findElement(By.xpath("//li[@class=\"menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-item-26284 has-children\"]"));
		System.out.println("LOG:::::::::::::::::::::::::::       Encontre el link");
		accion.moveToElement(menu).perform();
		System.out.println("LOG:::::::::::::::::::::::        se desplego el menú");
		Thread.sleep(2000);
		
		WebElement opcion=driver.findElement(By.linkText("QA Practices"));
		accion.moveToElement(opcion).perform();
		System.out.println("LOG:::::::::::::::::::::::        se desplego el submenu");
		Thread.sleep(2000);
		WebElement opcion2=driver.findElement(By.linkText("Agile & Scrum"));
		opcion2.click();
		System.out.println("LOG:::::::::::::::::::::::        Se dio click");
		WebElement titulo=driver.findElement(By.tagName("h1"));
		Thread.sleep(2000);
		if(titulo.getText().contains("All you want to know about Agile & Scrum")) {
			System.out.println("LOG:::::::::::::::::::::::        Entramos a la página correcta");
		}
		else {
			System.out.println("LOG:::::::::::::::::::::::::::::::No entramos a la página correcta");
		}
		driver.quit();
	}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}
	}
}
