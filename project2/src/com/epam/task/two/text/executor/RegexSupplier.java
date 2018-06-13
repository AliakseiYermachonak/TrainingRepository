package com.epam.task.two.text.executor;

import java.util.Locale;
import java.util.ResourceBundle;

public class RegexSupplier {
	
	private static ResourceBundle bundle = ResourceBundle.getBundle("com.epam.task.two.text.property.parser", Locale.getDefault());
	
	public static String getRegex(String key) {
		return bundle.getString(key);
	}
	
}
