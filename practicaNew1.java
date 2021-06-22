package semana1;


	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;

	public class practicaNew1 {

		public static void main(String[] args) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
			WebDriver driver= new ChromeDriver();
			driver.manage().window().maximize();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			Actions accion= new Actions(driver);
			String url="https://demoqa.com/select-menu";
			try {
			driver.get(url);
			WebElement combo=driver.findElement(By.className("css-19bqh2r"));
			Thread.sleep(1000);
			combo.click();
			//combo.sendKeys(Keys.TAB);
			Thread.sleep(2000);
			accion.sendKeys(Keys.ENTER).perform();
			String valor="Group 2, option 2";
			WebElement texto=driver.findElement(By.xpath("//div[@class=\" css-1uccc91-singleValue\"]"));
			for(int a=1;a<=6;a++) {
				if(texto.getText().contains(valor)) {
					accion.sendKeys(Keys.ENTER).perform();
					break;
				}
				else {
			combo.click();
			accion.sendKeys(Keys.DOWN).perform();
			accion.sendKeys(Keys.ENTER).perform();
			System.out.println(texto.getText());
				}
			}
			Thread.sleep(3000);
			WebElement combo2=driver.findElement(By.id("selectOne"));
			combo2.click();
			Thread.sleep(2000);
			combo.sendKeys("test");
			//driver.quit();
			}
			catch (Exception e) {
				e.printStackTrace();
				driver.quit();
				}
		}
	}
