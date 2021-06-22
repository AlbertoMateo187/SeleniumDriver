package semana1;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String url="https://www.google.com";
		driver.get(url);
		
		
		

	}

}
