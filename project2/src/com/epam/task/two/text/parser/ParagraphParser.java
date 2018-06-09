package com.epam.task.two.text.parser;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

/**
 * Parser class for the following links of the
 * chain of responsibility pattern. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class ParagraphParser implements Parser {

	private Parser nextParser;
	private static final Logger LOGGER = Logger.getLogger(ParagraphParser.class);
	ResourceBundle bundle = ResourceBundle.getBundle("com.epam.task.two.text.property.parser", Locale.getDefault());
	
	public ParagraphParser() {
		PropertyConfigurator.configure("resource/log4j.properties");
	}

	/**
	 * Use this to link the next chain unit.
	 * @param Parser
	 * @return Parser
	 */
	@Override
	public void setNextParser(Parser parser) {
		nextParser = parser;
	}

	/**
	 * Use this for parsing the text into the elements
	 * of the given text type.
	 * @param text to parse
	 * @return ArrayList of the components of the parsed text.
	 * @see Component
	 */
	@Override
	public ArrayList<Component> parse(String text, TextType textType) {
		ArrayList<Component> list = new ArrayList<>();

		Pattern pattern = Pattern.compile(bundle.getString(textType.name()));
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			String temp = matcher.group().trim();
			LOGGER.debug("Trying to add " + TextType.values()[textType.getNext()] + " || " + temp);
			LOGGER.debug("To " + textType);
			list.add(new TextComponent(nextParser.parse(temp, TextType.values()[textType.getNext()]), 
					TextType.values()[textType.getNext()]));
			LOGGER.debug("New component " + TextType.values()[textType.getNext()] + " \"" + temp
					+ "\" addead to " + textType);
		}
		return list;
	}

}
