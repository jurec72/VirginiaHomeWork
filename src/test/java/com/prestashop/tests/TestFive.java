package com.prestashop.tests;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestFive {
	WebDriver d;
	Random r;
	Faker f;

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
	public void loginTest() throws InterruptedException {
		WebElement signInButton = d.findElement(By.xpath("//a[@class='login']"));
		signInButton.click(); // click on sign in button

		// web elements after click sign in button
		WebElement emailInput = d.findElement(By.xpath("//input[@id='email_create']"));
		WebElement createAccountButton = d.findElement(By.xpath("//button[@id='SubmitCreate']"));

		// general elements
		r = new Random();
		f = new Faker();
		// random number
		int indexDay = f.number().numberBetween(1, 31);
		int indexMonth = f.number().numberBetween(1, 12);
		int indexYear = f.number().numberBetween(1950, 2010);
		int indexState = f.number().numberBetween(1, 50);

		// random string
		String phoneNumber = f.phoneNumber().cellPhone();
		String email = f.address().firstName() + "@gmail.com";
		String pass = f.internet().password(5, 13);
		String randomName = f.address().firstName();
		String randomLastName = f.address().lastName();
		String randomCompanyName = f.name().username();
		String randomAdress = f.address().streetAddress();
		String randomZip = "" + f.number().numberBetween(1, 9) + f.number().numberBetween(11, 99)
				+ f.number().numberBetween(11, 99);
		// send email to input and click button create account
		emailInput.sendKeys(email);
		createAccountButton.click();

		// next page with all info
		// choose Mr or Ms random
		WebElement chsMRMS = d.findElement(By.xpath("//input[@id='id_gender1']"));
		chsMRMS.click();

		// next elements
		WebElement firstName = d.findElement(By.xpath("//input[@id='customer_firstname']"));
		WebElement lastName = d.findElement(By.xpath("//input[@id='customer_lastname']"));

		WebElement passForRegistration = d.findElement(By.xpath("//input[@id='passwd']"));

		Thread.sleep(1000);

		firstName.sendKeys(randomName);

		Thread.sleep(1000);
		lastName.sendKeys(randomLastName);

		Thread.sleep(1000);
		passForRegistration.sendKeys(pass);

		// select days random
		WebElement dayClick = d.findElement(By.xpath("//select[@id='days']"));
		Thread.sleep(1000);
		dayClick.click();
		Thread.sleep(1000);
		Select day = new Select(d.findElement(By.xpath("//select[@id='days']")));
		day.selectByValue("" + indexDay);

		// select month random

		WebElement monthClick = d.findElement(By.xpath("//select[@id='months']"));
		Thread.sleep(1000);
		monthClick.click();
		Thread.sleep(1000);
		Select month = new Select(d.findElement(By.xpath("//select[@id='months']")));
		month.selectByValue("" + indexMonth);

		// select year random
		Thread.sleep(1000);
		WebElement yearClick = d.findElement(By.xpath("//select[@id='years']"));

		yearClick.click();
		Thread.sleep(1000);
		Select year = new Select(d.findElement(By.xpath("//select[@id='years']")));
		year.selectByValue("" + indexYear);

		/*
		 * Adress part
		 */

		WebElement companyInAdress = d.findElement(By.xpath("//input[@id='company']"));
		WebElement adressInAdress = d.findElement(By.xpath("//input[@id='address1']"));
		WebElement cityInAdress = d.findElement(By.xpath("//input[@id='city']"));

		Thread.sleep(1000);
		companyInAdress.sendKeys(randomCompanyName);
		Thread.sleep(1000);
		adressInAdress.sendKeys(randomAdress);
		Thread.sleep(1000);
		cityInAdress.sendKeys("New York");

		// choose state random
		WebElement stateClick = d.findElement(By.xpath("//select[@id='id_state']"));
		Thread.sleep(1000);
		stateClick.click();
		Thread.sleep(1000);
		Select state = new Select(d.findElement(By.xpath("//select[@id='id_state']")));
		state.selectByValue("" + indexState);
		Thread.sleep(1000);
		// zip code random
		WebElement zip = d.findElement(By.xpath("//input[@id='postcode']"));
		zip.sendKeys("" + randomZip);
		Thread.sleep(1000);
		// mobile random phone number
		WebElement phoneInAdress = d.findElement(By.xpath("//input[@id='phone_mobile']"));

		phoneInAdress.sendKeys(phoneNumber);

		// alias adress
		WebElement lastAdressInAdress = d.findElement(By.xpath("//input[@id='alias']"));
		Thread.sleep(1000);
		lastAdressInAdress.clear();
		lastAdressInAdress.sendKeys(email);

		// click on register button
		Thread.sleep(1000);
		WebElement registerButtonClick = d.findElement(By.xpath("//button[@id='submitAccount']//span"));
		registerButtonClick.click();
		Thread.sleep(1000);
		// click on sign out button
		WebElement sihnOutButton = d.findElement(By.xpath("//a[@class='logout']"));
		sihnOutButton.click();

		// sign in again
		WebElement emailSignIn = d.findElement(By.xpath("//input[@id='email']"));
		WebElement passwordSignIN = d.findElement(By.xpath("//input[@id='passwd']"));
		WebElement submitLoginButton = d.findElement(By.xpath("//button[@id='SubmitLogin']"));
		Thread.sleep(1000);
		emailSignIn.sendKeys(email);
		passwordSignIN.sendKeys(pass);
		submitLoginButton.click();

		// name on page
		WebElement nameOnPage = d.findElement(By.xpath("//a[@title='View my customer account']/span"));
		Thread.sleep(1000);
		String actualName = nameOnPage.getText();
		String expectedName = randomName + " " + randomLastName;
		//test
		Assert.assertEquals(actualName, expectedName);

	}

	@AfterClass
	public void finish() {
		d.quit();
	}

}
