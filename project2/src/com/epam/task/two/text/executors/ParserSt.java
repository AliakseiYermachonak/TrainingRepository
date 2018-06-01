package com.epam.task.two.text.executors;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;
import com.epam.task.two.text.exceptions.WTFException;

public class ParserSt {

	private static final Logger logger = Logger.getLogger(ParserSt.class);
	
	static {
		PropertyConfigurator.configure("resources/log4j.properties");
	}
	
	public static ArrayList<Component> parse(String text, TextType textType) throws WTFException {
		
		switch (textType) {
			case TEXT : {
				logger.debug("start parsing " + textType + "\t");
				return parseText(text, textType);
			}
			case PARAGRAPH : {
				logger.debug("start parsing " + textType + "\t" + text);
				return parseSubText(text, textType);
			}
			case SENTENCE : {
				logger.debug("start parsing " + textType +  "\t" + text);
				return parseSubText(text, textType);
			}
			case WORD : {
				logger.debug("start parsing " + textType +  "\t" + text);
				return parseSubText(text, textType);
			}
			default: {
				throw new WTFException();
			}
		}
	}
	
	public static boolean isStartOfListing(String part) {
		return part.matches("[a-z_].+{0,80}\\{");
	}
	
	public static boolean isEndOfListing(String part) {
		return part.matches("\\}");
	}
	
	public static ArrayList<Component> parseText(String text, TextType textType){
		String[] partsOfText = text.split(textType.getRegex());
		ArrayList<Component> list = new ArrayList<>();
				
		StringBuilder listing = new StringBuilder();
		boolean listeningListing = false;
		
		for(String part : partsOfText) {
			part.trim();
				if (isStartOfListing(part)||listeningListing) {
					if (isEndOfListing(part)) {
						listing.append(part);
						logger.debug("listing caught");
						list.add(new TextComponent(listing.toString(), TextType.values()[textType.getNext()], true));
						logger.debug("listing added");
						listing = new StringBuilder();
						listeningListing = false;
					} else {
						listing.append(part);
						listing.append("\n");
						listeningListing = true;
					}
				} else {
					try {
						/*System.out.println(new TextComponent(parse(part, TextType.values()[textType.getNext()]),
								TextType.values()[textType.getNext()], false));*/
						list.add(new TextComponent(parse(part, TextType.values()[textType.getNext()]),
								TextType.values()[textType.getNext()], false));
						//list.addAll(parse(part, TextType.values()[textType.getNext()]));
					} catch (WTFException e) {
						e.printStackTrace();
					}
				}
			
		}
		return list;
	}
	
	public static ArrayList<Component> parseSubText(String text, TextType textType){
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
				//logger.debug(temp + " " + (temp.matches(TextType.PREWORD)) + " matches PREWORD");
				list.add(new TextComponent(parseSubText(temp, TextType.values()[textType.getNext()]), 
						TextType.values()[textType.getNext()], leaf));
				logger.debug("New component " + TextType.values()[textType.getNext()] + " \"" + temp + "\" addead to " + textType);
			/*} else if ((textType == TextType.SENTENCE)&&(temp.matches(TextType.PREWORD))) {
				//this condition may be deleted if it is not necessary to separate punctuation and words 
				list.addAll(parseSubText(temp, TextType.values()[textType.getNext()]));*/
			}else{
				list.add(new TextComponent(temp, TextType.values()[textType.getNext()], leaf));
				logger.debug("New leaf " + TextType.values()[textType.getNext()] + " \"" + temp + "\" addead to " + textType);
			}
		}
		return list;
	}

	
}					
