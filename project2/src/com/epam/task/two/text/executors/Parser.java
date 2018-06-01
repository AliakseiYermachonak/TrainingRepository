package com.epam.task.two.text.executors;

import java.util.ArrayList;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.TextType;

public interface Parser {

	Parser setNextParser(Parser parser);
	ArrayList<Component> parse(String text, TextType textType);
	
}
