package com.shakur.Core.baseDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

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

	public static WebDriver launchGrid(String browser, String url) {
		ChromeOptions chromeOpt = new ChromeOptions();
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL("http://192.168.0.45:4444/wd/hub"), chromeOpt);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(url);
		return driver;
	}

	public static WebDriver launch(AutomationOptions autoOptions, String url) {
		if(autoOptions.isRemoteExecution()) {
			return launchGrid(null,url);
		}
		WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");
		if (Objects.nonNull(autoOptions.getDataLocation())) {
			options.addArguments("user-data-dir=" + autoOptions.getDataLocation());
		}
		if (autoOptions.isDisableAutomationOverLay()) {
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		}
		if (autoOptions.isFullScreen()) {
			options.addArguments("--start-fullscreen");
		}
		options.setHeadless(autoOptions.isHeadless());

		WebDriver driver = new ChromeDriver(options);
		driver.get(url);
		return driver;
	}

}
