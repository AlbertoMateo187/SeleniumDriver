package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class radiobutton {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		
		String url="https://www.google.com";
		driver.get(url);
		url="http://demo.guru99.com/test/radio.html" ;
		driver.navigate().to(url);
		List <WebElement> radiobuttons=driver.findElements(By.xpath("//input[@type=\"radio\"]"));
		System.out.println(radiobuttons.size());
		for(WebElement e:radiobuttons) {
			System.out.println(e.getAttribute("id")  +":::::::::::::::::::" +e.getAttribute("value"));
			e.click();
			System.out.println(e.isDisplayed() + "  " +e.isEnabled()+" "  + e.isSelected() );
			/*
			if(e.getAttribute("id").equals("vfb-7-2")) {
				e.click();
				Thread.sleep(2000);
				break;
			}*/
			
		}
		//driver.quit();

	}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Se cerro el navegador excepción");
			driver.quit();
		}
	}
}
