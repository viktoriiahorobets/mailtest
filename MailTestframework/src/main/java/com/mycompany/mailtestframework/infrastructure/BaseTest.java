
package com.mycompany.mailtestframework.infrastructure;

import io.github.bonigarcia.wdm.*;
import java.util.concurrent.*;
import javax.mail.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

public class BaseTest {

	protected IConfigurationService configurationService;
	protected IMailService mailService;
	protected WebDriver driver;
	
	public BaseTest (){
		configurationService = Services.getConfigurationService();
		mailService = Services.getMailService();
		
	}
	
	@BeforeTest
	public void beforeTest() throws MessagingException {
		mailService.clearInbox();
		initChromeDriver();
		driver.manage().deleteAllCookies();
		login();
	}
	
	@AfterTest
	public void afterTest() throws MessagingException {
		shutDownDriver();
	}
	
	private void initChromeDriver(){
		WebDriverManager.chromedriver().version(configurationService.getChromeDriverVersion()).setup();
		driver = new ChromeDriver();
	}
	
	private void shutDownDriver(){
		driver.quit();
	}
	
	private void login(){
		driver.get("https://accounts.google.com/signin");
		driver.findElement(By.id("identifierId")).sendKeys(configurationService.getUser());
		driver.findElement(By.id("identifierNext")).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.name("password")).sendKeys(configurationService.getPassword());
		driver.findElement(By.id("passwordNext")).click();
	}

	
}
