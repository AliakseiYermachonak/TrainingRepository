package com.epam.task.two.text.executors;

import java.util.ArrayList;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextType;

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
	Parser setNextParser(Parser parser);
	
	/**
	 * Use this for parsing the text into the elements
	 * of the given text type.
	 * @param text to parse
	 * @return ArrayList of the components of the parsed text.
	 * @see Component
	 */
	ArrayList<Component> parse(String text, TextType textType);
	
}
