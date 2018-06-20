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

public class TestOne {
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
	public void wrongCredentialsTest() {
		WebElement signInButton = d.findElement(By.xpath("//a[@class='login']"));
		signInButton.click(); // click on sign in button

		WebElement email = d.findElement(By.xpath("//input[@id='email']"));
		WebElement password = d.findElement(By.xpath("//input[@id='passwd']"));
		WebElement submitLoginButton = d.findElement(By.xpath("//button[@id='SubmitLogin']"));

		r = new Random();
		int ranNum = r.nextInt(1000);

		String emailInput = "testEmail" + ranNum + "@gmail.com";
		String passInput = "12345678";

		email.sendKeys(emailInput);
		password.sendKeys(passInput);
		submitLoginButton.click();

		WebElement error = d.findElement(By.xpath("//li[contains(text(),'Authentication failed.')]"));

		Assert.assertTrue(error.isDisplayed());

	}

	@AfterClass
	public void finish() {
		d.quit();
	}

}
