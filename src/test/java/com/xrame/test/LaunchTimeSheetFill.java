package com.xrame.test;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.shakur.Core.baseDriver.Base;
import com.shakur.Core.pageFactory.POMFactory;
import com.shakur.Core.utilities.AutomationOptions;
import com.xrame.test.page.TimesheetPage;

public class LaunchTimeSheetFill {
	
	public static void main(String[] args) throws InterruptedException {
		LaunchTimeSheetFill ts = new LaunchTimeSheetFill();
		ts.launchTimeSheet();
	}
	
	public void launchTimeSheet() throws InterruptedException {
		AutomationOptions autoOptions = new AutomationOptions();
		autoOptions.setHeadless(false);
		WebDriver driver = Base.launch(autoOptions, "https://timesheet.ultimatix.net/timesheet/");
		TimesheetPage tsPage = POMFactory.generate(driver, TimesheetPage.class);
		tsPage.userName.get().sendKeys("1456477",Keys.ENTER);
		Thread.sleep(2000);
		tsPage.selectAuthCode.get().click();
		tsPage.authCodeInput.get().sendKeys("818018",Keys.ENTER);
		Thread.sleep(5000);
		printCookies(driver);
	}
	
	public void printCookies(WebDriver driver) {
		StringBuffer buffer = new StringBuffer();
		driver.manage().getCookies().forEach(cookie->{
			buffer.append(cookie.getName()+"="+cookie.getValue()+";");
		});
		System.out.println(buffer.toString());
	}

}
