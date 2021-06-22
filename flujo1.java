package flujosHS;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class flujo1 {

		public static WebDriver driver;
		public static JavascriptExecutor jse;
		public static String proyecto;
			static {
			driver=getDriver();
			jse=getJse();
			}
			public static WebDriver getDriver() {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
			return driver;
			}
			public static JavascriptExecutor getJse() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			return jse;
			}
			public static void main(String[] args)  throws InterruptedException{
			try {	
				String url = "https://qa_knr.humansite.com.mx/hlogin.aspx";
				//Abrir navegador
				driver.get(url);
				//Thread.sleep(2000);
				driver.manage().window().maximize();
				driver.findElement(By.id("vMB_EPR_COD")).sendKeys("KNR");
				Thread.sleep(2000);
				driver.findElement(By.id("vUSUID")).sendKeys("EQUIPOQA");
				Thread.sleep(2000);
				driver.findElement(By.id("vUSUPSW")).sendKeys("124578");
				Thread.sleep(2000);
				driver.findElement(By.id("vIMAGEN8")).click();
				Thread.sleep(9000);
				//driver.findElement(By.className("specialeffects")).click();
				driver.findElement(By.xpath("(//span[@class=\"specialeffects\"])[3]")).click();
				Thread.sleep(2000);
				System.out.println("Dimos clic a nomina");
				driver.findElement(By.name("MPW0005vCONFIG")).click();
				Thread.sleep(4000);
				System.out.println("Dimos clic al engrane");
				driver.findElement(By.partialLinkText("Concep")).click();
				//driver.findElement(By.className("specialeffects1")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("vB_NOMBRE")).sendKeys("D001");
				Thread.sleep(2000);
				driver.findElement(By.id("IMAGE1")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("IMAGE20")).click();
				//driver.findElement(By.id("")).click();
				Thread.sleep(4000);
				System.out.println("Los id de mi navegador son :::::: " + driver.getWindowHandles());
				ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(1));
				System.out.println(driver.findElement(By.id("TEXTBLOCK2")).getText());
				jse.executeScript ("window.scrollBy(0,200)");
				List <WebElement> valores=driver.findElements(By.xpath("//td[@valign=\"middle\"]"));
				int b=0;
					int c=0;
				for(WebElement a:valores) {
					System.out.println(a.getText());
					b=b+1;
					if (a.getText().contains("COM25")) {
					c=b+3;
						System.out.println(c);
					}
					if(c==b) {
					a.click();
					Thread.sleep(10000);
					break;
					}
				}
				System.out.println(b);
				
				driver.findElement(By.id("span_vDETALLE_0006")).click();
				Thread.sleep(4000);
				driver.switchTo().frame(0);
				System.out.println(driver.findElement(By.id("span_CTLAGRUPACIONID_0001")).getText());
				WebElement checkbox =driver.findElement(By.name("CTLAPLICA_0001"));
				System.out.println(checkbox.isSelected());
				WebElement checkbox2 =driver.findElement(By.name("CTLAPLICA_0002"));
				System.out.println(checkbox2.isSelected());
				
				Thread.sleep(4000);
				driver.quit();
			}

			
			catch(Exception e){
				e.printStackTrace();
				driver.quit();
			}
		}
			
	}
 