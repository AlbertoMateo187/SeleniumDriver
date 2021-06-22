package semana1;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class examen2 {

public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url ="https://www.google.com";
        //Actions accion = new Actions(driver);
       
        try {
       
        driver.get(url);
        WebElement link = driver.findElement(By.name("q"));
        link.click();
       
   
       
driver.findElement(By.xpath("//input[@name=\"q\"]")).sendKeys("demo-guru");
driver.findElement(By.xpath("(//input[@class=\"gNO89b\"])[2]")).sendKeys(Keys.ENTER);
Thread.sleep(2000);

WebElement link2 = driver.findElement(By.xpath("(//h3)[1]"));
link2.click();

Thread.sleep(1000);

WebElement link3 = driver.findElement(By.xpath("(//a[@href=\"http://demo.guru99.com/payment-gateway/index.php\"])[1]"));
link3.click();

Thread.sleep(1000);

JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("scrollBy(0,600)");

       Thread.sleep(1000);
       
WebElement combo = driver.findElement(By.name("quantity"));
Select variable = new Select(combo);
Thread.sleep(1000);
variable.selectByIndex(3);

Thread.sleep(1000);

WebElement link4 = driver.findElement(By.xpath("//input[@value=\"Buy Now\"]"));
   link4.click();

 
Thread.sleep(1000);

WebElement link5 = driver.findElement(By.xpath("(//a[@href=\"cardnumber.php\"])[1]"));
link5.click();

   Thread.sleep(1000);
     
       ArrayList<String> ventanas = new ArrayList <String>(driver.getWindowHandles());
       driver.switchTo().window(ventanas.get(1));
       Thread.sleep(1000);
       

JavascriptExecutor js2 = (JavascriptExecutor) driver;
       js2.executeScript("scrollBy(0,500)");
       
       Thread.sleep(1000);

       String NoTarjeta = driver.findElement(By.xpath("(//h4[@style=\"text-align:center;font-size:24px;font-weight:400;color:#555;line-height:36px;\"])[1]")).getText();
       String cvv = driver.findElement(By.xpath("(//h4[@style=\"text-align:center;font-size:24px;font-weight:400;color:#555;line-height:36px;\"])[2]")).getText();
       String Exp = driver.findElement(By.xpath("(//h4[@style=\"text-align:center;font-size:24px;font-weight:400;color:#555;line-height:36px;\"])[3]")).getText();
       String CredLim = driver.findElement(By.xpath("(//h4[@style=\"text-align:center;font-size:24px;font-weight:400;color:#555;line-height:36px;\"])[4]")).getText();
       
       System.out.println(NoTarjeta);
       System.out.println(cvv);
       System.out.println(Exp);
       System.out.println(CredLim);

       driver.switchTo().window(ventanas.get(0));
       
   
       
       Thread.sleep(1000);
       
       driver.findElement(By.xpath("//input[@name=\"card_nmuber\"]")).sendKeys(NoTarjeta.substring(NoTarjeta.length()-16, NoTarjeta.length()));


        Thread.sleep(1000);
       
        driver.findElement(By.name("month")).sendKeys(Exp.substring(Exp.length()-7, Exp.length()-5));
       
        Thread.sleep(1000);
         
        driver.findElement(By.name("year")).sendKeys(Exp.substring(Exp.length()-4, Exp.length()));
       
        Thread.sleep(1000);
       
         
        driver.findElement(By.name("cvv_code")).sendKeys(cvv.substring(cvv.length()-3, cvv.length()));
       
        Thread.sleep(1000);
       
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
         js3.executeScript("scrollBy(0,200)");
       
         Thread.sleep(1000);
         
         WebElement link6 = driver.findElement(By.xpath("//input[@name=\"submit\"]"));
link6.click();
         
Thread.sleep(1000);

String Orden = driver.findElement(By.xpath("(//strong)[3]")).getText();
System.out.println("La orden generada por la compra fue :" + Orden);
         
Thread.sleep(3000);

driver.quit();
       
        }
        catch (Exception e) {
        driver.quit();
        }
}

}	