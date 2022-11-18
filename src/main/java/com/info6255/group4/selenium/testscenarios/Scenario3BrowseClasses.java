package com.info6255.group4.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group4.selenium.Utils;
/**
 *
 * @author Prasanna
 *
 */
public class Scenario3BrowseClasses {
//	TODO Add code for screenshot
	static Logger logger = Logger.getLogger(Scenario3BrowseClasses.class.getName());

	public static void runScenario(WebDriver driver, ExtentTest test) throws InterruptedException {
		
		// implementing wait
		WebDriverWait wait = new WebDriverWait(driver, 100);
		
					
		System.out.println("Scenario3--------BrowseClasses");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//wait for welcome page and click
		WebElement e = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]"))));		
		e.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]")).click();
		Utils.takeSnapShot(driver, "./screenshot/S3-1.png");
		
		//click on resources
		driver.findElement(By.xpath("//*[@id=\"spSiteHeader\"]/div/div[2]/div/div[3]/div/div/div/span[4]/a")).click();

		//clicking on Academics and class registration
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]/div[2]/div/div[1]/div/div/img")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S3-2.png");
		TimeUnit.SECONDS.sleep(2);
		
		// object of Actions class to scroll up and down
	    Actions at = new Actions(driver);
	    at.sendKeys(Keys.PAGE_DOWN).build().perform();
		
	    //click on button
	    TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[11]/div/div/a")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S3-3.png");
		TimeUnit.SECONDS.sleep(2);
		
		
		long startRegistrationTimer = System.currentTimeMillis();
		logger.log(Level.INFO, "Clicked course registration, waiting for new window");

		//Switch to new window
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

		//Click on Browse for classes
		logger.log(Level.INFO, "switched to new window, waiting for page to load.........");
		WebElement browseClassLink = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("classSearchLink"))));
		long endRegistrationTimer = System.currentTimeMillis();
		Utils.takeSnapShot(driver, "./screenshot/S3-4.png");
		logger.log(Level.INFO, "Registration window loaded, time taken= " + (endRegistrationTimer-startRegistrationTimer) + " milliseconds");
		browseClassLink.click();

		//Select a Term - Spring 2023 semester 
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
		WebElement semSearch = driver.findElement(By.id("s2id_autogen1_search"));
		semSearch.sendKeys("Spring 2023 Semester");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Utils.takeSnapShot(driver, "./screenshot/S3-5.png");
		logger.log(Level.INFO, "got sem list");

		//Select the spring 2023 div from the dropdown and click
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id=\"202330\"]")))).click();
		logger.log(Level.INFO, "clicked semester");

		Utils.takeSnapShot(driver, "./screenshot/S3-6.png");

		//Click Continue button
		driver.findElement(By.id("term-go")).click();
		
		//Click the div containing Subject input to activate it
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_subject")))).click();
		Utils.takeSnapShot(driver, "./screenshot/S3-7.png");
		
		//Add text to input and select Information Systems program
		WebElement subjectInput = driver
				.findElement(By.xpath("//*[@id=\"s2id_autogen1\"]"));
		subjectInput.sendKeys("Information Systems");
		TimeUnit.SECONDS.sleep(2);
		driver.findElement(By.id("INFO")).click();
		Utils.takeSnapShot(driver, "./screenshot/S3-8.png");
		driver.findElement(By.id("search-go")).click(); // Select
		TimeUnit.SECONDS.sleep(2);
		logger.log(Level.INFO, "Selected the subject");

		long startTimeCourses = System.currentTimeMillis();
		logger.log(Level.INFO, "Selecting items per page"); 
		
		// Courses expected to be displayed here
		//	Show 50 courses per page
		Select pagesDropDown = new Select(wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("page-size-select")))));
		pagesDropDown.selectByValue("50");
		TimeUnit.SECONDS.sleep(3);


		long endTimeCourses = System.currentTimeMillis();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("page-size-select"))));
		Utils.takeSnapShot(driver, "./screenshot/S3-9.png");
		TimeUnit.SECONDS.sleep(4);

		logger.log(Level.INFO, "It took " + (endTimeCourses - startTimeCourses) + "milliseconds to get the courses, and select 50 items per page");
		
		test.log(Status.INFO, "Expected: Browse Courses, Actual: Browsed courses successfully");
		
		System.out.println("Scenario3--------BrowseClasses-----final");

	}

}
