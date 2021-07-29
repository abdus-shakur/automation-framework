package com.shakur.Core.baseDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class BaseImpl {
	
	static WebDriver staticDriver;
	
	public static WebDriver initiateDefaultDriver() {
		WebDriverManager.chromedriver().setup();
		staticDriver = new ChromeDriver();
		return staticDriver;
	}
	
	public static WebDriver launchGrid(String browser,String url) {
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
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
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
	

}
