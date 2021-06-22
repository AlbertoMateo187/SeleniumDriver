package semana1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;



public class datePicker {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		
		String url="https://www.google.com";
		driver.get(url);
		url="https://www.seleniumeasy.com/test/bootstrap-date-picker-demo.html";
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.findElement(By.className("form-control")).click();
		Thread.sleep(1000);
	WebElement mes=driver.findElement(By.xpath("//th[@class=\"datepicker-switch\"]"));
		//System.out.println(mes.getText());
		WebElement anterior=driver.findElement(By.xpath("//th[@class=\"prev\"]"));
		for(int a=1;a<=100;a++) {
			System.out.println(mes.getText());
			if(mes.getText().contains("July 2018")) {
				break;
			}
			anterior.click();
		}
		Thread.sleep(2000);
		List <WebElement> dia=driver.findElements(By.tagName("td"));
		System.out.println(dia.size());
		for(WebElement e:dia) {
			System.out.println(e.getText());
			if(e.getText().equals("7")) {
				e.click();
				Thread.sleep(3000);
				break;
			}
		}
		
		/*
			if(mes.getText().contains("July 2018")) {
				break;
			}
			anterior.click();
			
			
			
		}*/
		/*
		url="https://demoqa.com/date-picker";
		driver.navigate().to(url);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class=\"react-datepicker-wrapper\"]")).click();
		Thread.sleep(1000);
		WebElement mes=driver.findElement(By.xpath("//div[@class=\"react-datepicker__header\"]"));
		System.out.println(mes.getText());
		List <WebElement> next=driver.findElements(By.xpath("//button[@aria-label=\"Next Month\"]"));
		for(WebElement e:next) {
			if(mes.getText().contains("Abril")) {
				break;
			}
			e.click();
			Thread.sleep(2000);
		}
		List<WebElement> dias=driver.findElements(By.xpath("//div[@tabindex=\"-1\"]"));
		System.out.println(dias.size());
		for(WebElement e:dias) {
			//System.out.println(e.getText());
			if(e.getText().equals("8")){
				e.click();
				break;
		}
		}*/
		//driver.quit();
		}
		
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador por excepción");
			driver.quit();
		}

	}

}
