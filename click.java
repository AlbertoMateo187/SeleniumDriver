package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class click {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String url="https://github.com/Alberto187/TestProject/blob/master/loginHS.xlsx";
		driver.get(url);
		Actions accion= new Actions(driver);
		driver.manage().window().maximize();
		/*
		WebElement cd=driver.findElement(By.tagName("span"));
		accion.contextClick(cd).perform();
		Thread.sleep(3000);
		List <WebElement> opciones=driver.findElements(By.tagName("span"));
		for(WebElement a:opciones) {
			System.out.println(a.getText());
		}
		
		
		
		WebElement dc=driver.findElement(By.tagName("button"));
		accion.doubleClick(dc).perform();
		Thread.sleep(3000);
		*/
		int u;
		double precios2 [];
		precios2=new double[4];
		String d="2",b="3",c="4",e=null;
		String [] botones= {d,b,c,e};
		//url="https://demoqa.com/buttons";
		driver.navigate().to(url);
		List <WebElement> boton=driver.findElements(By.tagName("button"));
		System.out.println("Los botnones que hay son::::::::" + boton.size());
		String id=null;
		int x=0;
		for(WebElement a:boton) {
		System.out.println(a.getText());
		botones[x]=a.getText();
		x++;
		}
		//driver.findElement(By.id(id)).click();
		System.out.println("Los valores del arreglo son::::::::::::::");
		for(int y=0;y<botones.length;y++) {
			System.out.println(botones[y]);
		}
		driver.quit();
		

	}

}
