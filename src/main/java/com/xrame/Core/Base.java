package com.xrame.Core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	static WebDriver staticDriver;
	
	public static WebDriver initiateDriver() {
		WebDriverManager.chromedriver().setup();
		staticDriver = new ChromeDriver();
		return staticDriver;
	}
	
	public static WebDriver launchGrid(String browser,String url) {
//		OperaOptions chromeOpt = new OperaOptions();
		ChromeOptions chromeOpt = new ChromeOptions();
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/hub"), chromeOpt);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(url);
		return driver;
	}
	
	public static WebDriver launch(String dataLocation,String url) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options =new ChromeOptions();
		if(null!=dataLocation)
		options.addArguments("user-data-dir="+dataLocation);
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
//		options.addArguments("--start-fullscreen");
//		options.setHeadless(true);
		WebDriver driver = new ChromeDriver(options);
		driver.get(url);
		return driver;
	}
	
	public static void takeScreenshot(WebDriver driver,String fileLocation) {
		try {
			Files.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE),new File(fileLocation));
		} catch (WebDriverException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
