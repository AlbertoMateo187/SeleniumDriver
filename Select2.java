package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Select2 {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		try {
		
		String url="https://www.google.com";
		driver.get(url);
		url="https://select2.org/getting-started/basic-usage";
		driver.navigate().to(url);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		js.executeScript("window.scrollBy(0,100)");
		WebElement options=driver.findElement(By.xpath("//select[@class=\"js-example-basic-single js-states form-control select2-hidden-accessible\"]"));
		Select combo = new Select (options);
		combo.selectByIndex(10);
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,900)");
		options=driver.findElement(By.xpath("(//select[@tabindex=\"-1\"])[2]"));
		Select combo2 = new Select (options);
		if(combo2.isMultiple()==true) {
			int tamaño=combo2.getOptions().size();
			System.out.println("La cantidad de opciones del combobox es::::::" + tamaño);
			//Arreglo de numeros aleatorios
			int[] numeros = new int[10];
				for(int i=0;i<numeros.length;i++) {
					numeros[i]=(int) (Math.random() * tamaño -1);
					System.out.println("Valor del arreglo"+i + "   " + numeros[i]);
				}
			//Seleccionar los estados usando los valores del arreglo
				for(int a=0;a<numeros.length;a++) {
					combo2.selectByIndex(numeros[a]);
					Thread.sleep(1000);
		}
				System.out.println("Se han seleccionado los estados");
				List <WebElement> estados=driver.findElements(By.xpath("//span[@class=\"select2-selection__choice__display\"]"));
				System.out.println("Los estados seleccionados son:::::::::");
				for(WebElement a:estados) {
					System.out.println(a.getText());
				}
				System.out.println("El primer estado seleccionado fue:::::" +combo2.getFirstSelectedOption().getText());
				Thread.sleep(5000);
				combo2.deselectByIndex(5);
				System.out.println("Se ha deseleccionado el quinto estado seleccionado");
				Thread.sleep(5000);
				combo2.deselectAll();
				System.out.println("Se han deseleccionado todos los estados");
				Thread.sleep(5000);
			}
		else {
			System.out.println("El combobox no permite seleccionar más de un valor");
			driver.quit();
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
