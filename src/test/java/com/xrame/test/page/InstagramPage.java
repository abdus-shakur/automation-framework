package com.xrame.test.page;

import com.shakur.Core.interfaces.DynamicElement;
import com.shakur.Core.interfaces.Element;

public class InstagramPage {
	
	@Element(xpath = "//input[@name='username']")
	public DynamicElement instaUserName;
	
	@Element(xpath = "//input[@name='password']")
	public DynamicElement instaPassword;
	
	@Element(xpath = "//button[@type='submit']")
	public DynamicElement login;
	
	@Element(xpath = "//button[@type='button' and text()='Not Now']")
	public DynamicElement notNowButton;
	
	@Element(xpath = "//button[ text()='Not Now']")
	public DynamicElement notNowOverlayButton;
	
	@Element(xpath = "//*[local-name() = 'svg' and @aria-label='Home']")
	public DynamicElement homeIcon;
	
	@Element(xpath = "//*[local-name() = 'svg' and @aria-label = 'New Post']")
	public DynamicElement newPostIcon;
	
	@Element(xpath = "//*[local-name() = 'svg' and @aria-label = '%s']")
	public DynamicElement svgIcon;
	
	@Element(xpath = "//button[text()='%s']")
	public DynamicElement buttonText;
	

}
