package Ejercicios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Practica1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String url="https://www.google.com";
		driver.get(url);
		driver.findElement(By.name("q")).sendKeys("gobierno de nayarit");
		System.out.println("LOG::::::::::::::: Locator 1 ::::::::::name");
		driver.findElement(By.id("lga")).click();
		System.out.println("LOG::::::::::::::: Locator 2::::::::::id");
		driver.findElement(By.xpath("(//input)[7]")).click();
		System.out.println("LOG::::::::::::::: Locator 3::::::::::::::xpath");
		Thread.sleep(3000);
		driver.findElement(By.tagName("h3")).click();
		System.out.println("LOG::::::::::::::: Locator 4:::::::::::::tagName");
		driver.findElement(By.linkText("Leer más")).click();
		System.out.println("LOG::::::::::::::: Locator 5::::::::::::::LinkText");
		Thread.sleep(3000);
		driver.findElement(By.partialLinkText("Gob")).click();
		System.out.println("LOG::::::::::::::: Locator 6::::::::::::::::partialLinkText");
		driver.findElement(By.className("nav-link")).click();
		System.out.println("LOG::::::::::::::: Locator 7:::::::::::className");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#dropdownMenuLink")).click();
		System.out.println("LOG::::::::::::::: Locator 8:::::::::::::::cssSelector");
		


	}

}
