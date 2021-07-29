package com.shakur.Core.interfaces;

import org.openqa.selenium.WebDriver;

public interface Base {
	
	public WebDriver initiateDefaultDriver();
	
	public WebDriver launchGrid(String browser,String url);
	
	public WebDriver launch(String dataLocation,String url);

}
