package com.epam.task.two.text.executors;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

public class TextParser implements Parser{

	private Parser nextParser;
	private static final Logger logger = Logger.getLogger(TextParser.class);
	
	public TextParser() {
		PropertyConfigurator.configure("resources/log4j.properties");
	}

	@Override
	public Parser setNextParser(Parser parser) {
		nextParser = parser;
		System.out.println(nextParser);
		return nextParser;
	}
	
	public void getNextParser() {
		System.out.println(nextParser);
	}

	@Override
	public ArrayList<Component> parse(String text, TextType textType) {
		
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
					list.add(new TextComponent(nextParser.parse(part, TextType.values()[textType.getNext()]),
							TextType.values()[textType.getNext()], false));
				}
			
		}
		return list;
	}

	public static boolean isStartOfListing(String part) {
		return part.matches("[a-z_].+{0,80}\\{");
	}
	
	public static boolean isEndOfListing(String part) {
		return part.matches("\\}");
	}
	
}
