package semana1;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class capturarHoras {
public static WebDriver driver;
public static JavascriptExecutor jse;
public static String proyecto;
	static {
	driver=getDriver();
	jse=getJse();
	proyecto=getProyecto();
	
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
	public static String  getProyecto() {
		String proyecto="Fénix";
		return proyecto;
		}
	public static void main(String[] args)  throws InterruptedException{
	try {
	String url = "http://interware.com.mx/iw-sci/inicio.do";
	String dia="";
	capturarHoras puente = new capturarHoras ();
	dia=puente.semana(dia);
	//Abrir navegador
	driver.get(url);
	//Thread.sleep(2000);
	driver.manage().window().maximize();
	capturarHoras.entrar(args);
	capturarHoras.usuarioCorrecto(args);
	//Validar d�a actual
	/*
	if (dia=="Lunes") {
		capturarHoras.diaAntV(args);
		capturarHoras.capturaHoy(args);
		capturarHoras.diaAnt(args);
	}
	if (dia=="Viernes") {
	capturarHoras.diaAnt(args);
	capturarHoras.capturaHoy(args);
	capturarHoras.diaAntV(args);
	}
	if (dia!="Viernes" && dia!="Lunes") {
	capturarHoras.diaAnt(args);
	capturarHoras.capturaHoy(args);
	capturarHoras.diaAnt(args);
	}
	*/
	int z=0;
	switch (z) 
	{
		case 1:  dia = "Lunes";
		capturarHoras.diaAntV(args);
		capturarHoras.capturaHoy(args);
		capturarHoras.diaAnt(args);  
			break;
		case 2:   dia = "Viernes";
		capturarHoras.diaAnt(args);
		capturarHoras.capturaHoy(args);
		capturarHoras.diaAntV(args);
			break;
		default: 
		capturarHoras.diaAnt(args);
		capturarHoras.capturaHoy(args);
		capturarHoras.diaAnt(args);
			break;
}
	System.out.println("Se capturaron las horas correctamente");
	driver.close();
	}
	catch(Exception e){
	e.printStackTrace();
	System.out.println("Se cerro el navegador por Exception");
	driver.quit();
	}
	}
	public static void entrar (String[] args)throws InterruptedException{
	//VAriables
	WebElement login=null;
	String user="mmateo";
	String pass="Leviathan18#";
	WebDriverWait wait = new WebDriverWait(driver, 105);
	//Clic en la opci�n de login
	login = driver.findElement(By.xpath("//div[@class=\"abcRioButtonContentWrapper\"]"));
	login.click();
	Thread.sleep(2000);
	ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
	//Cambiar a pesta�a Login
	driver.switchTo().window(tabs.get(1));
	Thread.sleep(1000);
	//Ingresar login y pass
	driver.findElement(By.xpath("//input[@type=\"email\"]")).sendKeys(user);
	driver.findElement(By.xpath("//div[@id=\"identifierNext\"]")).click();
	Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type=\"password\"]")));
	driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys(pass);
	driver.findElement(By.xpath("//div[@id=\"passwordNext\"]")).click();
	//Regresar a la pantalla de SCI
	driver.switchTo().window(tabs.get(0));
	//Esperar a que aparezca la opci�n de Captura
	WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"divIW-01\"]/a")));
	signInButton.click();
	Thread.sleep(2000);
	System.out.println("Se ingreso correctamente a SCI");
	}
	public static void usuarioCorrecto (String[] args)throws InterruptedException{
	String user="Mario Alberto Mateo Jimenez";
	//validar nombre de recurso
	WebElement nombreWeb=driver.findElement(By.xpath("//td[@class=\"texto-bold\"]"));
	if(nombreWeb.getText().contains(user)){
	System.out.println("Recurso correcto");
	}
	else {
	System.out.println("Nombre incorrecto");
	driver.quit();
	}
	}
	public String semana (String dia){
	Calendar now = Calendar.getInstance();
	String[] strDays = new String[]{
	"Domingo",
	"Lunes",
	"Martes",
	"Miercoles",
	"Jueves",
	"Viernes",
	"Sabado"};
	dia=strDays[now.get(Calendar.DAY_OF_WEEK) -1];
	System.out.println(dia);
	return dia;
	}
	public static void  diaAnt (String[] args)throws InterruptedException{
	//seleccionar el proyecto de Operaci�n QA
	driver.findElement(By.xpath("//a[@id=\"proyecto-button\"]")).click();
	Thread.sleep(2000);
	List<WebElement> lista = driver.findElements(By.xpath("//ul[@id=\"proyecto-menu\"]//a[@role=\"option\"]"));
	for(WebElement e : lista) {
	if(e.getText().contains(proyecto)) {
	e.click();
	}
	}
	//Ingresar hora Inicio y FIn
	driver.findElement(By.xpath("//input[@name=\"horaInicioIndividual\"]")).clear();
	driver.findElement(By.xpath("//input[@name=\"horaInicioIndividual\"]")).sendKeys("09:00");
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@name=\"horaFinIndividual\"]")).clear();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@name=\"horaFinIndividual\"]")).sendKeys("17:00");
	//Guardar Registro
	driver.findElement(By.xpath("//input[@value=\"Agregar\"]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//input[@value=\"Aceptar\"]")).click();  
	Thread.sleep(2000);
	driver.findElement(By.xpath("//span[@class=\"ui-button-text\"]")).click(); //aceptar mensaje de confirmaci�n
	//Mostrar fecha
	WebElement fecha=driver.findElement(By.xpath("//td[@colspan=\"2\"]"));
	System.out.println("Se captur� correctamente la hora de la fecha "+ fecha.getText());
	}
	public static void  diaAntV (String[] args)throws InterruptedException{
	//seleccionar el proyecto
	driver.findElement(By.xpath("//a[@id=\"proyecto-button\"]")).click();
	Thread.sleep(2000);
	List<WebElement> lista = driver.findElements(By.xpath("//ul[@id=\"proyecto-menu\"]//a[@role=\"option\"]"));
	for(WebElement e : lista) {
		if(e.getText().contains(proyecto)) {
			e.click();
			}
		}
	//Ingresar hora Inicio y FIn
	driver.findElement(By.xpath("//input[@name=\"horaInicioIndividual\"]")).clear();
	Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@name=\"horaInicioIndividual\"]")).sendKeys("09:00");
	Thread.sleep(4000);
	driver.findElement(By.xpath("//input[@name=\"horaFinIndividual\"]")).clear();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//input[@name=\"horaFinIndividual\"]")).sendKeys("17:00");
	Thread.sleep(1000);
	//Guardar Registro
	driver.findElement(By.xpath("//input[@value=\"Agregar\"]")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//input[@value=\"Aceptar\"]")).click();  
	Thread.sleep(3000);
	driver.findElement(By.xpath("//span[@class=\"ui-button-text\"]")).click(); //aceptar mensaje de confirmaci�n
	//Mostrar fecha
	WebElement fecha=driver.findElement(By.xpath("//td[@colspan=\"2\"]"));
	System.out.println("Se captur� correctamente la hora de la fecha "+ fecha.getText());
	}
	public static void  capturaHoy (String[] args)throws InterruptedException{
	//desplegar opciones y seleccionar opci�n Hoy
	driver.findElement(By.xpath("//*[@id=\"divIW-01\"]/a")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[@class=\"ui-selectmenu-icon ui-icon ui-icon-triangle-1-s\"]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[@aria-selected=\"false\"]")).click();
	Thread.sleep(1000);
	}
	
	
}