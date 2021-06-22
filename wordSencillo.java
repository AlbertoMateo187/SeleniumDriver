package word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class wordSencillo {
		public static WebDriver driver;
		public static JavascriptExecutor jse;
			static {
			driver=getDriver();
			jse=getJse();
			}
			public static WebDriver getDriver() {
				String projectPaht=System.getProperty("user.dir");
				System.setProperty("webdriver.chrome.driver", projectPaht+"\\Drivers\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
			return driver;
			}
			public static JavascriptExecutor getJse() {
				JavascriptExecutor jse = (JavascriptExecutor)driver;
			return jse;
			}
			public static void main(String[] args) throws IOException  {
				try {
				Properties propiedades = new Properties();
				String projectPaht=System.getProperty("user.dir");
				String ruta=projectPaht+"\\Archivos\\Datos";
				propiedades.load(new FileInputStream(ruta));
				String url = propiedades.getProperty("url");
				String destino = propiedades.getProperty("destino");
				driver.get(url);
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				XWPFDocument docx = new XWPFDocument();
				XWPFParagraph par = docx.createParagraph();
				XWPFRun run = par.createRun();
				run.setText("Texto dentro del word");
				run.setFontSize(73);
				FileOutputStream out = new FileOutputStream(destino+timeStamp+".doc");
				docx.write(out);
				out.close();
				docx.close();
				Thread.sleep(2000);
				driver.quit();
				}
				catch(Exception e){
					e.printStackTrace();
					System.out.println("Se cerro el navegador por Exception");
					driver.quit();
					}
			}
}
