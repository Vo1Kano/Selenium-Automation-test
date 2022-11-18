package com.info6255.group4.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group4.selenium.Utils;


public class Scenario1AddToFavorites {
	static Logger logger = Logger.getLogger(Scenario1AddToFavorites.class.getName());

	public static void runScenario(WebDriver driver, ExtentTest test) throws InterruptedException {
		
		// implementing wait
		WebDriverWait wait = new WebDriverWait(driver, 100);
		

		System.out.println("Scenario1--------AddToFavorites");
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//wait for welcome page and click
		WebElement e = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]"))));		
		e.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]")).click();
		Utils.takeSnapShot(driver, "./screenshot/S1-1.png");
		
		//click on resources
		driver.findElement(By.xpath("//*[@id=\"spSiteHeader\"]/div/div[2]/div/div[3]/div/div/div/span[4]/a")).click();
		
		//clicking on Academics and class registration
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]/div[2]/div/div[1]/div/div/img")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S1-2.png");
		TimeUnit.SECONDS.sleep(1);
		
		//to perform Scroll on application using Selenium
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,550)", "");
	    
	    // object of Actions class to scroll up and down
	    Actions at = new Actions(driver);
	    at.sendKeys(Keys.PAGE_DOWN).build().perform();
		
	    //clicking on option to add in favorites
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[5]/div/i")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S1-3.png");
		TimeUnit.SECONDS.sleep(2);

	    //clicking on option to add in favorites
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[9]/div/i")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S1-4.png");
		TimeUnit.SECONDS.sleep(3);
		
		System.out.println("Scenario1--------AddToFavorites----finished");

		//test log
		test.log(Status.INFO, "Expected: Add Course Registration to Favorites, Actual: Added Course Registration to Favorites");
	}

}
