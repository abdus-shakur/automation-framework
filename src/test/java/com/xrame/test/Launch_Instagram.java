package com.xrame.test;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shakur.Core.baseDriver.Base;
import com.shakur.Core.pageFactory.POMFactory;
import com.shakur.Core.utilities.AutomationOptions;
import com.shakur.Core.utilities.Utilities;
import com.xrame.test.page.InstagramPage;

public class Launch_Instagram {
	
	public static void main(String[] args) throws Exception {
		Launch_Instagram li = new Launch_Instagram();
		li.launchInstagram();
	}
	
	
	public void print(String text) {
		System.out.println("*****************************"+text+"*****************************");
	}
	
	
	public void printCookies(WebDriver driver) {Augmenter augumet = new Augmenter();
		WebStorage webstorage = (WebStorage)augumet.augment(driver);
		webstorage.getLocalStorage().keySet().forEach(key->{System.out.println(key+" WS ::: "+webstorage.getLocalStorage().getItem(key));});
		webstorage.getSessionStorage().keySet().forEach(key->{System.out.println(key+" SS ::: "+webstorage.getLocalStorage().getItem(key));});
		extractCookies(driver.manage().getCookies());
		System.out.println(driver.manage().getCookieNamed("csrftoken").getValue());
	}
	
	public void launchInstagram() throws InterruptedException {
		AutomationOptions autoOptions = new AutomationOptions();
		autoOptions.setHeadless(false);
		WebDriver driver = Base.launch(autoOptions, "https://instagram.com");
		print("Before login");
		printCookies(driver);
		InstagramPage instagramPage = POMFactory .generate(driver, InstagramPage.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100), Duration.ofMillis(100));
//		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(instagramPage.instaUserName.dynamicBy(""), 0)).get(0).sendKeys("_sofiKittty._");
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(instagramPage.instaUserName.dynamicBy(""), 0)).get(0).sendKeys("__.iamsana54.__");
//		instagramPage.instaPassword.get().sendKeys("_$ofiKittty._51");
		instagramPage.instaPassword.get().sendKeys("__.iamsana54.__51");
			instagramPage.login.get().click();
			print("Upon login");
			printCookies(driver);
		try {
			instagramPage.homeIcon.get().click();
		}catch(Exception e ) {
			handleOverlay(driver);
			try {
				print("Before CLicking on home icon");
				printCookies(driver);
				instagramPage.svgIcon.dynamic("Home").click();
			}catch(Exception f ) {
				handleOverlay(driver);
			}
		}
		print("Home page");
		printCookies(driver);
		Utilities.takeScreenshot(driver, System.getProperty("user.dir")+"/src/test/resources/test"+UUID.randomUUID().toString()+".png");
		instagramPage.svgIcon.dynamic("New Post").click();
		print("Click on new Post");
		printCookies(driver);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(instagramPage.buttonText.dynamicBy("Select From Computer"), 0)).get(0).sendKeys("__.iamsana54.__");
		instagramPage.buttonText.dynamic("Select From Computer").click();
		print("Click on Select from computer");
		printCookies(driver);
		Thread.sleep(10000);
		instagramPage.buttonText.dynamic("Next").click();
		print("Click on Next after selection");
		printCookies(driver);
		instagramPage.buttonText.dynamic("Next").click();
		print("Click on Next after filter");
		printCookies(driver);
		instagramPage.buttonText.dynamic("Share").click();
		print("Click on Share");
		printCookies(driver);
		Thread.sleep(20000);
		driver.quit();
	}
	
	public void extractCookies(Set<Cookie> cookies) {
		StringBuilder str = new StringBuilder();
		cookies.forEach(cookie->{
			System.out.println("Cookie ::: "+cookie);
			System.out.println(cookie.getValue());
			str.append(cookie.getName()+":"+cookie.getValue());
			str.append("; ");
		});
		System.out.println(str.toString());
	}
	
	public void handleOverlay(WebDriver driver) {
		InstagramPage instagramPage = POMFactory .generate(driver, InstagramPage.class);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100), Duration.ofMillis(100));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(instagramPage.notNowOverlayButton.dynamicBy(""), 0)).get(0).click();
	}

}
