package com.prestashop.tests;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Testing {

	public static void main(String[] args) {
		WebDriver d;
		Random r;
		Faker f = new Faker();

		WebDriverManager.chromedriver().setup();// Set up chromedriver path
		d = new ChromeDriver();
		d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		d.manage().window().fullscreen();

		String url = "http://automationpractice.com/index.php";
		d.get(url);

		WebElement signInButton = d.findElement(By.xpath("//a[@class='login']"));
		signInButton.click(); // click on sign in button

		// web elements after click sign in button
		WebElement emailInput = d.findElement(By.xpath("//input[@id='email_create']"));
		WebElement createAccountButton = d.findElement(By.xpath("//button[@id='SubmitCreate']"));

		// general elements
		r = new Random();

		int indexDay = r.nextInt(30);
		int indexMonth = r.nextInt(11);
		int indexYear = r.nextInt(20);
		int indexState = r.nextInt(50);

		String phoneNumber = f.phoneNumber().cellPhone();
		String email = f.address().firstName()+"@gmail.com";
		String pass = f.internet().password();
		String randomName = f.address().firstName();
		String randomLastName = f.address().lastName();
		String randomCompanyName = f.name().username();
		String randomAdress = f.address().streetAddress();
		String randomZip = f.address().zipCode();
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
		WebElement emailForRedistration = d.findElement(By.xpath("//input[@id='email']"));
		WebElement passForRegistration = d.findElement(By.xpath("//input[@id='passwd']"));

		firstName.sendKeys(randomName);
		lastName.sendKeys(randomLastName);
		emailForRedistration.sendKeys(email);
		passForRegistration.sendKeys(pass);

		// select days random
		WebElement dayClick = d.findElement(By.xpath("//select[@id='days']"));

		dayClick.click();

		Select day = new Select(d.findElement(By.xpath("//select[@id='days']")));
		day.selectByIndex(indexDay);

		// select month random

		WebElement monthClick = d.findElement(By.xpath("//select[@id='months']"));

		monthClick.click();

		Select month = new Select(d.findElement(By.xpath("//select[@id='months']")));
		month.selectByIndex(indexMonth);

		// select year random

		WebElement yearClick = d.findElement(By.xpath("//select[@id='years']"));

		yearClick.click();

		Select year = new Select(d.findElement(By.xpath("//select[@id='years']")));
		month.selectByIndex(indexYear);

		/*
		 * Adress part
		 */
		WebElement nameInAdress = d.findElement(By.xpath("//input[@id='firstname']"));
		WebElement lastNameInAdress = d.findElement(By.xpath("//input[@id='lastname']"));
		WebElement companyInAdress = d.findElement(By.xpath("//input[@id='company']"));
		WebElement adressInAdress = d.findElement(By.xpath("//input[@id='address1']"));
		WebElement cityInAdress = d.findElement(By.xpath("//input[@id='city']"));

		nameInAdress.sendKeys(randomName);
		lastNameInAdress.sendKeys(randomLastName);
		companyInAdress.sendKeys(randomCompanyName);
		adressInAdress.sendKeys(randomAdress);
		cityInAdress.sendKeys("New York");

		// choose state random
		WebElement stateClick = d.findElement(By.xpath("//select[@id='id_state']"));

		stateClick.click();

		Select state = new Select(d.findElement(By.xpath("//select[@id='id_state']")));
		state.selectByIndex(indexState);

		// zip code random
		WebElement zip = d.findElement(By.xpath("//input[@id='postcode']"));
		zip.sendKeys("" + randomZip);

		// mobile random phone number
		WebElement phoneInAdress = d.findElement(By.xpath("//input[@id='phone_mobile']"));

		phoneInAdress.sendKeys(phoneNumber);

		// alias adress
		WebElement lastAdressInAdress = d.findElement(By.xpath("//input[@id='alias']"));

		lastAdressInAdress.sendKeys(f.address().fullAddress());

		// click on register button

		WebElement registerButtonClick = d.findElement(By.xpath("//button[@id='submitAccount']//span"));
		registerButtonClick.click();

		d.quit();

	}

}
