package com.xrame.Core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface DynamicElement {
	
	public WebElement getElement();

	DynamicElement setElement(String location);
	
	public WebElement dynamic(String location);

	By dynamicBy(String dynamic);
	
}
