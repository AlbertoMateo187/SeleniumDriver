
package com.interware.humansite;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.interware.humansite.util.Utils;

public class LoginTests {
	
	/*
	@Test
	public void baseMethod() throws Exception {
		// Cargamos el tipo de navegador
		DesiredCapabilities capabilities;
		capabilities = Utils.loadBrowser(System.getProperty("browser"));
		
		// Generamos el WebDriver con la URL del hub donde se ejecutara la prueba
		System.out.println("########################################" + System.getProperty("selenium_grid_server_url"));
		final WebDriver driver = new RemoteWebDriver(new URL(System.getProperty("selenium_grid_server_url")),
				capabilities);
		
		//Inicializamos el contador de ScreenShots
		int countImage = 1;
		try {
			
			//Cargamos la pagina
			driver.get(System.getProperty("application_web_url"));
			driver.manage().window().maximize();
			
			//Tomamos ScreenShot
			countImage = Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), countImage);
			Thread.sleep(2000);
			
			//Cerramos el driver
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
    */

	@Test
	public void testLogin() throws Exception {
		// Cargamos el tipo de navegador
		DesiredCapabilities capabilities;
		capabilities = Utils.loadBrowser(System.getProperty("browser"));
		
		// Cargamos la URL del hub donde se ejecutara la prueba
		System.out.println("########################################" + System.getProperty("selenium_grid_server_url"));
		final WebDriver driver = new RemoteWebDriver(new URL(System.getProperty("selenium_grid_server_url")),
				capabilities);
		
		try {
			//Iniciamos el test
			login(driver);
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("### ERROR MESSAGE: " + e.getMessage());
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	public void login(WebDriver driver) throws Exception {
		String newURL = null;
		String alerta = null;
		// String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new
		// Date());
		
		// TODO: Los siguientes datos se obtienen de la entrada.
		String empresa = System.getProperty("link").toUpperCase();
		String url = System.getProperty("application_web_url");
		// String url = "https://qa_knr.humansite.com.mx/hlogin.aspx";
		
		// TODO: Estos datos no se pueden obtener de los datos del flujo CI/CD
		String razonSocial = "LABORATORIOS KENER";
		String user = "EQUIPOQA";
		String pass = "124578";
		
		
		try {
			// Cargamos pagina inicial
			driver.get(System.getProperty("application_web_url"));
			driver.manage().window().maximize();
			
			// Tomamos ScreenShot
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			Thread.sleep(2000);
			
			// Login
			System.out.println("LOGIN::::::::::::::: PANTALLA DE LOGIN");
			driver.findElement(By.id("vMB_EPR_COD")).sendKeys(empresa);
			Thread.sleep(2000);
			System.out.println("LOG::::::::::: SE INGRESO LA EMPRESA");
			
			// Validar nombre de la empresa
			driver.findElement(By.id("IMAGE2")).click();
			Thread.sleep(1000);
			
			alerta = driver.switchTo().alert().getText();
			System.out.println("Algo ha salido mal");
			System.out.println(alerta);
			
			// Tomamos ScreenShot
			Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
					System.getProperty("link"), this.getClass().getSimpleName());
			
			driver.switchTo().alert().accept();
			System.out.println("Se va cerrar la pantalla");
			driver.close();
		} catch (Exception r) {
			try {
				//Validamos datos de la empresa
				WebElement nombre = driver.findElement(By.tagName("text"));
				System.out.println("LOG::::::::::::::::: EL NOMBRE DE LA EMPRESA SE ENCONTRO: " + nombre.isDisplayed());
				System.out.println("LOG::::::::::::::::: EL NOMBRE DE LA EMPRESA: " + nombre.getText());
				if (nombre.getText().equals(razonSocial)) {
					
					//Asignamos valor del usuario
					System.out.println("LOG::::::::::::::::: EL NOMBRE DE LA EMPRESA COINCIDE");
					driver.findElement(By.id("vUSUID")).sendKeys(user);
					Thread.sleep(1000);
					System.out.println("LOG::::::::::: SE INGRESO EL USUARIO");
					
					//Asignamos valor del password
					
					driver.findElement(By.id("vUSUPSW")).sendKeys(pass);
					Thread.sleep(1000);
					System.out.println("LOG::::::::::: SE INGRESO EL PASSWORD");
					
					// Tomamos ScreenShot
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());
					
					//Ingresamos a la apliacion
					driver.findElement(By.id("vIMAGEN8")).click();
					
					//Tomamos ScreenShot
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());
					Thread.sleep(4000);
					
					alerta = driver.switchTo().alert().getText();
					System.out.println("Algo ha salido mal");
					System.out.println(alerta);
					
					//Tomamos ScreenShot
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());

					driver.switchTo().alert().accept();
					System.out.println("Se va cerrar la pantalla");
					driver.close();
				}
			} catch (Exception e) {
				//Validamos que nos encontremos en una URL diferente a la inicial
				newURL = driver.getCurrentUrl();
				if (newURL != url) {
					//Accedimos al sistema
					System.out.println("LOG::::::::::::::::::::::: ACCEDIMOS AL SISTEMA");
					System.out.println("LOG::::::::::: CERRAR SESION");
					
					//Vamos a salir de la aplicacion
					JavascriptExecutor jse = Utils.getJse(driver);
					jse.executeScript("scroll(100,0);");
					Thread.sleep(1000);
					driver.findElement(By.id("MPW0005SALIR")).click();
					Thread.sleep(1000);
					
					// ### Cambiamos la forma de guardar la evidencia a screenshot
					Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
							System.getProperty("link"), this.getClass().getSimpleName());

				} else {
					System.out.println("No se accedio al sistema");
				}
			}
		}
	}
}

