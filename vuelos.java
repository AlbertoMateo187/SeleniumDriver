package Ejercicios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class vuelos {

	public static WebDriver driver;
	public static JavascriptExecutor jse;
	public static Actions accion;
	static {
		driver=getDriver();
		jse=getJse();
		accion=getAccion();
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
	public static Actions getAccion() {
		Actions accion= new Actions(driver);
		return accion;
	}
	public static void main(String[] args) {
			try {
				String URL="https://blazedemo.com/";
				driver.get(URL);
				driver.manage().window().maximize();
				WebElement puntoA=driver.findElement(By.name("fromPort"));
				WebElement puntoB=driver.findElement(By.name("toPort"));
				Select combo = new Select (puntoA);
				combo.selectByVisibleText("Boston");
				combo = new Select (puntoB);
				combo.selectByVisibleText("Cairo");
				driver.findElement(By.tagName("input")).click();
				Thread.sleep(3000);
				List <WebElement> precios=driver.findElements(By.xpath("//td"));
				int prices=precios.size();
				int numColumnas=6;
				prices=prices/numColumnas;
				int id [];
				id=new int[prices];
				for(int a=0;a<id.length;a++){
					id[a]=6*(a+1);
					System.out.println(id[a]);
				}				
				double precios2 [];
				precios2=new double[prices];
				for(int i=0;i<id.length;i++) {
					String valor=driver.findElement(By.xpath("(//td)["+id[i]+"]")).getText();
					
					String valor1=valor.substring(valor.length()-6,valor.length());
					System.out.println(valor1);
				precios2[i]=Double.parseDouble(valor1);
				}
				double  max = precios2[0], min = precios2[0];
				int c = 0;
		        for (int i = 0; i <precios2.length; i++)
		        {
		            if (precios2[i] > max) {
		                max = precios2[i];		             
		                c=i+1;
		     		            }
		            else if (precios2[i] < min) {
		                min = precios2[i];		                
		            }
		        } 
		       driver.findElement(By.xpath("(//input[@type=\"submit\"])["+c+"]")).click();
				System.out.println("El precio más caro es:::::::" + max);
				System.out.println("El precio más barato es:::::::" + min);
				driver.quit();

			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("Se cerro el navegador por excepción");
				driver.quit();
			}
		}
		}