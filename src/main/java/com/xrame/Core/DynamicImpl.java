package com.xrame.Core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicImpl implements DynamicElement {
	
	WebDriver driver;
	By elementPath;
	String location;
	
	public DynamicImpl(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebElement getElement() {
		return driver.findElement(elementPath);
	}
	
	@Override
	public DynamicElement setElement(String location) {
		this.location = location;
		elementPath =  By.xpath(location);
		return this;
	}

	@Override
	public WebElement dynamic(String dynamic) {
		return driver.findElement(By.xpath(String.format(location,dynamic)));
	}
	
	@Override
	public By dynamicBy(String dynamic) {
		return By.xpath(String.format(location,dynamic));
	}
	
}
