package screenShotFail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class testFail {
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
			public static void main(String[] args)  throws InterruptedException, IOException{
			try {	
				//Properties propiedades = new Properties();
				//String ruta="/Practicas/src/screenShotFail/DatosFileTest";
				//propiedades.load(new FileInputStream( ruta));
				//String url = propiedades.getProperty("url");
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				driver.get("https://www.google.com/");
				DateFormat dateFormat=new SimpleDateFormat("dd-MM-yy");
				Date date=new Date();
				dateFormat.format(date );
				driver.manage().window().maximize();
				Thread.sleep(2000);
				WebElement searchBox=driver.findElement(By.name("q"));
				searchBox.clear();
				searchBox.sendKeys("Automatización");
				searchBox.submit();
				Thread.sleep(2000);
				String ruta="C:\\Users\\Mario\\Desktop\\IW\\Nayarit\\";
				File screenshotFile= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenshotFile, new File("C:\\Users\\Mario\\Desktop\\IW\\Nayarit\\"+timeStamp+".png"));
				FileUtils.copyFile(screenshotFile, new File(ruta+timeStamp+".png"));
			    XWPFDocument docx = new XWPFDocument();

			    XWPFParagraph par = docx.createParagraph();

			    XWPFRun run = par.createRun();

			    run.setText("Texto dentro del word");

			    run.setFontSize(73);
			    InputStream pic = new FileInputStream(ruta+timeStamp+".png");

			    run.addPicture(pic, Document.PICTURE_TYPE_JPEG, "1", Units.toEMU(500), Units.toEMU(200));

			    FileOutputStream out = new FileOutputStream(ruta+timeStamp+".doc");

			    docx.write(out);

			    out.close();

			    docx.close();
				driver.quit();
				
			}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("Se cerro el navegador por Exception");
					driver.quit();
					}
				}
				}		
