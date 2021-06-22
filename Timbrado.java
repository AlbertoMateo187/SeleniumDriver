package com.interware.humansite;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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


public class Timbrado {

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
			TimbradoTest(driver,jse);
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

	private void TimbradoTest(WebDriver driver,JavascriptExecutor jse) throws Exception {
		try {
			Properties propiedades = new Properties();
			String projectPaht=System.getProperty("user.dir");
			String ruta=projectPaht+"/src/test/java/com/interware/humansite/PropertiesTimbrado";
			propiedades.load(new FileInputStream( ruta));
			String user = propiedades.getProperty("usuario");
			String pass = propiedades.getProperty("pass");
			String url = propiedades.getProperty("url");
			String empresa = propiedades.getProperty("empresa");
			String razonSocial = propiedades.getProperty("razonSocial");
			String concepto1=propiedades.getProperty("concepto1");
			String percepcion=propiedades.getProperty("percepcion");
			String nombrePercepcion=propiedades.getProperty("nombrePercepcion");
			String opcion1=propiedades.getProperty("opcion1");
			String opcion2=propiedades.getProperty("opcion2");
			String opcion3=propiedades.getProperty("opcion3");
			String agrupacion=propiedades.getProperty("agrupacion");
			String deduccion=propiedades.getProperty("deduccion");
			String conceptoAgrup=propiedades.getProperty("conceptoAgrup");
			String impuestoRetenido=propiedades.getProperty("impuestoRetenido");
			String idPercepcion=propiedades.getProperty("idPercepcion");
			String valorPercepcion=propiedades.getProperty("valorPercepcion");
			String idPercepcion2=propiedades.getProperty("idPercepcion2");
			String valorPercepcion2=propiedades.getProperty("valorPercepcion2");
			String tipoTabla=propiedades.getProperty("tipoTabla");
			String idTabla=propiedades.getProperty("idTabla");
			String entradaCFDI1=propiedades.getProperty("entradaCFDI1");
			String entradaCFDI2=propiedades.getProperty("entradaCFDI2");
			String entradaCFDI3=propiedades.getProperty("entradaCFDI3");
			String entradaCFDI4=propiedades.getProperty("entradaCFDI4");
			String entradaCFDI5=propiedades.getProperty("entradaCFDI5");
			String COM29entrada1=propiedades.getProperty("COM29entrada1");
			String COM29entrada2=propiedades.getProperty("COM29entrada2");
			String salida=propiedades.getProperty("salida");
			String tipoTabla2=propiedades.getProperty("tipoTabla2");
			String idTabla2=propiedades.getProperty("idTabla2");
			String COM29salida1=propiedades.getProperty("COM29salida1");
			String COM29salida2=propiedades.getProperty("COM29salida2");
			String[] entradas = {entradaCFDI1,entradaCFDI2,entradaCFDI3,entradaCFDI4,entradaCFDI5};
			String[] entradasCFDI = {COM29entrada1,COM29entrada2};
			String[] salidasCFDI = {COM29salida1,COM29salida2};
			int idflujo=0;
			Timbrado puente = new Timbrado ();
			driver.navigate().to(System.getProperty("application_web_url"));
			driver.manage().window().maximize();
			Thread.sleep(2000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
				System.getProperty("link"), this.getClass().getSimpleName());
			System.out.println("***************INICIO DEL FLUJO DE TIMBRADO***************");
			//Ingresar Empresa
			puente.validarEmpresa(driver,empresa,razonSocial);
			//Ingresar User y Pass
			puente.login(driver,user, pass,url);
			//Seleccionar la opción Nómina
			puente.nomina(driver,opcion1,jse);
			//Buscar Deducción
			puente.deducciones(driver,jse);
			//Buscar AGRUPACIÓN
			puente.buscarRegistro(driver,agrupacion,idflujo,jse);
			//Buscar DEDUCCIÓN
			idflujo=1;
			puente.buscarRegistro(driver,deduccion,idflujo,jse);
			//Seleccionar opción Conceptos
			puente.nomina(driver,opcion2,jse);
			//Concepto ISR
			puente.consultarConcepto(driver,concepto1,impuestoRetenido,jse);
			//Buscar el concepto COM25 CFD Impuestos Retenidos
			idflujo=2;
			puente.buscarRegistro(driver,conceptoAgrup,idflujo,jse);
			//Validar Check Activo del impuesto retenido
			puente.validarCheck(driver,impuestoRetenido,idflujo);
			System.out.println("LOG::::::::::Termina flujo de IMPUESTO RETENIDO ISR");
			//Buscar el Concepto DED01 CFD Deducciones
			idflujo=3;
			puente.buscarRegistro(driver,agrupacion,idflujo,jse);
			//Validar Check Activo de la Deducción
			puente.validarCheck(driver,impuestoRetenido,idflujo);
			System.out.println("LOG::::::::::Termina flujo de DEDUCCION ISR");
			//Cerrar pantalla de Conceptos de Deducciones
			puente.cambiarConcepto(driver);
			//Buscar Concepto Percepciones
			puente.consultarConcepto(driver,percepcion,nombrePercepcion,jse);
			//Buscar el concepto de Percepción COM45 CFDI Total Gravado Percepciones 
			idflujo=4;
			puente.buscarRegistro(driver,idPercepcion,idflujo,jse);
			//Validar checks
			puente.validarCheck(driver,valorPercepcion,idflujo);
			System.out.println("LOG::::::::::Termina flujo de Total Gravado Percepciones	");
			//Buscar el concepto de Percepción PER01 CFDI Percepciones 
			idflujo=5;
			puente.buscarRegistro(driver,idPercepcion2,idflujo,jse);
			//Validar checks
			puente.validarCheck(driver,valorPercepcion2,idflujo);
			System.out.println("LOG::::::::::Termina flujo de Sueldos, Salarios Rayas y Jornales");
			//Cerrar pantalla de Conceptos de Percepciones
			puente.cambiarConcepto(driver);
			//Seleccionar opción Tablas y constantes 
			puente.nomina(driver,opcion3,jse);
			//Seleccionar Tabla alfanumérica-alfanumérica larga
			puente.tabla(driver,tipoTabla);
			//Buscar CFDI Generalidades CFDI
			idflujo=6;
			puente.buscarRegistro(driver,idTabla,idflujo,jse);
			//Buscar los valores de entrada de CFDI Generalidades CFDI
			puente.valoresEntradas(driver,entradas,salida,salidasCFDI,idflujo,jse);
			System.out.println("LOG::::::::::Termina flujo de CFDI Generalidades CFDI");
			//Seleccionar Tabla alfanumérica - alfanumérica
			puente.tabla(driver,tipoTabla2);
			//Buscar CFDI Generalidades CFDI
			puente.buscarRegistro(driver,idTabla2,idflujo,jse);
			//Buscar los valores de entrada CFDI Generalidades CFDI
			idflujo=7;
			puente.valoresEntradas(driver,entradasCFDI,salida,salidasCFDI,idflujo,jse);
			System.out.println("***************FIN DEL FLUJO DE TIMBRADO***************");
		}
		catch (FileNotFoundException e) {
			System.out.println("LOG::::::::::Error, El archivo no existe");
		} 
		catch (IOException e) {
			System.out.println("LOG::::::::::Error, No se puede leer el archivo");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("LOG::::::::::Error, Se cerrarA el navegador");
			driver.quit();
		}
	}
	public  void validarEmpresa(WebDriver driver,String empresa,String razonSocial)  throws InterruptedException, IOException{	
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
			System.out.println("LOG:::::::::::WARNING");
			System.out.println("LOG::::::::::" +  alerta +"(ALERTA)");
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
			if(currentURL!=url) {
				System.out.println("LOG::::::::::ACCEDIMOS AL SISTEMA");
			}
			else {
			System.out.println("LOG::::::::::NO ACCEDIMOS AL SISTEMA");
			driver.quit();
			System.exit(0);
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}	
	}
	public  void nomina(WebDriver driver,String opcion1,JavascriptExecutor jse) throws Exception{		
		try {
			//Clic a la opción Nómina
			WebElement nomina=driver.findElement(By.id("MPW0005TEXTBLOCK5"));
			nomina.click();
			Thread.sleep(3000);
			//Clic a la opción ENgrane
			WebElement engrane=driver.findElement(By.name("MPW0005vCONFIG"));
			engrane.click();
			Thread.sleep(4000);
			//Seleccionar una opción del menú
			WebElement agrupaciones=driver.findElement(By.partialLinkText(opcion1));
			agrupaciones.click();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			Thread.sleep(2000);
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN NOMINA");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void deducciones(WebDriver driver,JavascriptExecutor jse) throws InterruptedException{		
		try {
			WebElement tituloPantalla=driver.findElement(By.id("TEXTBLOCK2"));
			System.out.println("LOG::::::::::ENTRAMOS A LA PANTALLA " + tituloPantalla.getText());
			jse.executeScript("scroll(0,100);");
			Thread.sleep(3000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN DEDUCCIONMES");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void buscarRegistro(WebDriver driver,String valor1,int flujo,JavascriptExecutor jse) throws InterruptedException{		
		try {
			//Buscar Registro
			System.out.println("LOG::::::::::EL VALOR A BUSCAR ES: " + valor1);
			List <WebElement> id=driver.findElements(By.xpath("//*[contains(@gxrow,00)]"));
			int 
			Lista=id.size(),
			b=0,
			x=0;
			;
			boolean cond=false;
			while(cond==false) {
				for(WebElement a:id) {
					b=b+1;
					x=x+1;
					if(a.getText().contains(valor1)) {
						System.out.println("LOG::::::::::ENCONTRADO");
						a.click();
						Thread.sleep(3000);
						cond=true;
						break;
					}
					if(b==Lista) {
						cond=false;
						if(flujo<2) {
							driver.findElement(By.className("PagingButtonsNext")).click();
							Thread.sleep(3000);
						}
						else {
							driver.findElement(By.id("IMAGE4")).click();
							Thread.sleep(3000);
						}
						jse.executeScript("scroll(0,-270);");
						Thread.sleep(3000);
						b=0;
						id=driver.findElements(By.xpath("//*[contains(@gxrow,00)]"));
					
					}
					if(x>=500) {
						System.out.println("LOG::::::::::NO FUE ENCONTRADO EL REGISTRO");
						Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
								System.getProperty("link"), this.getClass().getSimpleName());
						driver.quit();
						System.exit(0);
					}
				}
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			switch(flujo) {
				case 0:
					//Consultar Agrupación
					driver.findElement(By.name("IMAGE7")).click();
					Thread.sleep(4000);
					System.out.println("LOG::::::::::Termina flujo de busqueda de AGRUPACION");
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());
					break;
				case 1:
					//Consultar Deducción
					System.out.println("LOG::::::::::Termina flujo de busqueda de DEDUCCION");
					Thread.sleep(2000);
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());
					break;
				case 2:
					//Consultar Deducción
					System.out.println("LOG::::::::::Termina flujo de busqueda de COM25 CFDI Impuestos Retenidos");
					Thread.sleep(2000);
					//Dar clic al Detalle de COM25 CFDI Impuestos Retenidos
					if(b<10) {
						driver.findElement(By.id("span_vDETALLE_000"+b)).click();
						Thread.sleep(4000);
					}
					else {
						driver.findElement(By.id("span_vDETALLE_00"+b)).click();
						Thread.sleep(4000);
					}
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());
					break;
				case 3:
					//Consultar Deducción
					System.out.println("LOG::::::::::Termina flujo de busqueda de DED01 CFDI Deducciones");
					Thread.sleep(2000);
					//Dar clic al Detalle de COM25 CFDI Impuestos Retenidos
					if(b<10) {
						driver.findElement(By.id("span_vDETALLE_000"+b)).click();
						Thread.sleep(4000);
					}
					else {
						driver.findElement(By.id("span_vDETALLE_00"+b)).click();
						Thread.sleep(4000);
					}
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());
					break;
		case 4:
			//Consultar Deducción
			System.out.println("LOG::::::::::Termina flujo de busqueda de COM45	CFDI Total Gravado Percepciones");
			Thread.sleep(2000);
			//Dar clic al Detalle de COM45	CFDI Total Gravado Percepciones
			if(b<10) {
				driver.findElement(By.id("span_vDETALLE_000"+b)).click();
				Thread.sleep(4000);
			}
			else {
				driver.findElement(By.id("span_vDETALLE_00"+b)).click();
				Thread.sleep(4000);
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			break;
		case 5:
			//Consultar Deducción
			System.out.println("LOG::::::::::Termina flujo de busqueda de PER01 CFDI Percepciones");
			Thread.sleep(2000);
			//Dar clic al Detalle de PER01 CFDI Percepciones
			if(b<10) {
				driver.findElement(By.id("span_vDETALLE_000"+b)).click();
				Thread.sleep(4000);
			}
			else {
				driver.findElement(By.id("span_vDETALLE_00"+b)).click();
				Thread.sleep(4000);
			}
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			break;
		case 6:
			//Dar clic a la opción Modificar
			driver.findElement(By.name("IMAGE5")).click();
			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			break;
		}
			}
		catch ( Exception e ) {
			System.out.println("LOG::::::::::NO FUE ENCONTRADO EL REGISTRO");
			e.printStackTrace();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void consultarConcepto(WebDriver driver,String concepto1,String nombreConcepto,JavascriptExecutor jse) throws InterruptedException{		
		try {
			//Consultar Nombre de la pantalla
			WebElement titulo=driver.findElement(By.id("TEXTBLOCK63"));
			System.out.println("LOG::::::::::El nombre de la pantalla es "+ titulo.getText());
			//Ingresar el ID del concepto
			driver.findElement(By.id("vB_NOMBRE")).sendKeys(concepto1);
			//Consultar concepto
			driver.findElement(By.name("IMAGE1")).click();
			Thread.sleep(3000);
			WebElement nombre=driver.findElement(By.id("span_vB_GLOSA"));
			if(nombre.getText().equals(nombreConcepto)) {
			WebElement tipoConcepto=driver.findElement(By.id("span_vTIPCONFORDSC"));
			System.out.println("LOG::::::::::EL TIPO DE CONCEPTO ES "+tipoConcepto.getText() );
			jse.executeScript("scroll(0,600);");
			//Modificar concepto
			driver.findElement(By.name("IMAGE20")).click();
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.manage().window().maximize();
			jse.executeScript("scroll(0,400);");
			Thread.sleep(3000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			}
			else {
				System.out.println("LOG::::::::::NO ES EL NOMBRE CORRECTO");
				Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
						System.getProperty("link"), this.getClass().getSimpleName());
			}
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::::::::::::::::::::::::::ERROR EN CONSULTAR CONCEPTO::::::::::::::");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void validarCheck(WebDriver driver,String valor1,int idflujo) throws InterruptedException{		
		try {
			//Verificar checkbox activo ISR
			driver.switchTo().frame(0);
			Thread.sleep(2000);
			int x=0;
			WebElement activo=null;
			List <WebElement> impuestos=driver.findElements(By.xpath("//*[contains(@id,'Grid1ContainerRow_')]"));
				for(WebElement a:impuestos) {
					x=x+1;
					if(a.getText().contains(valor1)) {
						activo=driver.findElement(By.name("CTLAPLICA_000"+x));
						if (activo.isSelected()==false) {
							activo.click();
							Thread.sleep(1000);
						}
						driver.findElement(By.name("IMAGE3")).click();
						Thread.sleep(1000);
						break;
					}
				}
			//Salir del frame
			driver.switchTo().defaultContent();
			Thread.sleep(1000);
			//Cerrar el pop up
			driver.findElement(By.id("gxp0_cls")).click();
			Thread.sleep(1000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::::::::::::::::::::::ERROR EN VALIDAR CHECK:::::::::::::::::::");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void cambiarConcepto(WebDriver driver) throws InterruptedException{		
		try {
			ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
			driver.close();
			driver.switchTo().window(tabs.get(0));
			System.out.println("LOG::::::::::PERCEPCIONES");
			//Limpiar Pantalla
			driver.findElement(By.id("vB_NOMBRE")).clear();
			Thread.sleep(2000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN CAMBIAR CONCEPTO");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
			System.exit(0);
		}
	}
	public  void tabla(WebDriver driver,String tipoTabla) throws InterruptedException{		
		try {
			Thread.sleep(5000);
			//Seleccionar la Tabla deseada
			WebElement tabla=driver.findElement(By.id("vTABLAYCONSTANTE"));
			Select opcion = new Select (tabla);	
			opcion.selectByVisibleText(tipoTabla);
			Thread.sleep(2000);
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.switchTo().frame(0);
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN TABLA");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
		}
	}
	public  void valoresEntradas(WebDriver driver,String[] entradas,String salida,String[] salidasCFDI,int flujo,JavascriptExecutor jse) throws InterruptedException{		
		try {
			Thread.sleep(2000);
			driver.switchTo().frame(0);
			int i=0,
				b=entradas.length;
			List <WebElement> valores=driver.findElements(By.xpath("//*[contains(@id,'span_TA')]"));
			for(i=0;i<entradas.length;i++) {
				for(WebElement c:valores) {
					if(c.getText().contains(entradas[i])) {
						switch(flujo) {
							case 6:
								if(c.getText().contains(entradas[4])) {
									WebElement texto=driver.findElement(By.name("TAAL_OUT_000"+b));
									if(texto.getAttribute("value").contains(salida)) {
										System.out.println("LOG::::::::::LA ENTRADA " + entradas[4] + " SI TIENE LA SALIDA ESPERADA");
									}
									else {
										System.out.println("LOG::::::::::LA ENTRADA " + entradas[4] + " NO TIENE LA SALIDA ESPERADA"+ "LA SALIDA MOSTRADA ES " + texto.getAttribute("value"));
									}
								}
								Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
										System.getProperty("link"), this.getClass().getSimpleName());
									break;
							case 7:
								int d=1;
								WebElement texto=driver.findElement(By.id("TAA_OUT_000"+d));
								d=d+1;
								if(texto.getAttribute("value").contains(salidasCFDI[i])) {
									System.out.println("LOG::::::::::LA ENTRADA " + entradas[i] + " SI TIENE LA SALIDA ESPERADA");
									System.out.println("LOG::::::::::FLUJO CONCLUIDO");
									Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
											System.getProperty("link"), this.getClass().getSimpleName());
								}
								else {
									System.out.println("LOG::::::::::LA ENTRADA " + entradas[i] + " NO TIENE LA SALIDA ESPERADA. "+ "LA SALIDA MOSTRADA ES " + texto.getAttribute("value"));
									System.out.println("LOG::::::::::FLUJO CONCLUIDO");
									Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
											System.getProperty("link"), this.getClass().getSimpleName());
									
								}
								Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
										System.getProperty("link"), this.getClass().getSimpleName());
								break;
							}
					}
				}
			}
			if(i==entradas.length) {
				System.out.println("LOG::::::::::SE ENCONTRARON TODAS LAS ENTRADAS");
				Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
						System.getProperty("link"), this.getClass().getSimpleName());
			}
			else {
				System.out.println("LOG::::::::::NO SE ENCONTRARON TODAS LAS ENTRADAS:::::::::::::::::::::");
				Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
						System.getProperty("link"), this.getClass().getSimpleName());
			}
			//Cerrar Pantalla Tablas & Constantes
			jse.executeScript("scroll(0,600);");
			driver.findElement(By.name("BUTTON2")).click();
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
		}
		catch ( Exception e ) {
			e.printStackTrace();
			System.out.println("LOG::::::::::ERROR EN VALORES ENTRADA");
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			driver.quit();
		}
	}

}