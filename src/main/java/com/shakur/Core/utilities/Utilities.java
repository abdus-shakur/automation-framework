package com.shakur.Core.utilities;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.google.common.io.Files;

public class Utilities {

	public String readClipboardText() {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		DataFlavor textFlavor = DataFlavor.getTextPlainUnicodeFlavor();
		try {
			return new BufferedReader(textFlavor.getReaderForText(cb.getContents("a"))).lines()
					.collect(Collectors.joining());
		} catch (UnsupportedFlavorException | IOException e) {
			return null;
		}
	}
	
	public Object readClipboardImage() {
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		DataFlavor imageFlavor = DataFlavor.imageFlavor;
		try {
			return cb.getData(imageFlavor);
		} catch (UnsupportedFlavorException | IOException e) {
			return null;
		}
	}
	

	public static void takeScreenshot(WebDriver driver,String fileLocation) {
		try {
			Files.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE),new File(fileLocation));
		} catch (WebDriverException | IOException e) {
			e.printStackTrace();
		}
	}

}
