package com.info6255.group4.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group4.selenium.Utils.*;
import com.info6255.group4.selenium.testscenarios.*;

import java.util.concurrent.TimeUnit;


/**
 * Group 4 Selenium Testing Assignment.
 * Main class for testing the scenarios
 *
 */
public class App  {

	static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) throws InterruptedException {
		try (InputStream input = new FileInputStream("./config.properties")) {

			
			   // -----------------------initial step-------------------------------------- 
			   //long scriptStartTime = System.currentTimeMillis(); 
			   Properties prop = new
			   Properties(); prop.load(input);
			   
			   // setting the driver
			   System.setProperty("webdriver.chrome.driver", "chromedriver");
			   
			   // -------------------------------------------------------------------
			   
			   
			   // --------------------PERFORM TESTS----------------------------------
			   /**
				 * Scenario 1
				 */
			   /*
				 * // Initiating your driver 
				 * WebDriver driver = new ChromeDriver();
				 * 
				 * driver.manage().window().maximize(); 
				 * 
				 * // Login 
				 * performLogin(driver, prop);
				 * 
				 * Scenario1AddToFavorites.runScenario(driver, null);
				 * 
				 */
			   
			   /**
				 * Scenario 2
				 */
			   /*
				 * // Initiating your driver 
				 * WebDriver driver = new ChromeDriver();
				 * 
				 * driver.manage().window().maximize(); 
				 * 
				 * // Login 
				 * performLogin(driver, prop);
				 * 
				 * Scenario2DeleteFromFav.runScenario(driver, null);
				 * 
				 */
			   
			   /**
				 * Scenario 3
				 */
			   /*
				 * // Initiating your driver 
				 * WebDriver driver = new ChromeDriver();
				 * 
				 * driver.manage().window().maximize(); 
				 * 
				 * // Login 
				 * performLogin(driver, prop);
				 * 
				 * Scenario3BrowseClasses.runScenario(driver);
				 * 
				 */
			   
			   /**
				 * Scenario 4
				 */
			   /*
				 * // Initiating your driver 
				 * WebDriver driver = new ChromeDriver();
				 * 
				 * driver.manage().window().maximize(); 
				 * 
				 * // Login 
			   	 *	bookstoreLogin(driver, prop, null);
				 * 
				 * Scenario4NUBookstore.runScenario(driver,null);
				 * 
				 */
			   
			 
			   /**
				 * Scenario 5
				 *//*
					 * // Initiating your driver 
					 * WebDriver driver = new ChromeDriver();
					 * driver.manage().window().maximize();
					 * 
					 * // login 
					 * performLogin(driver, prop);
					 * 
					 * // Create a course plan 
					 * Scenario5CoursePlan.runScenario(driver, null);
					 * 
					 * 
					 */
			// ---------------------------------------------------------------------
			   
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void performLogin(WebDriver driver, Properties prop, ExtentTest test) throws InterruptedException {
		//opening me.northeastern page
		driver.get("https://me.northeastern.edu/");
		Utils.takeSnapShot(driver, "./screenshot/login.png");

		//implementing wait on element
		WebDriverWait wait = new WebDriverWait(driver, 500);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"bySelection\"]/div[2]/div")))).click();

		//passing username
		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		username.sendKeys(prop.getProperty("NEU_USERNAME"));
		
		//passing password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(prop.getProperty("NEU_PASSWORD"));
		
		Utils.takeSnapShot(driver, "./screenshot/loginDetails.png");
		
		//click on login
		driver.findElement(By.xpath("/html/body/section/div/div[1]/div/form/div[3]/button")).click();

		
		// code to switch to iframe for duo two factor notification
		driver.switchTo().frame("duo_iframe");
		
		TimeUnit.SECONDS.sleep(2);
		
		//select option for authentication
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(" //*[@id=\"login-form\"]/fieldset/div/select/option[2]")).click();
		Utils.takeSnapShot(driver, "./screenshot/DuoLogin-1.png");

		//click on send
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"auth_methods\"]/fieldset[2]/div[1]/button")).click();
		Utils.takeSnapShot(driver, "./screenshot/DuoLogin-2.png");

		test.log(Status.INFO, "Expected: Login with 2FA, Actual: Logged in with 2FA");
		driver.switchTo().defaultContent();
		
		// click on microsoft login
		driver.findElement(By.xpath("//*[@id=\"idSIButton9\"]")).click();		
		
		
	}
	
	public static void bookstoreLogin (WebDriver driver, Properties prop, ExtentTest test) throws InterruptedException {
		
		//opening bookstore page and logging in
		driver.get("https://northeastern.bncollege.com/");
		Utils.takeSnapShot(driver, "./screenshot/login_bookstore.png");
		driver.findElement(By.xpath("//*[@id=\"bnedLoginButton\"]")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 500);
		
		//switching to login popup and adding details 
		driver.switchTo().frame("loginHeaderIframe1");
		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"email\"]")));
		username.sendKeys(prop.getProperty("BOOKSTORE_USERNAME"));
		
		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"password\"]")));
		password.sendKeys(prop.getProperty("BOOKSTORE_PASSWORD"));
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/login_bookstore_details.png");
		driver.switchTo().defaultContent();
		
		//clicking signin 
		driver.findElement(By.id("submitLoginHeaderForm")).click();
		Utils.takeSnapShot(driver, "./screenshot/bookstore_loggedin.png");
		TimeUnit.SECONDS.sleep(2);
		
		test.log(Status.INFO, "Expected: Login to Bookstore, Actual: Logged in to Bookstore");
		
	}
}
