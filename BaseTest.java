package com.interware.humansite;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.interware.humansite.util.Utils;

public class BaseTest {

	@Test
	public void test_dashboard_browser() throws MalformedURLException, InterruptedException {
		// Cargamos el tipo de navegador
		DesiredCapabilities capabilities;
		capabilities = Utils.loadBrowser(System.getProperty("browser"));
		
		System.out.println("########################################" + System.getProperty("selenium_grid_server_url"));
		final WebDriver driver = new RemoteWebDriver(new URL(System.getProperty("selenium_grid_server_url")),
				capabilities);
		
		try {
			//testDashboard(driver);
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
	
	private void testDashboard(WebDriver driver) throws Exception {
		
		driver.navigate().to(System.getProperty("application_web_url"));
		Thread.sleep(4000);
		
		driver.manage().window().maximize();
		driver.navigate().refresh();
		System.out.println("****** application_web_url:" + System.getProperty("application_web_url"));
		Thread.sleep(4000);

		Utils.takeScreenshotTop(driver, System.getProperty("path"), System.getProperty("browser"),
				System.getProperty("link"), this.getClass().getSimpleName());
	}

}
