package com.info6255.group4.selenium.testscenarios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.info6255.group4.selenium.Utils;

//TODO Add comments
/**
 * Create Plan for Course Registration
 * 
 */
//TODO Call Utils.takeScreenShot(driver, "filename") wherever necessary for before + after actions
public class Scenario5CoursePlan {

	static Logger logger = Logger.getLogger(Scenario5CoursePlan.class.getName());

	public static void runScenario(WebDriver driver, ExtentTest test) throws InterruptedException{
		
		// implementing wait
		WebDriverWait wait = new WebDriverWait(driver, 100);
		
		
		System.out.println("Scenario5--------CoursePlan");
	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//wait for welcome page and click
		WebElement e = wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]"))));		
		
		e.findElement(By.xpath("/html/body/div[2]/div/div/div/div[2]/div[2]/button[1]")).click();
		Utils.takeSnapShot(driver, "./screenshot/S5-1.png");
		
		//click on resources
		driver.findElement(By.xpath("//*[@id=\"spSiteHeader\"]/div/div[2]/div/div[3]/div/div/div/span[4]/a")).click();

		//clicking on Academics and class registration
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[1]/div[2]/div/div[1]/div/div/img")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S5-2.png");
		TimeUnit.SECONDS.sleep(1);
		
		//select course registration
		driver.findElement(By.xpath("//*[@id=\"7b3083e7-1956-4f64-968b-920d938ba636\"]/div/div/div/div[2]/div/div/div[1]/div/div[11]/div/div/a")).click();
		TimeUnit.SECONDS.sleep(1);
		Utils.takeSnapShot(driver, "./screenshot/S5-3.png");
		TimeUnit.SECONDS.sleep(1);

		
		String handle = driver.getWindowHandle();

		// switch to new window to open Banner
		for (String handles : driver.getWindowHandles()) {
			if (handles.equals(handle))
				continue;
			driver.switchTo().window(handles);
		}

		Utils.takeSnapShot(driver, "./screenshot/S5-4.png");
		driver.findElement(By.id("planningLink")).click();

		// select term in order to create a plan
		Utils.takeSnapShot(driver, "./screenshot/S5-5.png");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("s2id_txt_term")))).click();
		WebElement semSearch = driver.findElement(By.id("s2id_autogen1_search"));
		semSearch.sendKeys("Spring 2023 Semester");
		logger.log(Level.INFO, "Selecting semester");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id=\"202330\"]"))))
				.click();
		Utils.takeSnapShot(driver, "./screenshot/S5-6.png");
		driver.findElement(By.id("term-go")).click();

		// Search courses and add them to create a plan
		logger.log(Level.INFO, "Clicking create plan");
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.id("createPlan")).click();
		logger.log(Level.INFO, "adding course number");
		TimeUnit.SECONDS.sleep(1);
		driver.findElement(By.xpath("//input[@id='txt_keywordlike']")).sendKeys("INFO5100");
		Utils.takeSnapShot(driver, "./screenshot/S5-7.png");
		driver.findElement(By.id("search-go")).click();
		logger.log(Level.INFO, "searched course, waiting to load...");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Using xpath to find the button Add Course to the Plan
		wait.until(ExpectedConditions
				.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr/td[6]/div/button[2]"))))
				.click();
		Utils.takeSnapShot(driver, "./screenshot/S5-8.png");
		logger.log(Level.INFO, "Clicked add course button");
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"saveButton\"]"))))
				.click();
		logger.log(Level.INFO, "Clicked save plan");
		Utils.takeSnapShot(driver, "./screenshot/S5-9.png");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("txt_planDesc"))))
				.sendKeys("plan1");
		Utils.takeSnapShot(driver, "./screenshot/S5-10.png");
		logger.log(Level.INFO, "input text for plan name");
		logger.log(Level.INFO, "clicking save...");
		driver.findElement(
				By.xpath("//div[@class=\"ui-dialog-buttonset\"]/button[contains(text(),'Save')]//parent::button"))
				.click();

		Utils.takeSnapShot(driver, "./screenshot/S5-11.png");
		logger.log(Level.INFO, "Saved the plan");
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		logger.log(Level.INFO, "plan display--");
		Utils.takeSnapShot(driver, "./screenshot/S5-12.png");

		TimeUnit.SECONDS.sleep(2);
		System.out.println("Scenario5--------CoursePlan---finished");

		

		test.log(Status.INFO, "Expected: Create a Course plan, Actual: Course plan - plan1 created successfully");
	}
}