package com.prestashop.tests;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFour {

	WebDriver d;
	Random r;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();// Set up chromedriver path
		d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		d.manage().window().fullscreen();
	}

	@BeforeMethod
	public void beforeMEthod() {
		String url = "http://automationpractice.com/index.php";
		d.get(url);
	}

	@Test
	public void blankPasswordTest() {
		WebElement signInButton = d.findElement(By.xpath("//a[@class='login']"));
		signInButton.click(); // click on sign in button

		// web elements after click
		WebElement email = d.findElement(By.xpath("//input[@id='email']"));
		WebElement submitLoginButton = d.findElement(By.xpath("//button[@id='SubmitLogin']"));
		
		// random number
		r = new Random();
		int ranNum = r.nextInt(1000);

		//email and password
		String emailInput = "testEmail" + ranNum + "@gmail.com";

		//send email and password
		email.sendKeys(emailInput);
		submitLoginButton.click();

		// web element after click
		WebElement error = d.findElement(By.xpath("//li[contains(text(),'Password is required.')]"));

		Assert.assertTrue(error.isDisplayed());

	}

	@AfterClass
	public void finish() {
		d.quit();
	}

}
