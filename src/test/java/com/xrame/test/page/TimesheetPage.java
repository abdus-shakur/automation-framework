package com.xrame.test.page;

import com.shakur.Core.interfaces.DynamicElement;
import com.shakur.Core.interfaces.Element;

public class TimesheetPage {

	@Element(xpath = "//input[@type='text' and contains(@class,'userID')]")
	public DynamicElement userName;
	
	@Element(xpath = "//button[@id='auth-code-btn']")
	public DynamicElement selectAuthCode;
	
	@Element(xpath = "//input[@id='authcode1']")
	public DynamicElement authCodeInput;
}
