package com.info6255.group4.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Prasanna
 *
 */
public class Utils {
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath){
		try {
		
		//Convert web driver object to TakeScreenshot
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		
		//Move image file to new destination
		File DestFile=new File(fileWithPath);
		
		//Copy file at destination
		FileUtils.copyFile(SrcFile, DestFile);
		
		}
		catch(IOException e)
        {
            System.out.println(e.getMessage());

        }
	
	}
}
