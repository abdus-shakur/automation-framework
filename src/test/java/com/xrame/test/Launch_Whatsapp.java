package com.xrame.test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shakur.Core.baseDriver.Base;
import com.shakur.Core.pageFactory.POMFactory;
import com.shakur.Core.utilities.AutomationOptions;
import com.shakur.Core.utilities.Utilities;
import com.shakur.ElementLocation.WhatsappHomePage;

public class Launch_Whatsapp {

	public static void main(String[] args) {

		List<String> instances = new ArrayList<String>();
		instances.add("C:\\Users\\SHA\\AppData\\Local\\Temp\\User Data 2");
		instances.parallelStream().forEach(instance -> {
			Launch_Whatsapp whats = new Launch_Whatsapp();
			WebDriver driver = whats.LaunchWhatsapp(instance);
			Utilities.takeScreenshot(driver, System.getProperty("user.dir")+"/src/test/resources/test34.png");
			whats.checkOnlineStatus(instance, "Shakur jio",driver);
		});
	}
	
	public WebDriver LaunchWhatsapp(String dataLocatoin) {
		AutomationOptions autOptions = new AutomationOptions();
		autOptions.setDataLocation(dataLocatoin);
		autOptions.setFullScreen(false);
		autOptions.setDisableAutomationOverLay(false);
		autOptions.setHeadless(true);
		return Base.launch(autOptions, "https://web.whatsapp.com/");
	}
	
	public void searchContact(String nameOrMobile) {
		
	}
	
	public void checkOnlineStatus(String instance,String name,WebDriver driver) {
		driver.navigate().refresh();
		WhatsappHomePage wh = POMFactory.generate(driver, WhatsappHomePage.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25), Duration.ofMillis(100));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(wh.chatRow.dynamicBy(name), 0));
		wh.chatRow.dynamic(name).click();
		Utilities.takeScreenshot(driver, System.getProperty("user.dir")+"/src/test/resources/test35.png");
		wait.until(ExpectedConditions.visibilityOf(wh.typeMessage.get()));
		Boolean previousState = null;
		Boolean currentState = false;
		StringBuilder str = new StringBuilder();
		String online = "Online @, ";
		String offline = "Offline @, ";
		int pollingTime = 1000;
		while(true) {
			if(driver.findElements(wh.onlineStatus.dynamicBy("")).size()>0) {
				currentState = true;
				if(null==previousState) {
					str.append(online+new Date().toString());
					previousState = true;
				}
				if(currentState!=previousState) {
					if(str.length()<1) {
						str.append(online+new Date().toString());	
					}else {
						str.append(" , "+new Date().toString());
						System.out.println(str.toString());
						str = new StringBuilder();
						str.append(online+new Date().toString());	
					}
					previousState = true;
				}
				
			}else {
				currentState = false;
				if(null==previousState) {
					str.append(offline+new Date().toString());
					previousState = false;
				}
				if(currentState!=previousState) {
					if(str.length()<1) {
						str.append(offline+new Date().toString());	
					}else {
						str.append(" , "+new Date().toString());
						System.out.println(str.toString());
						str = new StringBuilder();
						str.append(offline+new Date().toString());	
					}
					previousState = false;
				}
			}
			try {
				
				Thread.sleep(pollingTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void sendMessageForName(String instance,String name,String messageText,WebDriver driver){
		WhatsappHomePage wh = POMFactory.generate(driver, WhatsappHomePage.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100), Duration.ofMillis(100));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(wh.chatRow.dynamicBy(name), 0));
		wh.chatRow.dynamic(name).click();
		wait.until(ExpectedConditions.visibilityOf(wh.typeMessage.get()));
		wh.typeMessage.get().click();
		wh.typeMessage.get().sendKeys(null==new Utilities().readClipboardText()?"":new Utilities().readClipboardText(), Keys.ENTER);
		new Utilities().readClipboardImage();
		wh.typeMessage.get().sendKeys(Keys.chord(Keys.CONTROL,"V"), Keys.ENTER);
		Utilities.takeScreenshot(driver, "Test.png");
		driver.quit();
	}

}
