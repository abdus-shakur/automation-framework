package com.xrame.test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.shakur.Core.baseDriver.Base;
import com.shakur.Core.pageFactory.POMFactory;
import com.shakur.Core.utilities.AutomationOptions;
import com.shakur.Core.utilities.Utilities;
import com.xrame.test.page.GooglePage;
import com.xrame.test.page.InstagramPage;

import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;

public class Launch_Test {
	
	public static void main(String[] args) throws Exception {
		Runnable thread = ()->{
				Launch_Test li = new Launch_Test();
				try {
					li.launchInstagram();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
			ArrayList<Runnable> threads = new ArrayList<>();
			threads.add(thread);
			threads.add(thread);
			threads.add(thread);
//		threads.stream().parallel().forEach(Runnable::run);
		ThreadPoolExecutor executor = new UnorderedThreadPoolEventExecutor(4);
		executor.submit(thread);
		executor.submit(thread);
		executor.submit(thread);
		executor.submit(thread);
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
		autoOptions.setRemoteExecution(true);
		long start = System.currentTimeMillis();
		WebDriver driver = Base.launch(autoOptions, "https://www.google.com");
		GooglePage googlePage = POMFactory .generate(driver, GooglePage.class);
		googlePage.search.dynamic("").sendKeys("Test SEarch",Keys.ENTER);
		Utilities.takeScreenshot(driver, System.getProperty("user.dir")+"/src/test/resources/test"+UUID.randomUUID().toString()+".png");
		driver.quit();
		System.out.println(System.currentTimeMillis()-start);
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
