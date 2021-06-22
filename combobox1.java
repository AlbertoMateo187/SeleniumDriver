package semana1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class combobox1 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.get("https://www.google.com");
		driver.navigate().to("http://demoaut.katalon.com/");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@id=\"btn-make-appointment\" and @class=\"btn btn-dark btn-lg\"]")).click();
		Thread.sleep(2000);
		WebElement user=driver.findElement(By.className("form-control"));
		String user2=user.getAttribute("value");
		WebElement pass=driver.findElement(By.xpath("(//input[@class=\"form-control\"])[2]"));
		String pass2=pass.getAttribute("value");
		//login
		driver.findElement(By.xpath("(//input[@class=\"form-control\"])[3]")).sendKeys(user2);
		driver.findElement(By.xpath("(//input[@class=\"form-control\"])[4]")).sendKeys(pass2);
		Thread.sleep(2000);
		driver.findElement(By.tagName("button")).submit();
		//Agendar cita
		
		WebElement combo=driver.findElement(By.id("combo_facility"));
		Select facility = new Select (combo);	
		facility.selectByIndex(1);
		driver.findElement(By.id("chk_hospotal_readmission")).click();
		Thread.sleep(1000);
		//driver.findElement(By.id("radio_program_medicaid")).click();
		List <WebElement> radios=driver.findElements(By.name("programs"));
		System.out.println(radios.size());
		Thread.sleep(1000);
		for(WebElement a:radios) {
			String nombre=a.getAttribute("value");
			if(nombre.contains("No")) {
				if(a.isSelected()==true) {
					System.out.println("El radiobutton ya está seleccionado");
					break;
				}
				else {
					System.out.println("EL radiobutton no esta seleccionado");
					a.click();
				}
				break;
			}
		}
		/*
		driver.findElement(By.id("txt_visit_date")).click();
		Thread.sleep(1000);
		String citaMes="October 2021";
		String citaDia="29";
		String texto="Comentario de prueba";
		WebElement mes=driver.findElement(By.className("datepicker-switch"));
		System.out.println(mes.getText());
		WebElement next=driver.findElement(By.className("next"));
		for(int a=0;a<=100;a++) {
			if(mes.getText().contains(citaMes)) {
				break;
			}
			next.click();
		}
	List <WebElement> dia=driver.findElements(By.xpath("//td[@class=\"day\"]"));
	for(WebElement e:dia){
		if (e.getText().equals(citaDia)) {
			e.click();
			break;
		}
	}
	driver.findElement(By.tagName("textarea")).sendKeys(texto);
	driver.findElement(By.tagName("button")).submit();
	//Cita confirmada
	WebElement titulo=driver.findElement(By.tagName("h2"));/*
	WebElement subtitulo=driver.findElement(By.tagName("p"));
	System.out.println(titulo.getText()+ "\n" + subtitulo.getText());
	List <WebElement> titulo2=driver.findElements(By.xpath("//div[@class=\"col-xs-offset-2 col-xs-8\"]"));
	for(WebElement e:titulo2) {
		System.out.println(e.getText());
	}
	int a=1;
	List <WebElement> subtitulo=driver.findElements(By.tagName("p"));
	for(WebElement e:subtitulo) {
		if(a<=6) {
			System.out.println(e.getText());
		}
		else {
			break;
		}
		a=a+1;
	}
	//driver.findElement(By.xpath("//a[@class=\"btn btn-default\"]")).click();
	*/
	driver.quit();
	}
	
}