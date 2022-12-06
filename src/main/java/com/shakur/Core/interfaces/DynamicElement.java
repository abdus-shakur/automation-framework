package com.shakur.Core.interfaces;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface DynamicElement {
	
	public WebElement get();

	public WebElement dynamic(String location);

	By dynamicBy(String dynamic);
	
	By by();
	
}
