package nomina;
import java.io.File;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;


	public class timbrado {
		public static WebDriver driver;
		public static JavascriptExecutor jse;
		public static String proyecto;
			static {
				driver=getDriver();
				jse=getJse();
		}
			public static WebDriver getDriver() {
				//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\chromedriver.exe");
				//WebDriver driver = new ChromeDriver();
				System.setProperty("webdriver.gecko.driver","C:\\Users\\Mario\\Downloads\\test_automation\\drivers\\geckodriver.exe");
				WebDriver driver = new FirefoxDriver();
				return driver;
			}
			public static JavascriptExecutor getJse() {
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				return jse;
			}	
			public static void main(String[] args)  throws Throwable{
				try {	
					Properties propiedades = new Properties();
					propiedades.load(new FileInputStream( "C:\\Users\\Mario\\flujosHS\\src\\propiedades\\archivo.properties"));
					String user = propiedades.getProperty("usuario");
					String pass = propiedades.getProperty("pass");
					String url = propiedades.getProperty("url");
					String empresa = propiedades.getProperty("empresa");
					String razonSocial = propiedades.getProperty("raz�nSocial");
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
					driver.get(url);
					driver.manage().window().maximize();
					Thread.sleep(2000);
					//Ingresar Empresa
					timbrado.validarEmpresa(empresa,razonSocial);
					//Ingresar User y Pass
					timbrado.login(user, pass,url);
					//Seleccionar la opci�n N�mina
					timbrado.nomina(opcion1);
					//Buscar Deducci�n
					timbrado.deducciones();
					//Buscar AGRUPACI�N
					timbrado.buscarRegistro(agrupacion,idflujo);
					//Buscar DEDUCCI�N
					idflujo=1;
					timbrado.buscarRegistro(deduccion,idflujo);
					//Seleccionar opci�n Conceptos
					timbrado.nomina(opcion2);
					//Concepto ISR
					timbrado.consultarConcepto(concepto1,impuestoRetenido);
					//Buscar el concepto COM25 CFD Impuestos Retenidos
					idflujo=2;
					timbrado.buscarRegistro(conceptoAgrup,idflujo);
					//Validar Check Activo del impuesto retenido
					timbrado.validarCheck(impuestoRetenido,idflujo);
					System.out.println("Termina flujo de IMPUESTO RETENIDO ISR");
					//Buscar el Concepto DED01 CFD Deducciones
					idflujo=3;
					timbrado.buscarRegistro(agrupacion,idflujo);
					//Validar Check Activo de la Deducci�n
					timbrado.validarCheck(impuestoRetenido,idflujo);
					System.out.println("Termina flujo de DEDUCCI�N ISR");
					//Cerrar pantalla de Conceptos de Deducciones
					timbrado.cambiarConcepto();
					//Buscar Concepto Percepciones
					timbrado.consultarConcepto(percepcion,nombrePercepcion);
					System.out.println("HASTA AQUI VAMOS BIEN");
					//Buscar el concepto de Percepci�n COM45 CFDI Total Gravado Percepciones 
					idflujo=4;
					timbrado.buscarRegistro(idPercepcion,idflujo);
					System.out.println("TERMINA EL FLUJO DE B�SQUEDA");
					timbrado.validarCheck(valorPercepcion,idflujo);
					System.out.println("Termina flujo de Total Gravado Percepciones	");
					//Buscar el concepto de Percepci�n PER01 CFDI Percepciones 
					idflujo=5;
					timbrado.buscarRegistro(idPercepcion2,idflujo);
					System.out.println("TERMINA EL FLUJO DE B�SQUEDA");
					timbrado.validarCheck(valorPercepcion2,idflujo);
					System.out.println("Termina flujo de Sueldos, Salarios Rayas y Jornales");
					//Cerrar pantalla de Conceptos de Percepciones
					timbrado.cambiarConcepto();
					//Seleccionar opci�n Tablas y constantes 
					timbrado.nomina(opcion3);
					//Seleccionar Tabla alfanum�rica-alfanum�rica larga
					timbrado.tabla(tipoTabla);
					//Buscar CFDI Generalidades CFDI
					idflujo=6;
					timbrado.buscarRegistro(idTabla,idflujo);
					//Buscar los valores de entrada de CFDI Generalidades CFDI
					timbrado.valoresEntradas(entradas,salida,salidasCFDI,idflujo);
					//Seleccionar Tabla alfanum�rica - alfanum�rica
					timbrado.tabla(tipoTabla2);
					//Buscar CFDI Generalidades CFDI
					timbrado.buscarRegistro(idTabla2,idflujo);
					//Buscar los valores de entrada CFDI Generalidades CFDI
					idflujo=7;
					timbrado.valoresEntradas(entradasCFDI,salida,salidasCFDI,idflujo);
				}
				catch (FileNotFoundException e) {
					System.out.println("Error, El archivo no existe");
				} 
				catch (IOException e) {
					System.out.println("Error, No se puede leer el archivo");
				}
				catch (Exception e) {
					System.out.println("Error, Se cerrar� el navegador");
					driver.quit();
				}
			}
			public static  void validarEmpresa(String empresa,String razonSocial)  throws InterruptedException, IOException{	
				try {
					driver.findElement(By.id("vMB_EPR_COD")).sendKeys(empresa);
					Thread.sleep(3000);
					System.out.println("LOG::::::::::: SE INGRESO LA EMPRESA");
					//Validar nombre de la empresa
					driver.findElement(By.id("IMAGE2")).click();
					Thread.sleep(3000);
					String alerta=null;
					alerta=driver.switchTo().alert().getText();
					System.out.println("::::::::::::::::Algo ha salido mal::::::::::::::::::::::::::");
					System.out.println("LOG:::::::::::::::::::::::" +  alerta);

				}
				catch( Exception e ){
					WebElement nombre=driver.findElement(By.id("span_vMB_EPR_IDS"));
					if(nombre.getText().equals(razonSocial)) {
						System.out.println("LOG::::::::::::::: LA EMPRESA ES CORRECTA");
				}
				}
			}
			public static void login(String user,String pass,String url) throws Exception{		
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
					driver.quit();
				}
				catch( Exception e ) {
					String currentURL=null;
					currentURL=driver.getCurrentUrl();
					if(currentURL!=url) {
						System.out.println("LOG::::::::::::::::::::::: ACCEDIMOS AL SISTEMA");
					}
					else {
					System.out.println("LOG::::::::::::::::::::::: NO ACCEDIMOS AL SISTEMA");
					}
				}	
			}
			public static void nomina(String opcion1) throws Exception{		
				try {
					//Clic a la opci�n N�mina
					WebElement nomina=driver.findElement(By.id("MPW0005TEXTBLOCK5"));
					nomina.click();
					Thread.sleep(3000);
					//Clic a la opci�n ENgrane
					WebElement engrane=driver.findElement(By.name("MPW0005vCONFIG"));
					engrane.click();
					Thread.sleep(4000);
					//Seleccionar una opci�n del men�
					WebElement agrupaciones=driver.findElement(By.partialLinkText(opcion1));
					agrupaciones.click();
					Thread.sleep(2000);
				}
				catch ( Exception e ) {
					e.printStackTrace();
					driver.quit();
				}
			}
			public static void deducciones() throws InterruptedException{		
				try {
					WebElement tituloPantalla=driver.findElement(By.id("TEXTBLOCK2"));
					System.out.println("LOG:::::::::::::::::::::::ENTRAMOS A LA PANTALLA     " + tituloPantalla.getText());
					jse.executeScript("scroll(0,100);");
					Thread.sleep(3000);
				}
				catch ( Exception e ) {
					e.printStackTrace();
					driver.quit();
				}
			}
			public static void buscarRegistro(String valor1,int flujo) throws InterruptedException{		
				try {
					//Buscar Registro
					System.out.println("Los valores a BUSCAR son:::::::::::::::" + valor1);
					List <WebElement> id=driver.findElements(By.xpath("//*[contains(@gxrow,00)]"));
					int 
					tamanoLista=id.size(),
					b=0;
					boolean cond=false;
					while(cond==false) {
						for(WebElement a:id) {
							b=b+1;
							if(a.getText().contains(valor1)) {
								System.out.println("Encontrado");
								//a.click();
								Thread.sleep(2000);
								cond=true;
								break;
							}
							if(b==tamanoLista) {
								cond=false;
								if(flujo<2) {
									driver.findElement(By.className("PagingButtonsNext")).click();
								}
								else {
									driver.findElement(By.id("IMAGE4")).click();
								}
								jse.executeScript("scroll(0,-270);");
								Thread.sleep(4000);
								b=0;
								id=driver.findElements(By.xpath("//*[contains(@gxrow,00)]"));
							
							}
						}
					}
					
					switch(flujo) {
						case 0:
							//Consultar Agrupacion
							driver.findElement(By.id("span_TIPOAGRUPACIONID_0008")).click();
							driver.findElement(By.name("IMAGE7")).click();
							Thread.sleep(4000);
							System.out.println("Termina flujo de b�squeda de AGRUPACI�N");
							break;
						case 1:
							//Consultar Deduccion
							System.out.println("Termina flujo de b�squeda de DEDUCCION");
							Thread.sleep(2000);
							break;
						case 2:
							//Consultar Deduccion
							System.out.println("Termina flujo de b�squeda de COM25 CFDI Impuestos Retenidos");
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
							break;
						case 3:
							//Consultar Deducci�n
							System.out.println("Termina flujo de b�squeda de DED01 CFDI Deducciones");
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
							break;
				case 4:
					//Consultar Deducci�n
					System.out.println("Termina flujo de b�squeda de COM45	CFDI Total Gravado Percepciones");
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
					break;
				case 5:
					//Consultar Deducci�n
					System.out.println("Termina flujo de b�squeda de PER01 CFDI Percepciones");
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
					break;
				case 6:
					//Dar clic a la opci�n Modificar
					driver.findElement(By.name("IMAGE5")).click();
					Thread.sleep(2000);
					driver.switchTo().defaultContent();
					break;
				}
					}
				catch ( Exception e ) {
					System.out.println("LOG::::::::::::::::::::: NO FUE ENCONTRADO EL REGISTRO");
					e.printStackTrace();
					driver.quit();
					System.exit(0);
				}
			}
			public static void consultarConcepto(String concepto1,String nombreConcepto) throws InterruptedException{		
				try {
					//Consultar Nombre de la pantalla
					WebElement titulo=driver.findElement(By.id("TEXTBLOCK63"));
					System.out.println("LOG:::::::::::::::::::::::::: El nombre de la pantalla es "+ titulo.getText());
					//Ingresar el ID del concepto
					driver.findElement(By.id("vB_NOMBRE")).sendKeys(concepto1);
					//Consultar concepto
					driver.findElement(By.name("IMAGE1")).click();
					Thread.sleep(3000);
					WebElement nombre=driver.findElement(By.id("span_vB_GLOSA"));
					if(nombre.getText().equals(nombreConcepto)) {
					WebElement tipoConcepto=driver.findElement(By.id("span_vTIPCONFORDSC"));
					System.out.println("LOG:::::::::::::::::::::::EL TIPO DE CONCEPTO ES ::::::::::::::::"+tipoConcepto.getText() );
					jse.executeScript("scroll(0,600);");
					//Modificar concepto
					driver.findElement(By.name("IMAGE20")).click();
					Thread.sleep(5000);
					ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
					driver.switchTo().window(tabs.get(1));
					driver.manage().window().maximize();
					jse.executeScript("scroll(0,400);");
					Thread.sleep(3000);
					}
					else {
						System.out.println("NO ES EL NOMBRE CORRECTO");
					}
				}
				catch ( Exception e ) {
					e.printStackTrace();
					//driver.quit();
				}
			}
			public static void validarCheck(String valor1,int idflujo) throws InterruptedException{		
				try {
					//Verificar checkbox activo ISR
					driver.switchTo().frame(0);
					Thread.sleep(2000);
					int x=0;
					WebElement activo=null;
					System.out.println(valor1);
					List <WebElement> impuestos=driver.findElements(By.xpath("//*[contains(@id,'Grid1ContainerRow_')]"));
						for(WebElement a:impuestos) {
							x=x+1;
							System.out.println(a.getText());
							if(a.getText().contains(valor1)) {
								activo=driver.findElement(By.name("CTLAPLICA_000"+x));
								System.out.println(activo.isSelected());
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
				}
				catch ( Exception e ) {
					e.printStackTrace();
					//driver.quit();
				}
			}
			public static void cambiarConcepto() throws InterruptedException{		
				try {
					ArrayList<String> tabs = new ArrayList <String>(driver.getWindowHandles());
					driver.close();
					driver.switchTo().window(tabs.get(0));
					System.out.println("::::::::::::::::::::::::::::::::::PERCEPCIONES::::::::::::::::::::::::::::::::::");
					//Limpiar Pantalla
					driver.findElement(By.id("vB_NOMBRE")).clear();
					Thread.sleep(2000);
				}
				catch ( Exception e ) {
					e.printStackTrace();
					driver.quit();
				}
			}
			public static void tabla(String tipoTabla) throws InterruptedException{		
				try {
					Thread.sleep(5000);
					//Seleccionar la Tabla deseada
					WebElement tabla=driver.findElement(By.id("vTABLAYCONSTANTE"));
					Select opcion = new Select (tabla);	
					opcion.selectByVisibleText(tipoTabla);
					Thread.sleep(2000);
					driver.switchTo().frame(0);
				}
				catch ( Exception e ) {
					e.printStackTrace();
					driver.quit();
				}
			}
			public static void valoresEntradas(String[] entradas,String salida,String[] salidasCFDI,int flujo) throws InterruptedException{		
				try {
					Thread.sleep(2000);
					driver.switchTo().frame(0);
					int i=0,
						b=entradas.length;
					System.out.println(b +":::::::::::::::::"+ flujo);
					List <WebElement> valores=driver.findElements(By.xpath("//*[contains(@id,'span_TA')]"));
					for(i=0;i<entradas.length;i++) {
						for(WebElement c:valores) {
							if(c.getText().contains(entradas[i])) {
								System.out.println(i);
								System.out.println(c.getText());
								switch(flujo) {
									case 6:
										if(c.getText().contains(entradas[4])) {
											WebElement texto=driver.findElement(By.name("TAAL_OUT_000"+b));
											System.out.println(texto.getAttribute("value"));
											if(texto.getAttribute("value").contains(salida)) {
												System.out.println("LA ENTRADA :::::::" + entradas[4] + "::::::S� TIENE LA SALIDA ESPERADA");
											}
											else {
												System.out.println("LA ENTRADA::::::" + entradas[4] + ":::::::::::NO TIENE LA SALIDA ESPERADA"+ "LA SALIDA MOSTRADA ES::::" + texto.getAttribute("value"));
											}
										}
											break;
									case 7:
										int d=1;
										System.out.println("VAMOS BIEN");
										WebElement texto=driver.findElement(By.id("TAA_OUT_000"+d));
										System.out.println(texto.getAttribute("value"));
										d=d+1;
										if(texto.getAttribute("value").contains(salidasCFDI[i])) {
											System.out.println("LA ENTRADA :::::::" + entradas[i] + "::::::S� TIENE LA SALIDA ESPERADA");
											System.out.println("FLUJO CONCLUIDO");
											
											driver.quit();
										}
										else {
											System.out.println("LA ENTRADA::::::" + entradas[i] + ":::::::::::NO TIENE LA SALIDA ESPERADA"+ "  LA SALIDA MOSTRADA ES::::" + texto.getAttribute("value"));
											
											System.out.println("FLUJO CONCLUIDO");
											driver.quit();
											
										}
										break;
									}
							}
						}
					}
					if(i==entradas.length) {
						System.out.println(":::::::::::::::::Se encontraron todas las ENTRADAS:::::::::::::::::::::");
					}
					else {
						System.out.println(":::::::::::::::::NO se encontraron las ENTRADAS:::::::::::::::::::::");
					}
					//Cerrar Pantalla Tablas & Constantes
					jse.executeScript("scroll(0,600);");
					driver.findElement(By.name("BUTTON2")).click();
					driver.switchTo().defaultContent();
					Thread.sleep(2000);
				}
				catch ( Exception e ) {
					e.printStackTrace();
					driver.quit();
				}
			}

	}