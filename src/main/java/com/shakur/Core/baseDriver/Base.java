package com.shakur.Core.baseDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.shakur.Core.utilities.AutomationOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class Base {
	
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
	
	public static WebDriver launch(AutomationOptions autoOptions,String url) {
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		WebDriverManager.chromedriver().setup();
		ChromeOptions options =new ChromeOptions();
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
		if(Objects.nonNull(autoOptions.getDataLocation())) {
			options.addArguments("user-data-dir="+autoOptions.getDataLocation());
		}
		if(autoOptions.isDisableAutomationOverLay()) {
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		}
		if(autoOptions.isFullScreen()) {
			options.addArguments("--start-fullscreen");	
		}
		options.setHeadless(autoOptions.isHeadless());
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.instagram.com");
		driver.manage().deleteAllCookies();
//		ds_user_id=47211806682; shbts="1653341264\05447211806682\0541684877264:01f7cdc804c62efcba956f7e927f401fe459db82dfff2cf3ad8de8b3e6007fb0f0c5308b"; mid=Yov8SgALAAHQcMXqufuqF6Q2VJcH; sessionid=47211806682%3AiOUqqOxBTt7iV7%3A20; ig_nrcb=1; ig_did=8B3E2FEB-42E9-4E25-9EAB-A3E8668ADBDA; csrftoken=SfchrQqpopzastQDfrLrhCx9nwUH0dSC; shbid="10246\05447211806682\0541684877264:01f7d9f90a1fefe5b34e18162d97de0cb808ab304495c2e0450407db4fb0ac74ddbc3aa4"; rur="PRN\05447211806682\0541684877312:01f7f7eac78385dda94c95d3fab91056a6645b42776e69409e5e3b9ae349d120ccb16cb7"; 
		if(Objects.nonNull(autoOptions.getCookies())) {
			String cooky = autoOptions.getCookies();
			Arrays.asList(cooky.split(";")).stream().filter(fil->!fil.contains("shbts")&&!fil.contains("mid")).forEach(cook->{
				List<String> values = Arrays.asList(cook.split("=",2));
				String name = "";
				String value;
				if(values.size()>0) {
					name = values.get(0);
				}
				if(values.size()>1) {
					value = values.get(1);
					System.out.println("Added Cookie : "+name+ " :: "+value);
					
					Cookie cookie = new Cookie(name,value,".instagram.com","/",new Date(2022-1900, 07, 24));
					
					driver.manage().addCookie(cookie);
				}
			});
			
		}
		driver.get(url);
		return driver;
	}
	

}
