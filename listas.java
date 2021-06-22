package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class listas {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
		String url="https://www.google.com";
		driver.get(url);
		url="https://demoqa.com/checkbox";
		driver.navigate().to(url);
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title=\"Expand all\"]")).click();
		Thread.sleep(2000);
		List <WebElement> lista=driver.findElements(By.xpath("//span[@class=\"rct-title\"]"));
		System.out.println("El número de checkbox son " + lista.size());
		for(WebElement a:lista) {
			System.out.println(a.getText() + a.getLocation());
				a.click();
				js.executeScript("window.scrollBy(0,32)");	
				Thread.sleep(1000);
		}
		js.executeScript("window.scrollBy(0,-400)");
		lista=driver.findElements(By.xpath("//span[@class=\"rct-checkbox\"]"));
		List <WebElement> seleccionados=driver.findElements(By.className("text-success"));
		WebElement texto=driver.findElement(By.id("result"));
		System.out.println(texto.getText());
		for (WebElement b:seleccionados)
		{
			System.out.println(b.getText() );
		}
		driver.quit();
		/*
		List <WebElement> lista2 = driver.findElements(By.xpath("//span[@class=\"text-success\"]"));
		System.out.println("El numero de seleccionados son: " + lista.size());
		
		int i=0;
		
			//if (b.isSelected()) {
			if (b.getAttribute("class").contains("rct-icon-check")) {			    	
		    	i++;
		    	System.out.println("CheckBox con estatus de seleccionado:" + i);
		    }
				
		}
		
		
		List <WebElement> checks=driver.findElements(By.className("text-success"));
		System.out.println("Los checkbox seleccionados son::::::::::");
		for(WebElement e:checks) {
			System.out.println(e.getText());
		}
		
		
		WebElement valores=driver.findElement(By.id("result"));
		System.out.println("::::" +  valores.getText());
		*/
		//driver.quit();
		
		}
		catch (Exception e){
			e.printStackTrace();
			
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
			
			
		}

	}

}
