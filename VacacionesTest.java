package com.interware.humansite;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import com.interware.humansite.util.Utils;

public class VacacionesTest {

	@Test
	public void test_dashboard_browser() throws MalformedURLException, InterruptedException {
		// Cargamos el tipo de navegador
		DesiredCapabilities capabilities;
		capabilities = Utils.loadBrowser(System.getProperty("browser"));
		
System.out.println("########################################" + System.getProperty("selenium_grid_server_url"));
		final WebDriver driver = new RemoteWebDriver(new URL(System.getProperty("selenium_grid_server_url")),
				capabilities);
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		try {
			TestVacaciones(driver,jse);
			driver.quit();
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("### ERROR MESSAGE: " + e.getMessage());
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
		
	}

	private void TestVacaciones(WebDriver driver,JavascriptExecutor jse) throws Exception {
		try {
			Properties propiedades = new Properties();
			String projectPaht=System.getProperty("user.dir");
			String ruta=projectPaht+"/src/test/java/com/interware/humansite/PropertiesVacaciones";
			propiedades.load(new FileInputStream( ruta));
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("pass");
			String empresa = propiedades.getProperty("empresa");
			String razonSocial = propiedades.getProperty("razonSocial");
			String opcion1 = propiedades.getProperty("opcion1");
			String subOpcion1 = propiedades.getProperty("subOpcion1");
			String opcion2 = propiedades.getProperty("opcion2");
			String subOpcion2 = propiedades.getProperty("subOpcion2");
			String noEmpleado = propiedades.getProperty("noEmpleado");
			String incidencia = propiedades.getProperty("incidencia");
			String diasVacaciones = propiedades.getProperty("diasVacaciones");
			VacacionesTest puente = new VacacionesTest ();
			driver.navigate().to(System.getProperty("application_web_url"));
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
				System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG***************INICIA FLUJO DE VACACIONES***************");
			int idflujo=1;
			//Validar Empresa
			puente.validarEmpresa(driver,empresa,razonSocial);
			//Validar User y Pass
			puente.login(driver,user, pass,System.getProperty("application_web_url"));
			//Seleccionar opción Vacaciones
			puente.vacaciones(driver,opcion1,subOpcion1,idflujo);
			//Buscar Empleado
			puente.buscarEmpleado(driver,noEmpleado);
			//Consultar saldo de Vacaciones
			puente.consultarSaldo(driver,jse);
			idflujo=2;
			//Módulo_Incidencias
			puente.vacaciones(driver,opcion2,subOpcion2,idflujo);
			//Obtener leyenda alerta
			puente.alerta(driver,idflujo,ruta);
			//Seleccionar Tipo de Nómina
			puente.TipoNomina(driver,noEmpleado);
			//Obtener leyenda alerta
			puente.SeleccionarIncidencia(driver,incidencia);
			//Seleccionar Fechas y días de vacaciones
			puente.SeleccionarFechas(driver,diasVacaciones);
			idflujo=3;
			//Obtener leyenda alerta
			puente.alerta(driver,idflujo,ruta);
			System.out.println("LOG***************FLUJO VACACIONES CONCLUIDO***************");
			driver.quit();
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("LOG::::::::::Se cerro el navegador por Exception");
			driver.quit();
						  }
		}
	public  void validarEmpresa(WebDriver driver,String empresa,String razonSocial)  throws InterruptedException{	
		try {
			driver.findElement(By.id("vMB_EPR_COD")).sendKeys(empresa);
			Thread.sleep(3000);
			System.out.println("LOG::::::::::SE INGRESO LA EMPRESA");
			//Validar nombre de la empresa
			driver.findElement(By.id("IMAGE2")).click();
			Thread.sleep(3000);
			String alerta=null;
			alerta=driver.switchTo().alert().getText();
			System.out.println("LOG::::::::::WARNING");
			System.out.println("LOG::::::::::" +  alerta+ "(Alerta)");
			driver.quit();
			System.exit(0);
		}
		catch( Exception e ){
			WebElement nombre=driver.findElement(By.tagName("text"));
			if(nombre.getText().equals(razonSocial)) {
				System.out.println("LOG::::::::::LA EMPRESA ES CORRECTA");		
			}
			else {
				System.out.println("LOG::::::::::LA EMPRESA ES INCORRECTA");
				driver.quit();
				System.exit(0);
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}
	}
	public  void login(WebDriver driver,String user,String pass,String url) throws Exception{		
		try {
			//Ingresar Usuario y Password
			driver.findElement(By.id("vUSUID")).sendKeys(user);
			System.out.println("LOG::::::::::SE INGRESO EL USUARIO");
			driver.findElement(By.id("vUSUPSW")).sendKeys(pass);
			System.out.println("LOG::::::::::SE INGRESO EL PASSWORD");
			driver.findElement(By.id("vIMAGEN8")).click();
			Thread.sleep(1000);
			String alerta=null;
			alerta=driver.switchTo().alert().getText();
			System.out.println("LOG::::::::::WARNING");
			System.out.println("LOG::::::::::" +  alerta);
			driver.quit();
			System.exit(0);
		}
		catch( Exception e ) {
			String currentURL=null;
			currentURL=driver.getCurrentUrl();
			if(currentURL!=url) {
				System.out.println("LOG::::::::::ACCEDIMOS AL SISTEMA");
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}	
	}
	public  void vacaciones(WebDriver driver,String valor1,String valor2, int idflujo)  throws InterruptedException{	
		try {
			//Seleccionar opciones del menú
			if(idflujo==1) {
			WebElement Directorio = driver.findElement(By.linkText(valor1));	
			System.out.println("LOG::::::::::Seleccionar " +valor1);
			Directorio.click();
			Thread.sleep(3000);
			}
			else {
				WebElement Directorio = driver.findElement(By.id("MPW0005TEXTBLOCK5"));	
				Directorio.click();
				Thread.sleep(3000);
			}
			WebElement Vacaciones = driver.findElement(By.linkText(valor2));
			System.out.println("LOG::::::::::Seleccionar " +valor2);
			Vacaciones.click();
			Thread.sleep(3000);
		}
		catch( Exception e ){
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN VACACIONES");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}	
	public  void alerta(WebDriver driver,int idflujo,String ruta)  throws InterruptedException{	
		try {
			String alerta = driver.switchTo().alert().getText();
			System.out.println("LOG::::::::::" +alerta+ "(Alerta)");
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
		}
		catch( Exception e ){
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN ALERTAS");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}		
	public  void buscarEmpleado(WebDriver driver,String noEmpleado)  throws InterruptedException{	
		try {
			WebElement Employed = driver.findElement(By.xpath("(//input[@name=\"vEMP_NIE\"])[1]"));
			Employed.clear();
			Thread.sleep(2000);
			Employed.sendKeys(noEmpleado);
			driver.findElement(By.cssSelector("input[name=\"ACTUALIZAR\"]")).click();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG::::::::::EL EMPLEADO " +noEmpleado+ " FUE ENCONTRADO");
			Thread.sleep(2000);
		}
		catch( Exception e ){
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN VACACIONES");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void consultarSaldo(WebDriver driver,JavascriptExecutor jse)  throws InterruptedException{	
		try {
			jse.executeScript("scroll(0,2000);");
			Thread.sleep(2000);
			driver.switchTo().frame(0);
			String Saldo = driver.findElement(By.id("span_vSALDOACTUALD")).getText();
			int a = Integer.parseInt(Saldo);
			driver.switchTo().defaultContent();
			jse.executeScript("scroll(0,2000);");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			if(a > 0 )
			{
				System.out.println("LOG::::::::::El saldo de vacaciones es: " +a+ " dias");
			}
			else
			{
				System.out.println("LOG::::::::::NO SE TIENE DIAS DE VACACIONES");
				driver.quit();
				System.exit(0);
			}
		}
		catch( Exception e ){
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN VACACIONES");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void SeleccionarIncidencia(WebDriver driver,String valor1)  throws InterruptedException{	
			try {
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.switchTo().frame(0);
				System.out.println("LOG::::::::::Se accedio al catalogo de incidencias correctamente");
				Thread.sleep(1000);
				//Buscar por descripción el valor Vacaciones
				driver.findElement(By.id("vC286")).sendKeys(valor1);
				driver.findElement(By.name("BTNBUSCARNOM")).click();
				System.out.println("LOG::::::::::Busqueda de incidencias correcto");
				Thread.sleep(3000);
				Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
						System.getProperty("link"), this.getClass().getSimpleName());
				//Seleccionar el tipo de Incidencia
				driver.findElement(By.id("vLINKSELECTION_0001")).click();
				System.out.println("LOG::::::::::Incidencia seleccionada correctamente");
				Thread.sleep(3000);
				driver.switchTo().defaultContent();
		}
		catch( Exception e ){
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR AL SELECCIONAR INCIDENCIA");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}	
	}
	public  void TipoNomina(WebDriver driver,String empleado)  throws InterruptedException{	
		try {
			driver.findElement(By.name("IMGNOM")).click();
			driver.switchTo().frame(1);
			System.out.println("LOG::::::::::Se accedio a la seleccion de Nomina");
			WebElement NOM = driver.findElement(By.cssSelector("input[id=\"vLINKSELECTION_0003\"]"));
			NOM.click();
			System.out.println("LOG::::::::::Se selecciono tipo de nomina");
			Thread.sleep(3000);
			driver.switchTo().defaultContent();
			driver.findElement(By.name("BTN_ACTUALIZAR")).click();
			Thread.sleep(3000);
			//Ingresar el Número de empleado
			driver.switchTo().frame(0);
			WebElement No_Employed = driver.findElement(By.name("vEMP_NIEC"));
			No_Employed.sendKeys(empleado);
			System.out.println("LOG::::::::::Se ingreso el numero de empleado correctamente");
			//Clic a la lupa de Incidencias
			WebElement Lupa_Nom = driver.findElement(By.name("CON"));
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			Lupa_Nom.click();
		}
		catch( Exception e ){
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN TIPO NOMINA");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void SeleccionarFechas(WebDriver driver,String valor)  throws InterruptedException{	
		try {
			//Fechas
			String Fecha = driver.findElement(By.id("span_vFECHACIERRE")).getText();
			String Dia = Fecha.substring(0, 2);
			String Mes = Fecha.substring(3, 6);
			String Year = Fecha.substring(Fecha.length() -4);
			int Dia_Num = Integer.parseInt(Dia)+7;
			String Dia_Alfa= Integer.toString(Dia_Num);
			System.out.println("LOG::::::::::La fecha final del periodo es: " +Dia_Num+"/"+Mes+"/"+Year);
			driver.switchTo().frame(0);
			WebElement Calendar = driver.findElement(By.id("vFECHAINCIDENCIAC_dp_trigger"));
			Calendar.click();
			Thread.sleep(3000);
			//Comparación de fechas
			String Date;
			WebElement Next_Month = driver.findElement(By.xpath("//td[@class=\"calendarbutton calendar-nav\"][3]"));
			WebElement Next_Year = driver.findElement(By.xpath("//td[@class=\"calendarbutton calendar-nav\"][4]"));
			//Mes
			for (int m=1; m<=12; m++)
				{
				   Date = driver.findElement(By.className("calendartitle")).getText();
				   String Submes = Date.substring(0, 3);
				  if (Submes.contains(Mes))
				  {
					  System.out.println("LOG::::::::::Se ha seleccionado un mes correctamente");
					  break;
				  }
				  else
				  {
					  Next_Month.click();
					  Thread.sleep(1000);
				  }
				}
			//Año
			for (int y=1; y<=5; y++)
				{
				  Date = driver.findElement(By.className("calendartitle")).getText();
				  String SubYear = Date.substring(Date.length() -4);
				  if (SubYear.contains(Year))
				  {
					  System.out.println("LOG::::::::::Se ha seleccionado un valor anual correctamente");
					  break;
				  }
				  else
				  {
					  Next_Year.click();
					  Thread.sleep(3000);
				  }
				}
			//Día
			List <WebElement> day = driver.findElements(By.className("day"));
			for (WebElement b:day)
			{   
				if(b.getText().contains(Dia_Alfa)) 
					{
					b.click();
					System.out.println("LOG::::::::::Se ha seleccionado un dia correctamente");
					break;
					}	
			}		
			WebElement Num_Vac = driver.findElement(By.id("vINCIDENCIAVALC"));
			Num_Vac.clear();
			Num_Vac.sendKeys(valor);
			System.out.println("LOG::::::::::Se han seleccionado " + valor +" dia(s) de vacaciones correctamente");
			WebElement Aplicar = driver.findElement(By.name("BUTTON4"));
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			Aplicar.click();
			Thread.sleep(3000);
	}
	catch( Exception e ){
		e.printStackTrace();
		System.out.println("LOG::::::::::ERROR EN SELECCION DE FECHAS");
		Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
				System.getProperty("link"), this.getClass().getSimpleName());
		driver.quit();
		System.exit(0);
	}	
}
	
}

