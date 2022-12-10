package com.shakur.Core.pageFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;

import com.shakur.Core.baseDriver.Base;
import com.shakur.Core.element.DynamicImpl;
import com.shakur.Core.interfaces.DynamicElement;
import com.shakur.Core.interfaces.Element;

public class POMFactory {

	@Element(xpath = "//a[text()='%s']")
	public DynamicElement linkElement;

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		POMFactory dt = POMFactory.generate(Base.initiateDefaultDriver(), POMFactory.class);
		dt.linkElement.dynamic("test").click();
		dt.linkElement.dynamic("find").click();
	}

	public static <T> T generate(WebDriver driver, Class<T> dynamicClass) {
		try {
			T obje = dynamicClass.getDeclaredConstructor().newInstance();
			Arrays.asList(dynamicClass.getFields()).stream().forEach(field -> {
				if (field.getType().equals(DynamicElement.class))
					try {
						field.set(obje, new DynamicImpl(driver).setElement(field.getAnnotation(Element.class).xpath()));
					} catch (IllegalArgumentException | IllegalAccessException e) {
					}
			});
			return obje;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e1) {
			return null;
		}

	}

}
