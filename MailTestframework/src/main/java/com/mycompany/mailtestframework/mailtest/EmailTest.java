package com.mycompany.mailtestframework.mailtest;

import com.mycompany.mailtestframework.infrastructure.*;
import javax.mail.*;
import junit.framework.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

public class EmailTest extends BaseTest {
	
	@Test(priority = 1)
	public void loginToGmail() {
		driver.get("https://myaccount.google.com/personal-info?pli=1");
		driver.findElement(By.xpath(".//*[text()='Sign in']/..")).click();
		String text = driver.findElement(By.xpath("//*")).getText();
		Assert.assertTrue(text.contains(configurationService.getUser()));
	}
	
	@Test(priority = 3)
	public void deleteEmail() throws MessagingException, InterruptedException{
		mailService.sendMail();
		
		driver.get("https://mail.google.com/mail/u/0/?tab=km1#inbox");
		driver.findElements(By.xpath("//*[@role='checkbox']")).get(1).click();
		WebElement element = driver.findElements(By.className("asa")).get(2);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		Thread.sleep(5000);
		
		String text = driver.findElement(By.xpath("//*")).getText();
		Assert.assertFalse(text.contains("Automatically sent Test e-mail"));
	}
	
	@Test(priority = 2)
	public void sendEmail() throws InterruptedException {
		String subject = "This is new sent email!";
		Thread.sleep(5000);
		driver.get("https://mail.google.com/mail/u/0/?tab=km1#inbox");
		WebElement element = driver.findElement(By.className("z0"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
		driver.findElement(By.xpath("//*[text()='To']/../../..//textarea")).sendKeys(configurationService.getUser());
		driver.findElement(By.name("subjectbox")).sendKeys(subject);
		driver.findElement(By.xpath("//div[text()='Send']")).click();
		Thread.sleep(5000);
		String text = driver.findElement(By.xpath("//*")).getText();
		Assert.assertTrue(text.contains(subject));
	}
	
}
