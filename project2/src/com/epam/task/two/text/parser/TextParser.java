package com.epam.task.two.text.parser;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.LeafComponent;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.entity.TextType;

/**
 * Parser class for the first link of the
 * chain of responsibility pattern. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class TextParser implements Parser{

	private Parser nextParser;
	private static final Logger LOGGER = Logger.getLogger(TextParser.class);
	ResourceBundle bundle = ResourceBundle.getBundle("com.epam.task.two.text.property.parser", Locale.getDefault());
	public TextParser() {
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
		
		String[] partsOfText = text.split(bundle.getString(textType.name()));
		ArrayList<Component> list = new ArrayList<>();
				
		StringBuilder listing = new StringBuilder();
		boolean listeningListing = false;
		
		for(String part : partsOfText) {
			part.trim();
				if (isStartOfListing(part)||listeningListing) {
					if (isEndOfListing(part)) {
						listing.append(part);
						LOGGER.debug("listing caught");
						list.add(new LeafComponent(listing.toString(), TextType.values()[textType.getNext()]));
						LOGGER.debug("listing added");
						listing = new StringBuilder();
						listeningListing = false;
					} else {
						listing.append(part);
						listing.append("\n");
						listeningListing = true;
					}
				} else {
					list.add(new TextComponent(nextParser.parse(part, TextType.values()[textType.getNext()]),
							TextType.values()[textType.getNext()]));
				}
			
		}
		return list;
	}

	/**
	 * Checks if the String line is a listing start.
	 * @param String line
	 * @return boolean
	 */
	private boolean isStartOfListing(String part) {
		return part.matches(bundle.getString("StartOfListing"));
	}
	
	/**
	 * Checks if the String line is an end of listing.
	 * @param String line
	 * @return boolean
	 */
	private boolean isEndOfListing(String part) {
		return part.matches(bundle.getString("EndOfListing"));
	}
	
}
