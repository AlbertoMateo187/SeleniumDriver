package com.interware.humansite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.interware.humansite.util.Utils;


public class NominaTest {

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
			testNomina(driver,jse);
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
	
	private void testNomina(WebDriver driver,JavascriptExecutor jse) throws Exception {
		try {
			Properties propiedades = new Properties();
			String projectPaht=System.getProperty("user.dir");
			String ruta=projectPaht+"/src/test/java/com/interware/humansite/pruebaNomina";
			propiedades.load(new FileInputStream( ruta));
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("pass");
			String empresa = propiedades.getProperty("empresa");
			String razonSocial = propiedades.getProperty("razonSocial");
			String opcion1 = propiedades.getProperty("opcion1");
			String subOpcion1 = propiedades.getProperty("subOpcion1");
			String subOpcion2 = propiedades.getProperty("subOpcion2");
			String corpo = propiedades.getProperty("corpo");
			String banco = propiedades.getProperty("banco");
			String compania = propiedades.getProperty("compania");
			String rutaDoc = propiedades.getProperty("rutaDoc");
			NominaTest puente = new NominaTest ();
			driver.navigate().to(System.getProperty("application_web_url"));
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
				System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG***********************************************INICIO DEL FLUJO DE NOMINA**********************");
			//Validar Empresa
			puente.validarEmpresa(driver,empresa,razonSocial);
			puente.login(driver,user, pass,System.getProperty("application_web_url"));
			//Ingresar a la pantalla de Pago
			puente.nomina(driver,opcion1,subOpcion1);
			puente.alerta(driver);
			//Pantalla Pago
			puente.pago(driver,corpo,jse);
			//Revisar datos mayores a 0
			puente.Calculo(driver);
			//Entrar a Dispersión
			puente.dispersion(driver,banco,compania,jse);
			//Activar checks
			puente.activar(driver);
			//Ingresar a la pantalla de Bandeja de Entrada
			puente.nomina(driver,opcion1,subOpcion2);
			//Consultar datos de Dispersion
			puente.consultarDatos(driver,jse,rutaDoc);
			System.out.println("LOG************************************FLUJO CONCLUIDO EXITOSAMENTE******************************");
			}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error, Se cerrara el navegador");
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
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG::::::::::WARNING");
			System.out.println("LOG::::::::::" +  alerta);
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
			System.out.println("LOG::::::::::: SE INGRESO EL USUARIO");
			driver.findElement(By.id("vUSUPSW")).sendKeys(pass);
			System.out.println("LOG::::::::::: SE INGRESO EL PASSWORD");
			driver.findElement(By.id("vIMAGEN8")).click();
			Thread.sleep(2000);
			String alerta=null;
			alerta=driver.switchTo().alert().getText();
			System.out.println("Algo ha salido mal");
			System.out.println("LOG:::::::::::::::::::::::" +  alerta);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
		catch( Exception e ) {
			String currentURL=null;
			currentURL=driver.getCurrentUrl();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			if(currentURL!=url) {
				System.out.println("LOG::::::::::::::::::::::: ACCEDIMOS AL SISTEMA");
			}
		}	
	}
	public  void nomina(WebDriver driver,String opcion1,String opcion2) throws InterruptedException{
		try {
			//Clic a la opción Nomina
			driver.findElement(By.partialLinkText(opcion1)).click();
			System.out.println("LOG:::::::::::::::::::SELECCIONAMOS "+ opcion1+ "::::::::::::::::::::::");
			Thread.sleep(3000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			//Clic a la opcion Pago
			driver.findElement(By.partialLinkText(opcion2)).click();
			//Thread.sleep(6000);
			System.out.println("LOG:::::::::::::::::::SELECCIONAMOS "+ opcion2+ "::::::::::::::::::::::");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}
		catch (Exception e) {
			System.out.println("LOG::::::::::::::::::::::::::::::::::::::::: Error en Nomina, Se cerrara el navegador");
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
		}
	}
	public  void pago(WebDriver driver,String opcion3,JavascriptExecutor jse) throws InterruptedException{
		try {
			
			//Seleccionar la nómina
			System.out.println("LOG::::::::::::::::::::::::::Seleccionar una Nomina:::::::::::::::::::::::::::::::::::::::::::::");
			driver.findElement(By.name("IMGNOM")).click();
			Thread.sleep(2000);
			driver.switchTo().frame(1);
			Thread.sleep(2000);
			driver.findElement(By.name("vLINKSELECTION_0001")).click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			//Limpiar el campo CORPO
			WebElement corpo=driver.findElement(By.id("vF_PRCODIGO"));
			corpo.clear();
			Thread.sleep(1000);
			//Insertar valor en el campo CORPO
			corpo.sendKeys(opcion3);
			Thread.sleep(1000);
			//Clic al botón Actualizar
			driver.findElement(By.name("BTN_ACTUALIZAR")).click();
			Thread.sleep(2000);
			jse.executeScript("scroll(0,2000);");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}
		catch (Exception e) {
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG::::::::::::::::::::::::::::::::Error en Pago, Se cerrara el navegador");
			driver.quit();
			System.exit(0);
		}
	}
	public  void dispersion(WebDriver driver,String banco,String empresa,JavascriptExecutor jse) throws InterruptedException{
		try {
			//Clic al botón Dispersión
			driver.findElement(By.name("BTN_DISPERSION")).click();
			System.out.println("LOG::::::::::Entramos a DISPERSION:::::::::::::::::");
			Thread.sleep(3000);
			ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.manage().window().maximize();
			System.out.println("LOG:::::::::::::::::::::::::::::INGRESAR DATOS PARA DISPERSION:::::::::::::::");
			//Seleccionar Banco
			WebElement idbanco=driver.findElement(By.id("vBANCOID"));
			Select opcion = new Select (idbanco);	
			opcion.selectByVisibleText(banco);
			Thread.sleep(3000);
			System.out.println("LOG:::::::::::::::::::SE SELECCIONO EL BANCO    " + banco+ "::::::::::::::::::::");
			//Seleccionar Fecha
			driver.findElement(By.id("vFECHA1_dp_trigger")).click();
			Thread.sleep(3000);
			List <WebElement> dias=driver.findElements(By.xpath("//td[@class]"));
			//Seleccionar el día Actual
			for(WebElement a:dias) {
				String dia=a.getAttribute("class");
				if(dia.contains("day today")) {
					a.click();
					driver.findElement(By.id("IMAGE5")).click();
					Thread.sleep(5000);
					String fecha=driver.findElement(By.id("span_vDATO1")).getText();
					System.out.println("LOG::::::::::::LA FECHA SELECCIONADA ES  "+ fecha +"::::::::::::::::::");
					break;
				}
			}
			//Ingresar la secuencia
			driver.findElement(By.id("vDATO2")).sendKeys("99");
			//Ingresar la Descripción
			WebElement descripcion=driver.findElement(By.id("vDATO3"));
			descripcion.clear();
			descripcion.sendKeys("PAGO DE NOMINA");
			jse.executeScript("scroll(0,2000);");
			//Ingresar la Compañia
			WebElement compañia=driver.findElement(By.id("vDATO4"));
			compañia.clear();
			compañia.sendKeys(empresa);
			//Ingresar el Detalle
			WebElement detalle=driver.findElement(By.id("vDATO5"));
			detalle.clear();
			detalle.sendKeys("NOMINA");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			//Guardar Datos
			driver.findElement(By.xpath("//span[@onclick=\"gx.evt.doClick('BUTTON1', event)\"]")).click();
			Thread.sleep(5000);
		}
		catch (Exception e) {
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG:::::::::::::::::::::::::::::::::::Error en Dispersion, Se cerrara el navegador");
			driver.quit();
			System.exit(0);
		}
	}
	public  void alerta(WebDriver driver) throws InterruptedException{
		try {
			String alerta=null;
			alerta=driver.switchTo().alert().getText();
			System.out.println("LOG:::::::::::EL MENSAJE DE LA ALERTA ES  "+ alerta+":::::::::::::::::::::");
			driver.switchTo().alert().accept();
			Thread.sleep(6000);	
		}
		catch (Exception e) {
			System.out.println("LOG::::::::::::::::::::::::::::::::::::::::: Error en Alerta, Se cerrara el navegador");
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
		}
	}
	public  void activar(WebDriver driver) throws InterruptedException{
		try {
			//Activar checks
			List <WebElement> checks=driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));
			System.out.println("LOG::::::::::::::::::::::   ACTIVAR CHECKS  :::::::::::::::::::::::::::::::");
			for(WebElement b:checks) {
				if(b.getAttribute("id").contains("vMON")) {
					b.click();
				}
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			Thread.sleep(3000);
		}
		catch (Exception e) {
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG::::::::::::::::::::::::Error en Activar, Se cerrara el navegador");
			driver.quit();
			System.exit(0);
		}
	}
	public  void consultarDatos(WebDriver driver,JavascriptExecutor jse,String ruta) throws InterruptedException{
		try {
			jse.executeScript("scroll(0,2000);");
			//Ir a la  última página
			System.out.println("LOG::::::::::::::::::::::::Seleccionar la ultima pagina:::::::::::::::::::::::::::::::::::::");
			driver.findElement(By.name("IMAGE13")).click();
			Thread.sleep(2000);
			//Dar play al último registro
			System.out.println("LOG::::::::::::::::::::::::Seleccionar el ultimo registro:::::::::::::::::::::::::::::::::::::");
			List <WebElement> registros=driver.findElements(By.xpath("//*[contains(@id,'vBOTON')]"));
			int reg=registros.size();
			int ultimo=0;
			for(WebElement a:registros) {
				ultimo=ultimo+1;
				if(ultimo==reg) {
					a.click();
					Thread.sleep(2000);
				}
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			//Imprimir registros
			driver.findElement(By.className("Btn_ActionBar")).click();
			Thread.sleep(4000);
			WebElement datos=driver.findElement(By.tagName("pre"));
			String layout=datos.getText();
			System.out.println(layout);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			File archivo=new File(ruta);
			//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
			FileWriter escribir=new FileWriter(archivo,true);
			//Escribimos en el archivo con el metodo write
			escribir.write(layout);
			//Cerramos la conexion
			escribir.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("LOG:::::::::::::::::::::::::Error al consultar datos, Se cerrara el navegador");
			driver.quit();
			System.exit(0);
		}
	}
	public  void Calculo(WebDriver driver) throws Exception{
		try {
			driver.switchTo().frame(0);
			String total=driver.findElement(By.id("span_vTOTPER")).getText();
			total = total.replaceAll(",","");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			double valor=Double.parseDouble(total);
			driver.switchTo().defaultContent();
			if(valor==0) {
				driver.findElement(By.name("BTN_PROACT")).click();
				Thread.sleep(6000);
				driver.switchTo().frame(1);
				driver.findElement(By.name("BUTTON1")).click();
				Thread.sleep(2000);
				System.out.println("LOG::::::::::CERRAMOS EL POP UP");
				driver.switchTo().defaultContent();
				Thread.sleep(6000);
				driver.findElement(By.name("BSELMON")).click();
				System.out.println("LOG::::::::::INGRESAR A MONITOREO");
				Thread.sleep(3000);
				//validar porcentaje
				driver.switchTo().frame(0);
				String Avance=driver.findElement(By.id("span_VHPARALELO_PORCENTAJE_0001")).getText();
				System.out.println(Avance);
				int Porcentaje=Integer.parseInt(Avance);
				while(Porcentaje<=99) {
					driver.findElement(By.name("IMAGE4")).click();
					Thread.sleep(2000);
					Avance=driver.findElement(By.id("span_VHPARALELO_PORCENTAJE_0001")).getText();
					Porcentaje=Integer.parseInt(Avance);
				}
				driver.switchTo().defaultContent();
				Thread.sleep(2000);
				driver.findElement(By.name("BCALCULO")).click();
				Thread.sleep(2000);
				System.out.println("LOG::::::::::INGRESAR A CALCULO");
				Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
						System.getProperty("link"), this.getClass().getSimpleName());
				driver.switchTo().defaultContent();
			}
			else {
				System.out.println("LOG::::::::::HAY VALORES PARA GENERAR DISPERSION");
				Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
						System.getProperty("link"), this.getClass().getSimpleName());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en Nomina, Se cerrará el navegador");
			driver.quit();
			System.exit(0);
		}
	}
}
