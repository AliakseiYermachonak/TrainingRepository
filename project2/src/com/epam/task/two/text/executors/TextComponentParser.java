package com.epam.task.two.text.executors;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

public class TextComponentParser implements Parser {

	private Parser nextParser;
	private static final Logger logger = Logger.getLogger(TextComponentParser.class);
		
	public TextComponentParser() {
		PropertyConfigurator.configure("resources/log4j.properties");
	}

	@Override
	public Parser setNextParser(Parser parser) {
		nextParser = parser;
		return nextParser;
	}

	@Override
	public ArrayList<Component> parse(String text, TextType textType) {
		ArrayList<Component> list = new ArrayList<>();
		Pattern pattern = Pattern.compile(textType.getRegex());
		Matcher matcher = pattern.matcher(text);
		while (matcher.find()) {
			boolean leaf = false;
			String temp = matcher.group().trim();
			logger.debug("Trying to add " + TextType.values()[textType.getNext()] + " || " + temp);
			logger.debug("To " + textType);
			if (temp.matches("\\d.*?[.]")) {
				leaf = true;
			}
			if ((textType == TextType.SENTENCE)||(textType == TextType.WORD)) {
				leaf = true;
			}
			if ((!leaf)) {
				list.add(new TextComponent(nextParser.parse(temp, TextType.values()[textType.getNext()]), 
						TextType.values()[textType.getNext()], leaf));
				logger.debug("New component " + TextType.values()[textType.getNext()] + " \"" + temp + "\" addead to " + textType);
			}else{
				list.add(new TextComponent(temp, TextType.values()[textType.getNext()], leaf));
				logger.debug("New leaf " + TextType.values()[textType.getNext()] + " \"" + temp + "\" addead to " + textType);
			}
		}
		return list;
	}

}
