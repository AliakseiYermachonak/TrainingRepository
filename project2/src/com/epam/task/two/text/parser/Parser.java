package com.epam.task.two.text.parser;

import java.util.ArrayList;
import com.epam.task.two.text.entity.Component;

/**
 * Basic parser interface for realization
 * of Chain of responsibility pattern.
 * @author Alexey Yermachyonok
 * @version 1.0
 */
public interface Parser {
	
	/**
	 * Use this to link the next chain unit.
	 * @param Parser
	 * @return Parser
	 */
	void setNextParser(Parser parser);
	
	/**
	 * Use this for parsing the text into the elements
	 * of the given text type.
	 * @param text to parse
	 * @return ArrayList of the components of the parsed text.
	 * @see Component
	 */
	ArrayList<Component> parse(String text);
	
}
