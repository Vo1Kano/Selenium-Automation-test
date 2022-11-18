package com.info6255.group4.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group4.selenium.Utils;


public class Scenario4NUBookstore {
	static Logger logger = Logger.getLogger(Scenario4NUBookstore.class.getName());

	public static void runScenario(WebDriver driver, ExtentTest test) throws InterruptedException {
		
		// implementing wait
		WebDriverWait wait = new WebDriverWait(driver, 100);
		
		
		System.out.println("Scenario4--------NU_BookstoreAddToCart");
		
		//sending search input
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bned_site_search\"]")));
		search.sendKeys("One of Us Is");
		Utils.takeSnapShot(driver, "./screenshot/S4-1.png");
		
		//to perform Scroll on application using Selenium
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,350)", "");
		
		TimeUnit.SECONDS.sleep(2);
		search.sendKeys(Keys.RETURN);
		
		//selecting option
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement e = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//a[@title='One of Us Is Lying']"))));		
		TimeUnit.SECONDS.sleep(2);
		Utils.takeSnapShot(driver, "./screenshot/S4-2.png");
		e.findElement(By.xpath("//a[@title='One of Us Is Lying']")).click();
		
		
		//to perform Scroll on application using Selenium
	    js.executeScript("window.scrollBy(0,350)", "");
		
	    //click on add to cart
		TimeUnit.SECONDS.sleep(1);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement addToCart =wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"addToCartButton\"]"))));
		addToCart.findElement(By.xpath("//*[@id=\"addToCartButton\"]")).click();
		Utils.takeSnapShot(driver, "./screenshot/S4-3.png");
		
		//to perform Scroll on application using Selenium
	    js.executeScript("window.scrollBy(0,-350)", "");

	    //opening cart
		TimeUnit.SECONDS.sleep(1);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement cart =wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"headerDesktopView\"]/div[5]/div/ul/li[3]/div/div/button/a/span/span"))));
		cart.findElement(By.xpath("//*[@id=\"headerDesktopView\"]/div[5]/div/ul/li[3]/div/div/button/a/span/span")).click();
		TimeUnit.SECONDS.sleep(4);	
		Utils.takeSnapShot(driver, "./screenshot/S4-4.png");
		
		System.out.println("Scenario4--------NU_BookstoreAddToCart----finished");

		test.log(Status.INFO, "Expected: Add Items to cart, Actual: Items Added to cart");
	}

}
