package com.xrame.ElementLocation;

import com.xrame.Core.DynamicElement;
import com.xrame.Core.Element;

public class WhatsappHomePage {
	
	@Element(xpath = "//a[text()='%s']")
	public DynamicElement linkText;
	
	@Element(xpath = "//div[@aria-label='Chat list']/div/descendant::span[@title='%s']/ancestor::div[@role='row']")
	public DynamicElement chatRow;
	
	@Element(xpath = "//div[text()='Type a message']/following-sibling::div[@contenteditable]")
	public DynamicElement typeMessage;
	
	@Element(xpath = "//span[@title='online']")
	public DynamicElement onlineStatus;

}
