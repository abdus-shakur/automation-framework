package com.xrame.Core;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;

public class POMFactory {

	@Element(xpath = "//a[text()='%s']")
	public DynamicElement linkElement;

	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		POMFactory dt = POMFactory.generate(Base.initiateDriver(), POMFactory.class);
		dt.linkElement.dynamic("test").click();
		dt.linkElement.dynamic("find").click();
	}

	public static <T> T generate(WebDriver driver, Class<T> dynamicClass) {
		try {
			T obje = dynamicClass.newInstance();
			Arrays.asList(dynamicClass.getFields()).stream().forEach(field -> {
				if (field.getType().equals(DynamicElement.class))
					try {
						field.set(obje, new DynamicImpl(driver).setElement(field.getAnnotation(Element.class).xpath()));
					} catch (IllegalArgumentException | IllegalAccessException e) {
					}
			});
			return obje;
		} catch (InstantiationException | IllegalAccessException e1) {
			return null;
		}

	}

}
